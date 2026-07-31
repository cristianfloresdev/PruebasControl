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

    List<ProductoResponseDTO> filtrarPorCategoria(Long categoriaId);

    //Asi ignoramos las mayusculas
    List<ProductoResponseDTO> buscarPorNombre(String nombre);
}
