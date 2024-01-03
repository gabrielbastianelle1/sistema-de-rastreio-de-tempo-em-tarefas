package gabriel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    public static void main(String[] args) throws IOException {
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