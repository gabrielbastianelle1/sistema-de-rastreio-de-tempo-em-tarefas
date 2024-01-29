package gabriel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import gabriel.core.task.domain.Task;
import gabriel.core.user.domain.User;
import gabriel.core.user.repository.UserRepository;
import gabriel.infra.parse.CsvParseImpl;
import gabriel.infra.parse.JsonMapper;
import gabriel.infra.parse.JsonMapperImpl;
import gabriel.infra.parse.JsonParse;
import gabriel.infra.parse.JsonParseImpl;
import gabriel.infra.reflection.Container;
import gabriel.infra.reflection.ObjectFactory;
import gabriel.infra.repository.UserFileRepository;
import gabriel.infra.repository.UserMemoryRepository;
import gabriel.infra.util.ClientHandler;

public class App {

    public static void main(String[] args)
            throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        // String json = "{\"username\":\"myUsername\",\"password\":\"myPassword\"}";

        // ObjectFactory objectFactory = new ObjectFactory();
        // CsvParseImpl<User> csvParseImpl = new CsvParseImpl<>(objectFactory);
        // UserFileRepository repository = new UserFileRepository(csvParseImpl);

        // System.out.println(repository.findAll());

        try (ServerSocket serverSocket = new ServerSocket(4000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                ClientHandler cria = new ClientHandler(clientSocket,
                        "gabriel.infra.controller.");

                cria.register(UserRepository.class, UserMemoryRepository.class);
                cria.register(JsonParse.class, JsonParseImpl.class);
                cria.register(JsonMapper.class, JsonMapperImpl.class);

                Thread my = new Thread(cria, clientSocket.getInetAddress().getHostAddress());
                my.start();
            }
        }
    }
}
