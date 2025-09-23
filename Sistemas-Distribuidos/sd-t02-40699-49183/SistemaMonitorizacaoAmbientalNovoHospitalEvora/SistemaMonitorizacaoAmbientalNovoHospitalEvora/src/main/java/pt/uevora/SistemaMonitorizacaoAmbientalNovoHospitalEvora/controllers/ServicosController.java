package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.ServicoServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.ServicoDTO;

@RestController
@RequestMapping("servicos")
public class ServicosController {

    private final ServicoServices servicoServices;

    public ServicosController(ServicoServices servicoServices) {
        this.servicoServices = servicoServices;
    }

    @GetMapping("")
    List<ServicoDTO> all() {
        return this.servicoServices.listServicos();
    }

    @GetMapping("sala/{salaId}")
    List<ServicoDTO> allBySala(@PathVariable("salaId") Long salaId) {
        return this.servicoServices.getServicosBySala(salaId);
    }
}
