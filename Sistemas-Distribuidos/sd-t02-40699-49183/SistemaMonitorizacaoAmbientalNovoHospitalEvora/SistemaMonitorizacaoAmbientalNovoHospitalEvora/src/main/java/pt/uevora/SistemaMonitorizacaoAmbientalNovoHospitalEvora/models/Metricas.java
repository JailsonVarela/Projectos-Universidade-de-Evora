package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "metricas")
public class Metricas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH) //ManyToOne -> muitas metricas para um dispositivo, CascadeType.DETACH -> se apagar um dispositivo não apaga as metricas.
    @JoinColumn(name = "dispositivo_id", referencedColumnName = "id", nullable = false)
    private DispositivoIoT dispositivoIoT;

    @Column(name = "temperatura", nullable = false)
    private double temperatura;

    @Column(name = "humidade", nullable = false)
    private double humidade;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    // Construtores
    public Metricas() {
    }

    public Metricas(DispositivoIoT dispositivoIoT, double temperatura, double humidade, LocalDateTime timestamp) {
        this.dispositivoIoT = dispositivoIoT;
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.timestamp = timestamp;
    }

    public Metricas(Long id, DispositivoIoT dispositivoIoT, double temperatura, double humidade, LocalDateTime timestamp) {
        this.id = id;
        this.dispositivoIoT = dispositivoIoT;
        this.temperatura = temperatura;
        this.humidade = humidade;
        this.timestamp = timestamp;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DispositivoIoT getDispositivoIoT() {
        return dispositivoIoT;
    }

    public void setDispositivoIoT(DispositivoIoT dispositivoIoT) {
        this.dispositivoIoT = dispositivoIoT;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getHumidade() {
        return humidade;
    }

    public void setHumidade(double humidade) {
        this.humidade = humidade;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
