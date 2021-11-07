package task.http;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class EuroraHttpHandler implements HttpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EuroraHttpHandler.class);

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        LOGGER.info("RECEIVED REQUEST");
        var requestMethod = httpExchange.getRequestMethod();
        if (Objects.equals(requestMethod, "GET")) {
            handleResponse(httpExchange);
        } else {
            handleMethodNotAllowed(httpExchange);
        }
        LOGGER.info("FINISHED HANDLING REQUEST");
    }

    private void handleMethodNotAllowed(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(405, 0);
        var responseStream = httpExchange.getResponseBody();
        var response = "Method Not Allowed";
        responseStream.write(response.getBytes(StandardCharsets.UTF_8));
        responseStream.close();
        httpExchange.close();
    }

    private void handleResponse(HttpExchange httpExchange) throws IOException {
        var url = httpExchange.getRequestURI().toString();
        LOGGER.info("REQUEST URL " + url);
        if(url.equals("/")) {
            httpExchange.sendResponseHeaders(200, 0);
            var responseStream = httpExchange.getResponseBody();
            var response = "It works";
            responseStream.write(response.getBytes(StandardCharsets.UTF_8));
            responseStream.close();
        } else if (url.equals("/ping") || url.equals("/ping/")) {
            httpExchange.sendResponseHeaders(200, 0);
            var responseStream = httpExchange.getResponseBody();
            var response = "ok";
            responseStream.write(response.getBytes(StandardCharsets.UTF_8));
            responseStream.close();
        } else {
            httpExchange.sendResponseHeaders(404, 0);
            var responseStream = httpExchange.getResponseBody();
            var response = "Not Found";
            responseStream.write(response.getBytes(StandardCharsets.UTF_8));
            responseStream.close();
        }
        httpExchange.close();
    }
}
