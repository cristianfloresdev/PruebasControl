package com.emporio.service;

import com.emporio.dto.CategoriaRequestDTO;
import com.emporio.dto.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService
{
    CategoriaResponseDTO crear(CategoriaRequestDTO dto);

    List<CategoriaResponseDTO> listarActivas();
}
