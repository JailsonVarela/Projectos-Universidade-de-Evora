package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services;

import org.springframework.stereotype.Service;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.LimiteDeDispositivosPorSalaException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.DispositivoIoT;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models.Sala;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.DispositivosIoTRepository;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.repositories.SalaRepository;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.DispositivoIoTDTO;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.SalaDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DispositivoIoTServices {

    private final DispositivosIoTRepository dispositivosIoTRepository;
    private final SalaRepository salaRepository;
    private final SalaServices salaServices;
    private final int LIMITE_DE_DISPOSITIVOS_IOT_POR_SALA = 2;

    public DispositivoIoTServices(DispositivosIoTRepository dispositivosIoTRepository, SalaRepository salaRepository, SalaServices salaServices) {
        this.dispositivosIoTRepository = dispositivosIoTRepository;
        this.salaRepository = salaRepository;
        this.salaServices = salaServices;
    }

    public void createDispositivoIOT(DispositivoIoTDTO dispositivoIoTDTO) {
        if(dispositivoIoTDTO.getId() != null) {
            Optional<DispositivoIoT> dispositivoIoT = this.dispositivosIoTRepository.findById(dispositivoIoTDTO.getId());
            ajudanteCreateDispositivoIOT(dispositivoIoTDTO, dispositivoIoT, "ID", dispositivoIoTDTO.getId().toString());
        } else {
            ajudanteCreateDispositivoIOT(dispositivoIoTDTO, Optional.empty(), "none", dispositivoIoTDTO.getLocalizacao().getId().toString());
        }
    }

    public void updateDispositivoIoT(DispositivoIoTDTO dispositivoIoTDTO){
        if (dispositivoIoTDTO.getId() == null){
            throw new EntidadeNaoExistenteException("DispositivoIoT", "Id", dispositivoIoTDTO.getId());
        }

        Optional<DispositivoIoT> dispositivoIoT = this.dispositivosIoTRepository.findById(dispositivoIoTDTO.getId());
        if(dispositivoIoT.isPresent()){

            DispositivoIoT dispositivo = dispositivoIoT.get();

            Sala localizacao = this.salaRepository.
                    findById(dispositivoIoTDTO.getLocalizacao().getId())
                    .orElseThrow(() -> new EntidadeNaoExistenteException("Sala", "ID", dispositivoIoTDTO.getLocalizacao().getId()));
            dispositivo.setLocalizacao(localizacao);
            this.dispositivosIoTRepository.save(dispositivo);
        }else
            throw new EntidadeNaoExistenteException("DispositivoIoT", "ID", dispositivoIoTDTO.getId() );
    }

    public void delectDispositivoIoT(DispositivoIoTDTO dispositivoIoTDTO){
        if (dispositivoIoTDTO.getId() == null) {
            throw new EntidadeNaoExistenteException("DispositivoIoT", "ID", dispositivoIoTDTO.getId());
        }

        Optional<DispositivoIoT> dispositivoIoT = this.dispositivosIoTRepository.findById(dispositivoIoTDTO.getId());
        if (dispositivoIoT.isPresent()){
            this.dispositivosIoTRepository.delete(dispositivoIoT.get());
        }else
            throw new EntidadeNaoExistenteException("DispositivoIoT", "ID",dispositivoIoTDTO.getId());
    }

    private void ajudanteCreateDispositivoIOT(DispositivoIoTDTO dispositivoIoTDTO, Optional<DispositivoIoT> dispositivoIoT, String propriedade, String valor) throws EntidadeJaExistenteException {
        if(dispositivoIoT.isPresent())
            throw new EntidadeJaExistenteException("DispositivoIoT", propriedade, valor);
        Sala localizacao = this.salaRepository.
                findById(dispositivoIoTDTO.getLocalizacao().getId())
                .orElseThrow(() -> new EntidadeNaoExistenteException("Sala", "ID", dispositivoIoTDTO.getLocalizacao().getId()));
        List<DispositivoIoT> dispositivosNaSala = this.dispositivosIoTRepository.findDispositivoIoTByLocalizacao(localizacao);
        if(dispositivosNaSala.size() >= LIMITE_DE_DISPOSITIVOS_IOT_POR_SALA)
            throw new LimiteDeDispositivosPorSalaException(localizacao.getNome(), LIMITE_DE_DISPOSITIVOS_IOT_POR_SALA);
        this.dispositivosIoTRepository.save(new DispositivoIoT(dispositivoIoTDTO.getId(), localizacao));
    }

    public DispositivoIoTDTO getDipositivoIoTDetailsById(Long dispositivoIoTId) {
        DispositivoIoT dispositivoIoT = this.dispositivosIoTRepository.findById(dispositivoIoTId)
                .orElseThrow(() -> new EntidadeNaoExistenteException("DispositivoIoT", "ID", dispositivoIoTId));
        return new DispositivoIoTDTO(dispositivoIoT.getId(),
                salaServices.getSalaDetailsById(dispositivoIoT.getLocalizacao().getId()));
    }
    
    public List<DispositivoIoTDTO> getAllDispositivos() {
        return dispositivosIoTRepository.findAll().stream()
                .map(dispositivoIoT -> new DispositivoIoTDTO(dispositivoIoT.getId(),
                            salaServices.getSalaDetailsById(dispositivoIoT.getLocalizacao().getId()))
                 ).collect(Collectors.toList());
    } 

}
