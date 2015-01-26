package restx;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kmc on 1/25/15.
 */
public class ThingResponseStatus implements ThingApiInterface {

    // JSON representation success or failure status of CRUD operation

    public enum ThingResponseStatusCode {
        // HTTP response status codes used in Thing CRUD API
        OK(200), CREATED(201),
        BAD_REQUEST(400), NOT_FOUND(404),
        /* CONFLICT(409) */
        INTERNAL_SERVER_ERROR(500), NOT_IMPLEMENTED(501);
        private Integer responseStatusCode;
        protected Integer getResponseStatusCode() { return responseStatusCode; }
        private ThingResponseStatusCode(Integer code) {
            this.responseStatusCode = code;
        }
    }

    public Boolean getResponseFlag() {
        return responseFlag;
    }

    public ThingResponseStatusCode getResponseCode() {
        return responseCode;
    }

    Boolean responseFlag = false;
    ThingResponseStatusCode responseCode = ThingResponseStatusCode.INTERNAL_SERVER_ERROR;

    public ThingResponseStatus(Boolean flag) {
        this.responseFlag = flag;
        this.responseCode = flag ? ThingResponseStatusCode.OK :
                ThingResponseStatusCode.INTERNAL_SERVER_ERROR;
    }

    public ThingResponseStatus(Boolean flag, ThingResponseStatusCode code) {
        this.responseFlag = flag;
        this.responseCode = code;
    }

    public ThingResponseStatus(ThingResponseStatusCode code) {
        this.responseCode = code;
        switch (code) {
            case OK:
            case CREATED:
                this.responseFlag = true;
                break;
            case BAD_REQUEST:
            case NOT_FOUND:
            case INTERNAL_SERVER_ERROR:
            case NOT_IMPLEMENTED:
                this.responseFlag = false;
        }
    }

    public static String statusKey = "status";
    public static String responseCodeKey = "responseCode";
    public static String successFlagValue = "true";
    public static String failureFlagValue = "false";

    public String getResponseFlagString() {
        return responseFlag ? successFlagValue : failureFlagValue;
    }


    public static String lastChanceJsonFailureResponse() {
        return String.format("{\"%s\":\"%s\";\"%s\":\"%d\"}",
                statusKey, failureFlagValue,
                responseCodeKey,
                ThingResponseStatusCode.INTERNAL_SERVER_ERROR.getResponseStatusCode());
    }


    @Override
    public String toJsonString() {
        JSONObject jsonObject = toJsonObject();
        if (jsonObject != null) {
            return jsonObject.toString();
        } else return lastChanceJsonFailureResponse();
    }

    public JSONObject toJsonObject() {
        try {
            return new JSONObject().
                    put(statusKey, getResponseFlagString()).
                    put(responseCodeKey, getResponseCode().getResponseStatusCode());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
