package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Edificio;

import java.util.Optional;

public interface EdificioRepositrory extends BaseRepository<Edificio, Long> {
    //TODO: Change EdificioRepositrory to EdificioRepository
    Optional<Edificio> findEdificioByNome(String nome);
}
