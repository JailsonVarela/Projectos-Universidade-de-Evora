package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaDTO {

    private Long id;
    private String nome;
    private Integer piso;
    private EdificioDTO edificio;

    public SalaDTO() {}

    public SalaDTO(Long id) {
        this.id = id;
    }

    public SalaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public SalaDTO(Long id, String nome, int piso, EdificioDTO edificio) {
        this.id = id;
        this.nome = nome;
        this.piso = piso;
        this.edificio = edificio;
    }

    public SalaDTO(String nome, int piso, EdificioDTO edificio) {
        this.nome = nome;
        this.piso = piso;
        this.edificio = edificio;
    }
}

