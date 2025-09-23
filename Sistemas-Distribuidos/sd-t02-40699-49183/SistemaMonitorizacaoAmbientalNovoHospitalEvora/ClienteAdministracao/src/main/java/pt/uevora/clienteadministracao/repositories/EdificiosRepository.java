package pt.uevora.clienteadministracao.repositories;

import pt.uevora.clienteadministracao.models.Edificio;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface EdificiosRepository {
    @GET("edificios")
    Call<List<Edificio>> getAllEdificios();
}
