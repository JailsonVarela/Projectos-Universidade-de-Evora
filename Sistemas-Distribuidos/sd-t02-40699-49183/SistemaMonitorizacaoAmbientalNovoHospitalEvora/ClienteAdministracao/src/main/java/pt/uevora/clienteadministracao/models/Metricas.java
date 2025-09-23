package pt.uevora.clienteadministracao.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Metricas {

    private Long id;
    private Double temperatura;
    private Double humidade;
    private LocalDateTime timestamp;
    private Long dispositivoIoTId;

    public Metricas() {}

    public Metricas(Long id, Long dispositivoIoTId, Double temperatura, Double humidade, LocalDateTime timestamp) {
        this.id = id;
        this.dispositivoIoTId = dispositivoIoTId;
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.timestamp = timestamp;
    }

    public Metricas(Long dispositivoIoTId, Double temperatura, Double humidade, LocalDateTime timestamp) {
        this.dispositivoIoTId = dispositivoIoTId;
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MetricasDTO{" +
                "id=" + id +
                ", temperatura=" + temperatura +
                ", humidade=" + humidade +
                ", timestamp=" + timestamp +
                ", dispositivoIoTId=" + dispositivoIoTId +
                '}';
    }
}
