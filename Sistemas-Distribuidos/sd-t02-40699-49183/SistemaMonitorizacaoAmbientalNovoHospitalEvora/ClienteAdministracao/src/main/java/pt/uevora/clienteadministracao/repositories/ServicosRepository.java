package pt.uevora.clienteadministracao.repositories;

import pt.uevora.clienteadministracao.models.Servico;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ServicosRepository {
    @GET("servicos")
    Call<List<Servico>> getAllServicos();

    @GET("servicos/sala/{salaId}")
    Call<List<Servico>> getAllServicosBySala(@Path("salaId") Long salaId);
}
