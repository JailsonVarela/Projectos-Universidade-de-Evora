package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.DispositivoIoTServices;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.services.dto.DispositivoIoTDTO;

import java.util.List;

@RestController
@RequestMapping("/dispositivos")
public class DispositivoIoTController {

    private final DispositivoIoTServices dispositivoIoTServices;

    public DispositivoIoTController(DispositivoIoTServices dispositivoIoTServices) {
        this.dispositivoIoTServices = dispositivoIoTServices;
    }

    @PutMapping("{id}")
    void update(@RequestBody DispositivoIoTDTO dispositivoIoTDTO, @PathVariable Long id) {
        dispositivoIoTDTO.setId(id);
        this.dispositivoIoTServices.updateDispositivoIoT(dispositivoIoTDTO);
    }

    @DeleteMapping("/{id}")
    void delete(@RequestBody DispositivoIoTDTO dispositivoIoTDTO, @PathVariable Long id){
        dispositivoIoTDTO.setId(id);
        this.dispositivoIoTServices.delectDispositivoIoT(dispositivoIoTDTO);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void register(@RequestBody DispositivoIoTDTO dispositivoIoT) {
        this.dispositivoIoTServices.createDispositivoIOT(dispositivoIoT);
    }

    @GetMapping("{id}")
    DispositivoIoTDTO getById(@PathVariable Long id) {
        return this.dispositivoIoTServices.getDipositivoIoTDetailsById(id);
    }

    @GetMapping("")
    List<DispositivoIoTDTO> all() {
        return this.dispositivoIoTServices.getAllDispositivos();
    }

    //TODO: Metodos para ver os detalhes dos dispositivos como

}
