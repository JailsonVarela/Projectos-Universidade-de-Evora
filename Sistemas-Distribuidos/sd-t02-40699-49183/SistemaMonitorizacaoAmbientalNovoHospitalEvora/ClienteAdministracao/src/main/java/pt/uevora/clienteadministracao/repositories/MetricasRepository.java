package pt.uevora.clienteadministracao.repositories;

import pt.uevora.clienteadministracao.models.MediaMetricas;
import pt.uevora.clienteadministracao.models.Metricas;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricasRepository {
    @GET("metricas/media")
    Call<MediaMetricas> getMediaMetricas();
    Call<MediaMetricas> getMediaMetricas(@Query("dataInicio") LocalDateTime dataInicio,
                                         @Query("dataTermino") LocalDateTime dataTermino);

    @GET("metricas/media/sala/{salaId}")
    Call<MediaMetricas> getMediaMetricasPorSala(@Path("salaId") Long salaId);
    Call<MediaMetricas> getMediaMetricasPorSala(@Path("salaId") Long salaId,
                                                @Query("dataInicio") LocalDateTime dataInicio,
                                                @Query("dataTermino") LocalDateTime dataTermino);
    @GET("metricas/media/servico/{servicoId}")
    Call<MediaMetricas> getMediaMetricasPorServico(@Path("servicoId") Long servicoId);
    @GET("metricas/media/servico/{servicoId}")
    Call<MediaMetricas> getMediaMetricasPorServico(@Path("servicoId") Long servicoId,
                                                   @Query("dataInicio") LocalDateTime dataInicio,
                                                   @Query("dataTermino") LocalDateTime dataTermino);

    @GET("metricas/media/edificio/{edificioId}")
    Call<MediaMetricas> getMediaMetricasPorEdificio(@Path("edificioId") Long edificioId);
    @GET("metricas/media/edificio/{edificioId}")
    Call<MediaMetricas> getMediaMetricasPorEdificio(@Path("edificioId") Long edificioId,
                                                    @Query("dataInicio") LocalDateTime dataInicio,
                                                    @Query("dataTermino") LocalDateTime dataTermino);
    @GET("metricas/media/edificio/{edificioId}/piso/{pisoId}")
    Call<MediaMetricas> getMediaMetricasPorPisoEdificio(@Path("edificioId") Long edificioId, @Path("pisoId") Long pisoId);
    @GET("metricas/media/edificio/{edificioId}/piso/{pisoId}")
    Call<MediaMetricas> getMediaMetricasPorPisoEdificio(@Path("edificioId") Long edificioId, @Path("pisoId") Long pisoId,
                                                        @Query("dataInicio") LocalDateTime dataInicio,
                                                        @Query("dataTermino") LocalDateTime dataTermino);


    @GET("metricas")
    Call<List<Metricas>> getAllMetricas();
    @GET("metricas")
    Call<List<Metricas>> getAllMetricas(@Query("dataInicio") LocalDateTime dataInicio,
                                        @Query("dataTermino") LocalDateTime dataTermino);

    @GET("metricas/sala/{salaId}")
    Call<List<Metricas>> getMetricasPorSala(@Path("salaId") Long salaId);
    @GET("metricas/sala/{salaId}")
    Call<List<Metricas>> getMetricasPorSala(@Path("salaId") Long salaId,
                                            @Query("dataInicio") LocalDateTime dataInicio,
                                            @Query("dataTermino") LocalDateTime dataTermino);

    @GET("metricas/servico/{servicoId}")
    Call<List<Metricas>> getMetricasPorServico(@Path("servicoId") Long servicoId);
    @GET("metricas/servico/{servicoId}")
    Call<List<Metricas>> getMetricasPorServico(@Path("servicoId") Long servicoId,
                                               @Query("dataInicio") LocalDateTime dataInicio,
                                               @Query("dataTermino") LocalDateTime dataTermino);

    @GET("metricas/edificio/{edificioId}")
    Call<List<Metricas>> getMetricasPorEdificio(@Path("edificioId") Long edificioId);
    @GET("metricas/edificio/{edificioId}")
    Call<List<Metricas>> getMetricasPorEdificio(@Path("edificioId") Long edificioId,
                                                @Query("dataInicio") LocalDateTime dataInicio,
                                                @Query("dataTermino") LocalDateTime dataTermino);

    @GET("metricas/edificio/{edificioId}/piso/{pisoId}")
    Call<List<Metricas>> getMetricasPorPisoEdificio(@Path("edificioId") Long edificioId, @Path("pisoId") Long pisoId);
    @GET("metricas/edificio/{edificioId}/piso/{pisoId}")
    Call<List<Metricas>> getMetricasPorPisoEdificio(@Path("edificioId") Long edificioId, @Path("pisoId") Long pisoId,
                                                     @Query("dataInicio") LocalDateTime dataInicio,
                                                     @Query("dataTermino") LocalDateTime dataTermino);
}
