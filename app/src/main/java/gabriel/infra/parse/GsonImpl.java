package gabriel.infra.parse;

import com.google.gson.Gson;

public class GsonImpl implements JsonParse {

    private Gson gson;

    private GsonImpl() {
        this.gson = new Gson();
    }

    @Override
    public <T> T execute(String json, Class<T> clazz) {
        T object = gson.fromJson(json, clazz);
        return object;
    }

}
