package pt.uevora.dispositivoIoT;

import java.time.LocalDateTime;

public class Mensagem {

    private Long dispositivoIoTId;
    private Double temperatura;
    private Double humidade;
    private LocalDateTime timestamp;

    public Mensagem(Long dispositivoId, Double temperatura, Double humidade, LocalDateTime timestamp) {
        this.dispositivoIoTId = dispositivoId;
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.timestamp = timestamp;
    }

    public Long getDispositivoIoTId() {
        return dispositivoIoTId;
    }

    public void setDispositivoIoTId(Long dispositivoIoTId) {
        this.dispositivoIoTId = dispositivoIoTId;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getHumidade() {
        return humidade;
    }

    public void setHumidade(Double humidade) {
        this.humidade = humidade;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" + "\"dispositivoIoTId\": " + dispositivoIoTId + ", " +
                     "\"temperatura\": " + temperatura + ", " +
                     "\"humidade\": " + humidade + ", " +
                     "\"timestamp\": \"" + timestamp.toString() + "\"}";
    }
}
