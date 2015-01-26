package restx;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class RestExample {

    private static ThingList things = new ThingList();
    private static ThingsCRUD crud = new ThingsCRUD(things);

    public final static Integer defaultPortNumber = 9001;
    public static void main(String[] args) throws Exception {
        Integer portNumber = defaultPortNumber;
        System.out.format("Starting REST example on localhost port %d...\n", portNumber);
        HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
        server.createContext("/things/list", new ThingsListHandler());
        server.createContext("/things/create", new ThingCreateHandler());
        server.createContext("/things/read", new ThingReadHandler());
        server.createContext("/things/update", new ThingUpdateHandler());
        server.createContext("/things/delete", new ThingDeleteHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private static void handlerHelper(HttpExchange t, String response) {
        try {
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        } catch (IOException e) {
        }
    }

    private void handlerHelper(HttpExchange t, JSONObject response) {
        handlerHelper(t, response.toString() + "\n");
    }

    static class ThingsListHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            ThingResponseStatus response = crud.doList();
            RestExample.handlerHelper(t, response.toJsonString());
         }
    }

    static class ThingCreateHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            Thing tempThing = new Thing("temp name", null); // TODO: get parameters
            ThingResponseStatus response = crud.doCreate(tempThing);
            RestExample.handlerHelper(t, response.toJsonString());
        }
    }

    static class ThingReadHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            Integer id = 1; // TODO: get parameters
            ThingResponseStatus response = crud.doRead(id);
            RestExample.handlerHelper(t, response.toJsonString());
        }
    }

    static class ThingUpdateHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            Integer id = 1; // TODO: get parameters
            ThingResponseStatus response = crud.doUpdate(id, "new name", null);
            RestExample.handlerHelper(t, response.toJsonString());
        }
    }

    static class ThingDeleteHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            Integer id = 1;
            ThingResponseStatus response = crud.doDelete(id);
            RestExample.handlerHelper(t, response.toJsonString());
        }
    }
}
