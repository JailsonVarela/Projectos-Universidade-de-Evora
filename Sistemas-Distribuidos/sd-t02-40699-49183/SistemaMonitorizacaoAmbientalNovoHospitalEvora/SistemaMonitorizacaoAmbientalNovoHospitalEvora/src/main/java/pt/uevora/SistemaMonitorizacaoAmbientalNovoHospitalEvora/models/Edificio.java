package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "edificio")
public class Edificio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = true)
    private String nome;

    @OneToMany(mappedBy = "edificio")
    private List<Sala> salas;

    // Construtores
    public Edificio() {
    }

    public Edificio(String nome) {
        this.nome = nome;
    }

    public Edificio(Long id, String nome) {
        this.nome = nome;
    }

    public Edificio(Long id, String nome, List<Sala> salas) {
        this.id = id;
        this.nome = nome;
        this.salas = salas;
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

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

}
