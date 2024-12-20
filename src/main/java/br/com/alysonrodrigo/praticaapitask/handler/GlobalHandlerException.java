package br.com.alysonrodrigo.praticaapitask.handler;

import br.com.alysonrodrigo.praticaapitask.dto.ApiProblemDTO;
import br.com.alysonrodrigo.praticaapitask.exception.NegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerException {

    private static final String TYPE_VALIDATION_ERROR = "https://api.cookiesoft.cloud/validation-error";
    private static final String TYPE_BUSINESS_ERROR = "https://api.cookiesoft.cloud/business-error";
    private static final String TYPE_GENERIC_ERROR = "https://api.cookiesoft.cloud/generic-error";


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiProblemDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String detail = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    return fieldName + ": " + errorMessage;
                })
                .collect(Collectors.joining(", "));

        ApiProblemDTO problem = ApiProblemDTO.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                                .type(TYPE_VALIDATION_ERROR)
                                .title("Erro de validação")
                                .detail(detail)
                                .timestamp(LocalDateTime.now())
                                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ApiProblemDTO> handleNegocioException(NegocioException ex) {
        ApiProblemDTO problem = ApiProblemDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type(TYPE_VALIDATION_ERROR)
                .title("Erro de negócio")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiProblemDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiProblemDTO problem = ApiProblemDTO.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .type(TYPE_VALIDATION_ERROR)
                .title("Recurso não encontrado")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiProblemDTO> handleGenericException(Exception ex) {
        ApiProblemDTO problem = ApiProblemDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .type(TYPE_VALIDATION_ERROR)
                .title("Erro Genérico")
                .detail(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }
}
