package pt.uevora.clienteadministracao.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servico {
    private Long id;
    private String nome;
    private String descricao;
    private Sala sala;

    public Servico(Long id) {
        this.id = id;
    }

    public Servico(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Servico(Long id, String nome, String descricao, Sala servicoDTO) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.sala = servicoDTO;
    }

    public Servico(String nome, String descricao, Sala servicoDTO) {
        this.nome = nome;
        this.descricao = descricao;
        this.sala = servicoDTO;
    }

    @Override
    public String toString() {
        return "Servico(" +
                "id=" + id +
                ", nome= '" + nome +
                ')';
    }
}
