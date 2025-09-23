package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EdificioDTO {

    private Long id;
    private String nome;

    public EdificioDTO() {
    }

    public EdificioDTO(Long id) {
        this.id = id;
    }

    public EdificioDTO(String nome) {
        this.nome = nome;
    }

    public EdificioDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
