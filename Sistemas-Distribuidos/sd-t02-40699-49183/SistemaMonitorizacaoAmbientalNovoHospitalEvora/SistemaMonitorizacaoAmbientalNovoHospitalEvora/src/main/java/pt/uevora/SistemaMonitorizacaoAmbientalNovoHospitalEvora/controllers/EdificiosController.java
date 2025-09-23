package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.EdificioServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.EdificioDTO;

import java.util.List;

@RestController
@RequestMapping("edificios")
public class EdificiosController {

    private final EdificioServices edificioServices;

    public EdificiosController(EdificioServices edificioServices) {
        this.edificioServices = edificioServices;
    }

    @GetMapping("")
    List<EdificioDTO> all() {
        return this.edificioServices.getAllEdificios();
    }

    //TODO: Metodos para ver os detalhes dos edificios como

}
