package gabriel.infra.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import gabriel.core.UseCaseDto;
import gabriel.infra.reflection.Container;
import gabriel.infra.reflection.MethodHandler;
import gabriel.infra.reflection.Reflection;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final String basePackage;
    private final Container container;

    public ClientHandler(Socket clientSocket, String basePackage) {
        this.clientSocket = clientSocket;
        this.basePackage = basePackage;
        this.container = new Container();
    }

    @Override
    public void run() {
        System.out.println("client: " + Thread.currentThread().getName() + " connected!");

        try {
            handleClientRequest();
        } catch (IOException e) {
            handleIOException(e);
        } finally {
            closeClientSocket();
        }
    }

    private void handleClientRequest() throws IOException {
        InputStream input = clientSocket.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        String url;

        while ((url = reader.readLine()) != null) {
            processRequest(url);
        }
    }

    private void processRequest(String url) {
        try {
            Request request = new Request(url);

            String controllerName = basePackage + request.getControllerName();

            Class<?> controllerClazz = new Reflection().getClass(controllerName);

            Object controller = container.getInstance(controllerClazz);

            UseCaseDto.Output dto = new MethodHandler(controller).executeMethod(request.getMethodName(),
                    request.getBody());

            prepareResponse(dto);
        } catch (Exception e) {
            handleProcessingException(e);
        }
    }

    private void prepareResponse(UseCaseDto.Output dto) throws IOException {
        OutputStream output = clientSocket.getOutputStream();

        JsonSerialize jsonSerialize = new JsonSerializeImpl();

        String response = jsonSerialize.execute(dto);

        PrintWriter writer = new PrintWriter(output, true);
        writer.println(response);
    }

    private void handleIOException(IOException e) {
        System.out.println("An IOException occurred: " + e.getMessage());
        e.printStackTrace();
    }

    private void handleProcessingException(Exception e) {
        System.out.println("An exception occurred while processing the request: " + e.getMessage());
        e.printStackTrace();
    }

    private void closeClientSocket() {
        try {
            clientSocket.close();
            System.out.println("client: " + Thread.currentThread().getName() + " disconnected!");
        } catch (IOException e) {
            System.out.println("An error occurred while closing the client socket: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public <T, K extends T> void register(Class<T> typeInterface, Class<K> typeInstace) {
        this.container.register(typeInterface, typeInstace);
    }

}