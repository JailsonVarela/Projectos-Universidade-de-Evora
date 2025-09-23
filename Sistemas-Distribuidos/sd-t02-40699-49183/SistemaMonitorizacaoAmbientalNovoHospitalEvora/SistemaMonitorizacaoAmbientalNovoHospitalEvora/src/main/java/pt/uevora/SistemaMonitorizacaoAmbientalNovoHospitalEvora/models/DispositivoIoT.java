package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models;

import jakarta.persistence.*;

@Entity
@Table(name = "dispositivoIoT") // nome da tabela na BD
public class DispositivoIoT {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Id -> chave primária, GeneratedValue -> incremento automático
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH) //ManyToOne -> muitos dispositivos para uma sala, CascadeType.REFERSH -> se apagar uma sala não apaga os dispositivos, apenas atualiza.
    @JoinColumn(name = "localizacaoId", nullable = false)
    private Sala localizacao;

    // Construtores
    public DispositivoIoT() {
    }

    public DispositivoIoT(Long id) {
        this.id = id;
    }

    public DispositivoIoT(Long id, Sala localizacao) {
        this.id = id;
        this.localizacao = localizacao;
    }

    public DispositivoIoT(Sala localizacao) {
        this.localizacao = localizacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sala getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Sala localizacao) {
        this.localizacao = localizacao;
    }

}
