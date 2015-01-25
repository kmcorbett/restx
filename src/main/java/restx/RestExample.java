package restx;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class RestExample {

    private static ThingList things = new ThingList();
    private static ThingsCRUD crud = new ThingsCRUD(things);

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/things/list", new ThingsListHandler());
        server.createContext("/things/create", new ThingCreateHandler());
        server.createContext("/things/read", new ThingReadHandler());
        server.createContext("/things/update", new ThingUpdateHandler());
        server.createContext("/things/delete", new ThingDeleteHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class ThingsListHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response;
            Integer responseCode = 200;
            try {
                response = crud.doList().toString();
            } catch (JSONException e) {
                response = "JSON Exception: " + e.getMessage();
                responseCode = 500;
            }
            t.sendResponseHeaders(responseCode, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class ThingCreateHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response;
            Integer responseCode;
            Thing tempThing = new Thing();
            tempThing.setName("temp name");
            try {
                response = crud.doCreate(tempThing).toString();
                responseCode = 200;
            } catch (JSONException e) {
                response = "JSON Exception: " + e.getMessage();
                responseCode = 500;
            }
            t.sendResponseHeaders(responseCode, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class ThingReadHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response;
            Integer responseCode;
            try {
                response = crud.doRead(1).toString();
                responseCode = 200;
            } catch (JSONException e) {
                response = "JSON Exception: " + e.getMessage();
                responseCode = 500;
            }
            t.sendResponseHeaders(responseCode, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class ThingUpdateHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response;
            Integer responseCode;
            try {
                response = crud.doUpdate(1, "new name", null).toString();
                responseCode = 200;
            } catch (JSONException e) {
                response = "JSON Exception: " + e.getMessage();
                responseCode = 500;
            }
            t.sendResponseHeaders(responseCode, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    static class ThingDeleteHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response;
            Integer responseCode = 404;
            try {
                response = crud.doDelete(1).toString();
                responseCode = 200;
            } catch (JSONException e) {
                response = "JSON Exception: " + e.getMessage();
                responseCode = 500;
            }
            t.sendResponseHeaders(responseCode, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
