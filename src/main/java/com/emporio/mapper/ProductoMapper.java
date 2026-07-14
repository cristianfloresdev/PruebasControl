package com.emporio.mapper;

import com.emporio.dto.ProductoResponseDTO;
import com.emporio.model.Producto;

public class ProductoMapper
{
    private ProductoMapper()
    {
    }

    public static ProductoResponseDTO toResponseDTO(Producto producto)
    {
        return ProductoResponseDTO.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .activo(producto.getActivo())
                .categoriaId(producto.getCategoria().getId())
                .categoriaNombre(producto.getCategoria().getDescripcion())
                .build();
    }
}
