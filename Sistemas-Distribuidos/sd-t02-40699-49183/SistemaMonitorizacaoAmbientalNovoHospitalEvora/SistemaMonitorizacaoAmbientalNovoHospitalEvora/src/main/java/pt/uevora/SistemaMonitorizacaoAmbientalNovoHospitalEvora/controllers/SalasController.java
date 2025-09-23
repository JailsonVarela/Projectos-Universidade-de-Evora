package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.SalaServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.SalaDTO;

import java.util.List;

@RestController
@RequestMapping("salas")
public class SalasController {

    private final SalaServices salaServices;

    public SalasController(SalaServices salaServices) {
        this.salaServices = salaServices;
    }

    @GetMapping("")
    List<SalaDTO> all() {
        return this.salaServices.listSalas();
    }

    @GetMapping("edificio/{edificioId}")
    List<SalaDTO> allSalasByEdificioId(@PathVariable("edificioId") Long edificioId) {
        return this.salaServices.getSalasByEdificio(edificioId);
    }
}
