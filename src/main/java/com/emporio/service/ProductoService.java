package com.emporio.service;

import com.emporio.dto.ProductoRequestDTO;
import com.emporio.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService
{
    ProductoResponseDTO crear(ProductoRequestDTO dto);

    ProductoResponseDTO obtenerPorId(Long id);

    List<ProductoResponseDTO> listarActivos();

    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);

    void eliminar(Long id);
}
