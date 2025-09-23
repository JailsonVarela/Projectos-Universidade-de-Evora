package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.utils;


import org.slf4j.Logger;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.LimiteDeDispositivosPorSalaException;

public class ExceptionHandler {

    private final Logger logger;

    public ExceptionHandler(Logger logger) {
        this.logger = logger;
    }

    public void handleCreate(OperationTask operation) {
        try {
            operation.run();
        } catch (EntidadeNaoExistenteException | EntidadeJaExistenteException | LimiteDeDispositivosPorSalaException e) {
            this.logger.info(e.getMessage());
        }
    }
}
