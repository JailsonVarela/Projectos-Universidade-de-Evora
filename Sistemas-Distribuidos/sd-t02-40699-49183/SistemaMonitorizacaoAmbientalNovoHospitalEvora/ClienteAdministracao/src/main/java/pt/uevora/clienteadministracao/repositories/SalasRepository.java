package pt.uevora.clienteadministracao.repositories;

import pt.uevora.clienteadministracao.models.Sala;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface SalasRepository {
    @GET("salas")
    Call<List<Sala>> getAllSalas();

    @GET("salas/edificio/{edificioId}")
    Call<List<Sala>> getAllSalasByEdificio(@Path("edificioId") Long edificioId);
}
