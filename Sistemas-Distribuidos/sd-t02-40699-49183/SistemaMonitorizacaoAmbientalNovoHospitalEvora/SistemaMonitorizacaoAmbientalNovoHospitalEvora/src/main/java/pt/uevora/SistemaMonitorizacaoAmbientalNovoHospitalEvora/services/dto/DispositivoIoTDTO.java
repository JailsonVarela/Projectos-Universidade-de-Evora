package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DispositivoIoTDTO {

    private Long id;
    private SalaDTO localizacao;

    public DispositivoIoTDTO() {}

    public DispositivoIoTDTO(Long id) {
        this.id = id;
    }

    public DispositivoIoTDTO(SalaDTO localizacao) {
        this.localizacao = localizacao;
    }

    public DispositivoIoTDTO(Long id, SalaDTO localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }
}
