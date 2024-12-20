package br.com.alysonrodrigo.praticaapitask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(max = 255, message = "O nome não pode exceder 255 caracteres.")
    private String name;
    private String description;
}
