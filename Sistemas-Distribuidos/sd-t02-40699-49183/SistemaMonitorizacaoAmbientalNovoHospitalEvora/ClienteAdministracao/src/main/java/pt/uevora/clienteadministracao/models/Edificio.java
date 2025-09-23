package pt.uevora.clienteadministracao.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Edificio {

    private Long id;
    private String nome;
    private Integer numeroDePisos;

    public Edificio() {
    }

    public Edificio(Long id) {
        this.id = id;
    }

    public Edificio(String nome) {
        this.nome = nome;
    }

    public Edificio(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Edificio{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numeroDePisos=" + numeroDePisos +
                '}';
    }
}
