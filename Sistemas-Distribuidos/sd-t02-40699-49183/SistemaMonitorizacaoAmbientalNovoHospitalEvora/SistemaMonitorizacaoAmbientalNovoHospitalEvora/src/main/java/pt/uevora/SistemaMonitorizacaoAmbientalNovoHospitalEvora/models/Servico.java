package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models;

import jakarta.persistence.*;

@Entity
@Table(name = "servico")
public class Servico {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao", nullable = true)
    private String descricao;

    @ManyToOne(cascade = CascadeType.REFRESH) //ManyToOne -> muitos serviços para uma sala, CascadeType.REFERSH -> se apagar uma sala não apaga os serviços, apenas atualiza.
    @JoinColumn(name = "salaId",referencedColumnName = "id", nullable = true)
    private Sala sala;

    // Construtores
    public Servico() {
    }

    public Servico(String nome) {
        this.nome = nome;
    }

    public Servico(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Servico(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Servico(Long id, String nome, String descricao, Sala sala) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.sala = sala;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
