package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Edificio;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Sala;

import java.util.List;
import java.util.Optional;

public interface SalaRepository extends BaseRepository<Sala, Long> {
    Optional<Sala> findSalaByNome(String nome);
    List<Sala> findAllByEdificio(Edificio edificio);
}
