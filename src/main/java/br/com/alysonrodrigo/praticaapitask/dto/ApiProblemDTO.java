package br.com.alysonrodrigo.praticaapitask.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiProblemDTO {

    private int status;
    private String type;
    private String title;
    private String detail;
    private String instance;
    private LocalDateTime timestamp;
}
