package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services;

import org.springframework.stereotype.Service;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Edificio;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.EdificioRepositrory;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.EdificioDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EdificioServices {
    private final EdificioRepositrory edificioRepositrory;

    public EdificioServices(EdificioRepositrory edificioRepositrory) {
        this.edificioRepositrory = edificioRepositrory;
    }

    public void createEdificio(EdificioDTO edificioDTO) throws EntidadeJaExistenteException {
        if(edificioDTO.getId() != null) {
            Optional<Edificio> edificio = edificioRepositrory.findById(edificioDTO.getId());
            this.ajudanteCreateEdificio(edificioDTO, edificio, "ID", edificioDTO.getId().toString());
        } else {
            Optional<Edificio> edificio = edificioRepositrory.findEdificioByNome(edificioDTO.getNome());
            ajudanteCreateEdificio(edificioDTO, edificio, "nome", edificioDTO.getNome());
        }
    }

    private void ajudanteCreateEdificio(EdificioDTO edificioDTO, Optional<Edificio> edificio, String propriedade, String valor) {
        if (edificio.isPresent())
            throw new EntidadeJaExistenteException("Edificio", propriedade, valor);
        edificioRepositrory.save(new Edificio(edificioDTO.getNome()));
    }

    public List<EdificioDTO> getAllEdificios() {
        return edificioRepositrory.findAll().stream()
                .map(edificio -> new EdificioDTO(edificio.getId(), edificio.getNome()))
                .collect(Collectors.toList());
    }

    public EdificioDTO getEdificioDetails(Long edificioId) throws EntidadeNaoExistenteException {
        Optional<Edificio> edificio = edificioRepositrory.findById(edificioId);
        if(edificio.isEmpty())
            throw new EntidadeNaoExistenteException("Edificio", "ID", edificioId);
        return new EdificioDTO(edificio.get().getId(), edificio.get().getNome());
    }
}