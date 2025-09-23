package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions;

public class LimiteDeDispositivosPorSalaException extends BaseException {
    public LimiteDeDispositivosPorSalaException(String nomeSala, int limite) {
        super(String.format("A sala %s já alacançou o limite de %d dispositivos IoT", nomeSala, limite));
    }
}
