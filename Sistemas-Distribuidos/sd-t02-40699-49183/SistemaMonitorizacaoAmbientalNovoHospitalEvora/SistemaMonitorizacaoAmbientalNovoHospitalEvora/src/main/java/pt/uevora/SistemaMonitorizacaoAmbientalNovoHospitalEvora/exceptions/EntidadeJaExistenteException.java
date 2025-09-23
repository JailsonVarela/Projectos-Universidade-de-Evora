package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions;

public class EntidadeJaExistenteException extends BaseException {
    public EntidadeJaExistenteException(String nomeEntidade, String nomePropriedade, String valorPropriedade) {
        super(String.format("%s com %s '%s' já existe.", nomeEntidade, nomePropriedade, valorPropriedade));
    }
    public EntidadeJaExistenteException(String nomeEntidade, String nomePropriedade, Long valorPropriedade) {
        super(String.format("%s com %s '%d' já existe.", nomeEntidade, nomePropriedade, valorPropriedade));
    }
}
