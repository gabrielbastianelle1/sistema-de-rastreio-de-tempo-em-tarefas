package gabriel.infra.parse;

import java.util.Map;
import java.util.function.Function;

public class MapToPairs {
    private Class<?> clazz;
    private String stringToParse;
    private Map<String, String> map;

    public <T> MapToPairs(Class<T> clazz, String stringToParse) {
        this.clazz = clazz;
        this.stringToParse = stringToParse;
    }

    public ExecuteParse mapToPairs(Function<String, Map<String, String>> operation) {
        this.map = operation.apply(this.stringToParse);
        return new ExecuteParse(clazz, map);
    }

}
