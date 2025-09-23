package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private SalaDTO sala;

    public ServicoDTO(Long id) {
        this.id = id;
    }

    public ServicoDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public ServicoDTO(Long id, String nome, String descricao, SalaDTO servicoDTO) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.sala = servicoDTO;
    }

    public ServicoDTO(String nome, String descricao, SalaDTO servicoDTO) {
        this.nome = nome;
        this.descricao = descricao;
        this.sala = servicoDTO;
    }
}
