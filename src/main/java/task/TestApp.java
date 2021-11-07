package task;

import com.sun.net.httpserver.HttpServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.http.EuroraHttpHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestApp.class);

    public static void main(String[] args) {
        try {
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/", new EuroraHttpHandler());
            server.setExecutor(threadPoolExecutor);
            server.start();
            LOGGER.info("SERVER STARTED");
        } catch (IOException ioException) {
            LOGGER.error("Exception occurred", ioException);
        }

    }
}
