package gabriel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import gabriel.infra.util.ClientHandler;

public class App {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        // String json = "{\"username\":\"myUsername\",\"password\":\"myPassword\"}";

        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                Runnable cria = new ClientHandler(clientSocket, "gabriel.infra.controller.");
                Thread my = new Thread(cria, clientSocket.getInetAddress().getHostAddress());
                my.start();
            }
        }
    }

}