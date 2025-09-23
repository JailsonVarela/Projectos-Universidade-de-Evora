package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Sala;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Servico;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.SalaRepository;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.ServicoRepository;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.ServicoDTO;


@Service
public class ServicoServices {
    private final ServicoRepository servicoRepository;
    private final SalaRepository salaRepository;

    public ServicoServices(ServicoRepository servicoRepository, SalaRepository salaRepository) {
        this.servicoRepository = servicoRepository;
        this.salaRepository = salaRepository;
    }

    public void createServico(ServicoDTO servicoDTO) {
        if(servicoDTO.getId() != null) {
            Optional<Servico> servico = this.servicoRepository.findById(servicoDTO.getId());
            this.ajudanteCreateServico(servicoDTO, servico, "ID", servicoDTO.getId().toString());
        } else {
            Optional<Servico> servico = this.servicoRepository.findServicoByNome(servicoDTO.getNome());
            this.ajudanteCreateServico(servicoDTO, servico, "nome", servicoDTO.getNome());
        }
    }

    private void ajudanteCreateServico(ServicoDTO servicoDTO, Optional<Servico> servico, String propriedade, String valor) throws EntidadeJaExistenteException, EntidadeNaoExistenteException {
        if(servico.isPresent())
            throw new EntidadeJaExistenteException("Servico", propriedade, valor);
        Sala sala = this.salaRepository.findById(servicoDTO.getSala().getId())
                .orElseThrow(() -> new EntidadeNaoExistenteException("Sala", "ID", servicoDTO.getSala().getId()));
        this.servicoRepository.save(new Servico(servicoDTO.getId(), servicoDTO.getNome(), servicoDTO.getDescricao(), sala));
    }

    public List<ServicoDTO> listServicos() {
        return servicoRepository.findAll().stream()
                .map(servico -> new ServicoDTO(servico.getId(), servico.getNome()))
                .collect(Collectors.toList());
    }

    public List<ServicoDTO> getServicosBySala(Long salaId) {
        Sala sala = this.salaRepository.findById(salaId)
                .orElseThrow(() -> new EntidadeNaoExistenteException("Sala", "ID", salaId));
        return servicoRepository.findAllBySala(sala).stream()
                .map(servico -> new ServicoDTO(servico.getId(), servico.getNome()))
                .collect(Collectors.toList());
    }
}
