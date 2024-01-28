package gabriel.infra.parse;

import java.util.Map;

import gabriel.infra.reflection.ObjectFactory;

public class JsonParseImpl implements JsonParse {

    private final JsonMapper mapper;
    private final ObjectFactory objectFactory;

    public JsonParseImpl(JsonMapper mapper, ObjectFactory objectFactory) {
        this.mapper = mapper;
        this.objectFactory = objectFactory;
    }

    @Override
    public <T> T execute(String json, Class<T> clazz) {
        Map<String, String> map = mapper.execute(json);
        return objectFactory.execute(map, clazz);
    }

}
