package pt.uevora.clienteadministracao.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sala {

    private Long id;
    private String nome;
    private Integer piso;
    private Edificio edificio;

    public Sala() {}

    public Sala(Long id) {
        this.id = id;
    }

    public Sala(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Sala(Long id, String nome, int piso, Edificio edificio) {
        this.id = id;
        this.nome = nome;
        this.piso = piso;
        this.edificio = edificio;
    }

    public Sala(String nome, int piso, Edificio edificio) {
        this.nome = nome;
        this.piso = piso;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Localização(id=" + id +
                ", nome='" + nome +
                ", piso=" + piso + ")";
    }
}

