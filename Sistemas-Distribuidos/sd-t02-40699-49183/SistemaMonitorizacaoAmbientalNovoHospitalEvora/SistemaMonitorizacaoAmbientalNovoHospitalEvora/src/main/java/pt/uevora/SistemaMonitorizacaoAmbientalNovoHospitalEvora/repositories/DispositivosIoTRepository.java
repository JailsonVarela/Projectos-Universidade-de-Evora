package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.DispositivoIoT;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Sala;

import java.util.List;

public interface DispositivosIoTRepository extends BaseRepository<DispositivoIoT, Long> {
    List<DispositivoIoT> findDispositivoIoTByLocalizacao(Sala sala);
}
