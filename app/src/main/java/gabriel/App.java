package gabriel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

import gabriel.core.user.domain.User;
import gabriel.core.user.domain.Username;
import gabriel.core.user.repository.UserRepository;
import gabriel.infra.parse.JsonMapper;
import gabriel.infra.parse.JsonMapperImpl;
import gabriel.infra.parse.JsonParse;
import gabriel.infra.parse.JsonParseImpl;
import gabriel.infra.reflection.ObjectFactory;
import gabriel.infra.repository.UserMemoryRepository;
import gabriel.infra.repository.UserSqlRepository;
import gabriel.infra.util.ClientHandler;

public class App {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        // String json = "{\"username\":\"myUsername\",\"password\":\"myPassword\"}";

        // UserSqlRepository repository = new
        // UserSqlRepository("jdbc:postgresql://db:5432/project", "gabriel", "123",
        // new ObjectFactory());

        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            System.out.println("Server running on port 4000");
            while (true) {
                Socket clientSocket = serverSocket.accept();

                ClientHandler cria = new ClientHandler(clientSocket,
                        "gabriel.infra.controller.");

                cria.register(UserRepository.class, UserSqlRepository.class);
                cria.register(JsonParse.class, JsonParseImpl.class);
                cria.register(JsonMapper.class, JsonMapperImpl.class);

                Thread my = new Thread(cria, clientSocket.getInetAddress().getHostAddress());
                my.start();
            }
        }
    }
}
