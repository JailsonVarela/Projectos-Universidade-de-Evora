package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories;

import org.springframework.data.jpa.repository.Query;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.DispositivoIoT;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Metricas;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Servico;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricasRepository extends BaseRepository <Metricas, Long> {
    List<Metricas> findAllByDispositivoIoT(DispositivoIoT dispositivoIoT);

    List<Metricas> findAllByTimestampBetween(LocalDateTime dataInicio, LocalDateTime dataTermino);

    @Query("select m from Metricas m inner join Servico s on m.dispositivoIoT.localizacao.id = s.sala.id where s.id = ?1 and m.timestamp between ?2 and ?3")
    List<Metricas> findAllByServicoIdAndTimestampBetween(Long servicoId, LocalDateTime dataInicio, LocalDateTime dataTermino );

    List<Metricas> findAllByDispositivoIoTAndTimestampBetween(DispositivoIoT dispositivoIoT, LocalDateTime dataInicio, LocalDateTime dataTermino);

    @Query("select m from Metricas m where m.dispositivoIoT.localizacao.edificio.id = ?1 and m.dispositivoIoT.localizacao.piso = ?2 and m.timestamp between ?3 and ?4")
    List<Metricas> findAllByEdificioIdAndPisoAndTimestampBetween(Long edificioId, int piso, LocalDateTime dataInicio, LocalDateTime dataTermino);
}
