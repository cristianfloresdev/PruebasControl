package com.emporio.service.impl;

import com.emporio.dto.CategoriaRequestDTO;
import com.emporio.dto.CategoriaResponseDTO;
import com.emporio.mapper.CategoriaMapper;
import com.emporio.model.Categoria;
import com.emporio.repository.CategoriaRepository;
import com.emporio.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService
{
    private final CategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public CategoriaResponseDTO crear(CategoriaRequestDTO dto)
    {
        Categoria categoria = Categoria.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();

        Categoria guardada = categoriaRepository.save(categoria);
        return CategoriaMapper.toResponseDTO(guardada);
    }

    @Override
    public List<CategoriaResponseDTO> listarActivas()
    {
        return categoriaRepository.findAll()
                .stream()
                .filter(Categoria::getActivo)
                .map(CategoriaMapper::toResponseDTO)
                .toList();
    }
}
