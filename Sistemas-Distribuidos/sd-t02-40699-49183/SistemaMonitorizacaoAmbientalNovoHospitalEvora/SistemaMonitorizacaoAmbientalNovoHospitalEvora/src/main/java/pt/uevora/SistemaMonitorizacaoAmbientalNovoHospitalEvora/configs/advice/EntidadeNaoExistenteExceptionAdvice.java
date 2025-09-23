package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.configs.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeNaoExistenteException;

@RestControllerAdvice
public class EntidadeNaoExistenteExceptionAdvice {

    @ExceptionHandler(EntidadeNaoExistenteException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EntidadeNaoExistenteException ex) {
        return "{\"Erro\": " + ex.getMessage() + "}";
    }
}
