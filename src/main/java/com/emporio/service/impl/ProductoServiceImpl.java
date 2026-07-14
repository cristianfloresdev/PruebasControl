package com.emporio.service.impl;

import com.emporio.dto.ProductoRequestDTO;
import com.emporio.dto.ProductoResponseDTO;
import com.emporio.exception.ResourceNotFoundException;
import com.emporio.mapper.ProductoMapper;
import com.emporio.model.Categoria;
import com.emporio.model.Producto;
import com.emporio.repository.CategoriaRepository;
import com.emporio.repository.ProductoRepository;
import com.emporio.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService
{
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public ProductoResponseDTO crear(ProductoRequestDTO dto)
    {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe la categoria con id " + dto.getCategoriaId()));


        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .categoria(categoria)
                .build();

        Producto guardado = productoRepository.save(producto);
        return ProductoMapper.toResponseDTO(guardado);
    }

    @Override
    public ProductoResponseDTO obtenerPorId(Long id)
    {
        Producto producto = buscarOFallar(id);
        return ProductoMapper.toResponseDTO(producto);
    }

    @Override
    public List<ProductoResponseDTO> listarActivos()
    {
        return productoRepository.findByActivoTrue()
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto)
    {
        Producto producto = buscarOFallar(id);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe la categoria con id " + dto.getCategoriaId()));

        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(categoria);

        Producto actualizado = productoRepository.save(producto);
        return ProductoMapper.toResponseDTO(actualizado);
    }

    @Override
    @Transactional
    public void eliminar(Long id)
    {
        Producto producto = buscarOFallar(id);
        producto.setActivo(false);
        productoRepository.save(producto);
    }

    private Producto buscarOFallar(Long id)
    {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe el producto con id " + id));
    }
}
