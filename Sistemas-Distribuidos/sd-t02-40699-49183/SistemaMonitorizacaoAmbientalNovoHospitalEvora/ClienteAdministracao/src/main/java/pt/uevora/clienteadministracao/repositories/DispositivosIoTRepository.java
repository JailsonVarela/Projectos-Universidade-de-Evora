package pt.uevora.clienteadministracao.repositories;

import okhttp3.RequestBody;
import pt.uevora.clienteadministracao.models.DispositivoIoT;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface DispositivosIoTRepository {
    @GET("dispositivos")
    Call<List<DispositivoIoT>> getAllDispositivoIoT();

    @GET("dispositivos/{id}")
    Call<DispositivoIoT> getDispositivoIoTbyId(@Path("id") Long id);

    @POST("dispositivos")
    Call<Void> createDispositivoIoT(@Body RequestBody dispositivoIoT);

    @DELETE("dispositivos/{id}")
    Call<Void> deleteDispositivoIoT(@Path("id") Long id);

    @PUT("dispositivos/{id}")
    Call<Void> updateDispositivoIoT(@Body RequestBody dispositivoIoT, @Path("id") Long id);
}

