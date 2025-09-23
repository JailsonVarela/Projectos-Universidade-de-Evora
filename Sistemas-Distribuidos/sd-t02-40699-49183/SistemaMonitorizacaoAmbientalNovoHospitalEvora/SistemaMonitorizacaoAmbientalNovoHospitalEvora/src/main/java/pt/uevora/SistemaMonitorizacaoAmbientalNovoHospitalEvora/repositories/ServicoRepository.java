package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Sala;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Servico;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends BaseRepository<Servico, Long> {
    Optional<Servico> findServicoByNome(String nome);
    List<Servico> findAllBySala(Sala sala);
}