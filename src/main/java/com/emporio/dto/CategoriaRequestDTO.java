package com.emporio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaRequestDTO
{
    @NotBlank(message = "El nombre de la categoria es obligatorio")
    private String nombre;

    private String descripcion;
}
