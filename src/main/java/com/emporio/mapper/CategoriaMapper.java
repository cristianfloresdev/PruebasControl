package com.emporio.mapper;

import com.emporio.dto.CategoriaResponseDTO;
import com.emporio.model.Categoria;

public class CategoriaMapper
{
    private CategoriaMapper()
    {
    }

    public static CategoriaResponseDTO toResponseDTO(Categoria categoria)
    {
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .activo(categoria.getActivo())
                .build();
    }
}
