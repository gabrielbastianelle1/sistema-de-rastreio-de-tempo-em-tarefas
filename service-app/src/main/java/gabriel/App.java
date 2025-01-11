package gabriel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;

import gabriel.core.user.repository.UserRepository;
import gabriel.infra.myspring.Component;
import gabriel.infra.myspring.FrameworkApplication;
import gabriel.infra.myspring.Inject;
import gabriel.infra.parse.JsonMapper;
import gabriel.infra.parse.JsonMapperImpl;
import gabriel.infra.parse.JsonParse;
import gabriel.infra.parse.JsonParseImpl;
import gabriel.infra.repository.UserSqlRepository;
import gabriel.infra.util.ClientHandler;

@Component
class HelloService {
    public void sayHello() {
        System.out.println("Hello from the framework!");
    }
}

public class App {
    @Inject
    private HelloService helloService;

    public void start() {
        helloService.sayHello();
    }

    private static Integer executeTransaction(final String test, Function<Integer, Integer> operation) {
        // Example logic: Just apply the operation on a sample value, here we use 5 as
        // an example
        Integer result = operation.apply(50); // Applying the operation to an input value (e.g., 5)
        return result; // Return the result of the operation
    }

    public static void main(String[] args) {
        System.out.println("testani");

        Integer result = executeTransaction("peidei caraio", a -> {
            System.out.println(a); // Print the value (this part is fine)
            return a * 2; // Return an integer as the result (multiply by 2)
        });
        System.out.println(result);
        // FrameworkApplication.run(App.class);
    }

    // public static void main(String[] args)
    // throws IOException, ClassNotFoundException, NoSuchMethodException,
    // SecurityException,
    // InstantiationException, IllegalAccessException, IllegalArgumentException,
    // InvocationTargetException {

    // // String json = "{\"username\":\"myUsername\",\"password\":\"myPassword\"}";

    // // UserSqlRepository repository = new
    // // UserSqlRepository("jdbc:postgresql://db:5432/project", "gabriel", "123",
    // // new ObjectFactory());

    // try (ServerSocket serverSocket = new ServerSocket(4000)) {
    // System.out.println("Server running on port 4000");
    // while (true) {
    // Socket clientSocket = serverSocket.accept();

    // ClientHandler cria = new ClientHandler(clientSocket,
    // "gabriel.infra.controller.");

    // cria.register(UserRepository.class, UserSqlRepository.class);
    // cria.register(JsonParse.class, JsonParseImpl.class);
    // cria.register(JsonMapper.class, JsonMapperImpl.class);

    // Thread my = new Thread(cria, clientSocket.getInetAddress().getHostAddress());
    // my.start();
    // }
    // }
    // }
}
