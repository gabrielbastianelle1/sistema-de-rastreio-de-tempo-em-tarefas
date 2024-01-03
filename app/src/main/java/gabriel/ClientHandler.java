package gabriel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final String basePackage;

    public ClientHandler(Socket clientSocket, String basePackage) {
        this.clientSocket = clientSocket;
        this.basePackage = basePackage;
    }

    @Override
    public void run() {
        System.out.println("client: " + Thread.currentThread().getName() + " connected!");

        try {
            InputStream input = clientSocket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input,
                    StandardCharsets.UTF_8));

            String url;

            while ((url = reader.readLine()) != null) {
                Request request = new Request(url);

                // basePackage + request.getControllerName()

                new ControllerHandler(basePackage + request.getControllerName()).refletClass().createNewInstance()
                        .executeMethod(request.getMethodName());

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("client: " + Thread.currentThread().getName() + "disconnected!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}