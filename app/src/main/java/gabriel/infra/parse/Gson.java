package gabriel.infra.parse;

import java.util.Map;
import java.util.function.Function;

public class Gson implements JsonParse {

    @Override
    public <K, V> Map<K, V> a(String json) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'a'");
    }

    @Override
    public <T> T execute(Class<T> clazz, String stringToParse, Function<String, String[]> regexPatter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public <T> ClassInput defineClass(Class<T> clazz) {
        return new ClassInput(clazz);
    }

}
