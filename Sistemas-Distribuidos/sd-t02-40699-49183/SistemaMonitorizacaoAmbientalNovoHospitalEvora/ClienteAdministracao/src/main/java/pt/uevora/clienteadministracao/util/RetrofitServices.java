package pt.uevora.clienteadministracao.util;

import com.google.gson.*;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class RetrofitServices {
    private final Retrofit retrofit;
    private final Gson gson;

    public RetrofitServices() {
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                        new JsonDeserializer<LocalDateTime>() {
                            @Override
                            public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                                return LocalDateTime.parse(jsonElement.getAsString());
                            }
                        })
                .create();
        this.retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://localhost:8080/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public <T> T construirServico(Class<T> classRefence) {
        return this.retrofit.create(classRefence);
    }

    public <T> T extractDatas(Call<T> datas) throws Exception {
        Response<T> rs = datas.execute();
        if (datas.isExecuted() && rs.isSuccessful())
            return rs.body();
        if(rs.errorBody() == null)
            throw new Exception("Ocorreu um erro ao fazer a requisição");
        else
            throw  new Exception(rs.errorBody().string());
    }

    public <T> RequestBody createRequestBody(T entity) {
        return RequestBody.create(MediaType.parse("application/json"), gson.toJson(entity));
    }
}
