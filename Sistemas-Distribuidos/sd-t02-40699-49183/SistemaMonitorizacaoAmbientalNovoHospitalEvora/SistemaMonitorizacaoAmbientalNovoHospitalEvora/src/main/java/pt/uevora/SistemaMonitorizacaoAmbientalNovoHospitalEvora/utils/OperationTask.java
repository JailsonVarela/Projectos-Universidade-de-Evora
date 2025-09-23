package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.utils;

import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;

public interface OperationTask {
    public void run() throws EntidadeJaExistenteException, EntidadeNaoExistenteException;
}
