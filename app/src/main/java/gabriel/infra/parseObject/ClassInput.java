package gabriel.infra.parseObject;

public class ClassInput {
    private Class<?> clazz;
    private String stringToParse;

    public <T> ClassInput(Class<T> clazz) {
        this.clazz = clazz;
    }

    public MapToPairs defineString(String string) {
        this.stringToParse = string;
        return new MapToPairs(clazz, stringToParse);
    }

}
