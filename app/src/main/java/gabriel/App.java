package gabriel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import gabriel.core.user.repository.UserRepository;
import gabriel.infra.repository.Database;
import gabriel.infra.util.ClientHandler;
import gabriel.infra.util.JsonDeserialize;
import gabriel.infra.util.JsonDeserializeImpl;

public class App {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        // String json = "{\"username\":\"myUsername\",\"password\":\"myPassword\"}";

        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                ClientHandler cria = new ClientHandler(clientSocket,
                        "gabriel.infra.controller.");

                cria.register(UserRepository.class, Database.class);
                cria.register(JsonDeserialize.class, JsonDeserializeImpl.class);

                Thread my = new Thread(cria, clientSocket.getInetAddress().getHostAddress());
                my.start();
            }
        }
    }
}