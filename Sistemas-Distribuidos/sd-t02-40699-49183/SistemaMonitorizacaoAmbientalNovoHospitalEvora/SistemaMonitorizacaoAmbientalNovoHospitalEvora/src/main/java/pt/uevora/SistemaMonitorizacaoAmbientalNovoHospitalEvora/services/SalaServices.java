package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Edificio;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Sala;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.EdificioRepositrory;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.SalaRepository;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.EdificioDTO;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.SalaDTO;

@Service
public class SalaServices {

    private final SalaRepository salaRepository;
    private final EdificioRepositrory edificioRepositrory;
    private final EdificioServices edificioServices;

    public SalaServices(SalaRepository salaRepository, EdificioRepositrory edificioRepositrory,
                        EdificioServices edificioServices) {
        this.salaRepository = salaRepository;
        this.edificioRepositrory = edificioRepositrory;
        this.edificioServices = edificioServices;
    }

    public void createSala(SalaDTO salaDTO) throws EntidadeJaExistenteException {
        if(salaDTO.getId() != null) {
            Optional<Sala> sala = this.salaRepository.findById(salaDTO.getId());
            ajudanteCreateSala(salaDTO, sala, "ID", salaDTO.getId().toString());
        } else {
            Optional<Sala> sala = this.salaRepository.findSalaByNome(salaDTO.getNome());
            ajudanteCreateSala(salaDTO, sala, "nome", salaDTO.getNome());
        }
    }

    private void ajudanteCreateSala(SalaDTO salaDTO, Optional<Sala> sala, String propriedade, String valor) throws EntidadeJaExistenteException {
        if(sala.isPresent())
            throw new EntidadeJaExistenteException("Sala", propriedade, valor);
        Edificio edificio = this.edificioRepositrory.
                findById(salaDTO.getEdificio().getId())
                .orElseThrow(() -> new EntidadeNaoExistenteException("Edificio", "ID", salaDTO.getEdificio().getId()));
        this.salaRepository.save(new Sala(salaDTO.getId(), salaDTO.getNome(), salaDTO.getPiso(), edificio));
    }

    public SalaDTO getSalaDetailsById(Long salaId) {
        Optional<Sala> sala = this.salaRepository.findById(salaId);
        if(sala.isEmpty())
            throw new EntidadeNaoExistenteException("Sala", "ID", salaId);
        Sala conteudo = sala.get();
        return new SalaDTO(conteudo.getId(), conteudo.getNome(), conteudo.getPiso(),
                this.edificioServices.getEdificioDetails(conteudo.getEdificio().getId()));
    }

    public SalaDTO getSalaDetailsByNome(String nome) throws EntidadeNaoExistenteException {
        Optional<Sala> sala = this.salaRepository.findSalaByNome(nome);
        if(sala.isEmpty())
            throw new EntidadeNaoExistenteException("Sala", "nome", nome);
        Sala conteudo = sala.get();
        return new SalaDTO(conteudo.getId(), conteudo.getNome(), conteudo.getPiso(),
                this.edificioServices.getEdificioDetails(conteudo.getEdificio().getId()));
    }

    public List<SalaDTO> listSalas() {
        return this.salaRepository.findAll().stream()
        .map(sala -> new SalaDTO(sala.getId(), sala.getNome(), sala.getPiso(),
                     this.edificioServices.getEdificioDetails(sala.getEdificio().getId())))
        .collect(Collectors.toList());
    }

    public List<SalaDTO> getSalasByEdificio(Long edificioId) {
        Edificio edificio = this.edificioRepositrory.findById(edificioId)
                .orElseThrow(() -> new EntidadeNaoExistenteException("Edifício", "ID", edificioId));
        return this.salaRepository.findAllByEdificio(edificio).stream()
                .map(sala -> new SalaDTO(sala.getId(), sala.getNome(), sala.getPiso(),
                        this.edificioServices.getEdificioDetails(sala.getEdificio().getId())))
                .collect(Collectors.toList());
    }
}
