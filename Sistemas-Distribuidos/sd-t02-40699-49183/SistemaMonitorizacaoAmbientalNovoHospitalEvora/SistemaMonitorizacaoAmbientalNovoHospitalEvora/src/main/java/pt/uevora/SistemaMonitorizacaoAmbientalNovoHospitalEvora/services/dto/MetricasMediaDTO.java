package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MetricasMediaDTO {
    private Double temperaturaMedia;
    private Double humidadeMedia;
}