package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions;

public class EntidadeNaoExistenteException extends BaseException {
    public EntidadeNaoExistenteException(String nomeEntidade, String nomePropriedade, String valorPropriedade) {
        super(String.format("%s com %s '%s' não existe.", nomeEntidade, nomePropriedade, valorPropriedade));
    }

    public EntidadeNaoExistenteException(String nomeEntidade, String nomePropriedade, Long valorPropriedade) {
        super(String.format("%s com %s '%d' não existe.", nomeEntidade, nomePropriedade, valorPropriedade));
    }
}
