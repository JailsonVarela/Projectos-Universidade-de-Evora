package pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.configs.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.uevora.SistemaMonitorizacaoAmbientalNovoHospitalEvora.exceptions.EntidadeJaExistenteException;

@RestControllerAdvice
public class EntidadeJaExistenteExceptionAdvice {

    @ExceptionHandler(EntidadeJaExistenteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String employeeNotFoundHandler(EntidadeJaExistenteException ex) {
        return "{\"Erro\": " + ex.getMessage() + "}";
    }
}
