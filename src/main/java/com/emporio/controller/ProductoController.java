package com.emporio.controller;

import com.emporio.dto.ProductoRequestDTO;
import com.emporio.dto.ProductoResponseDTO;
import com.emporio.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor
public class ProductoController
{
    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crear(@Valid @RequestBody ProductoRequestDTO dto)
    {
        ProductoResponseDTO creado = productoService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id)
    {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listar()
    {
        return ResponseEntity.ok(productoService.listarActivos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(@PathVariable Long id,
                                                           @Valid @RequestBody ProductoRequestDTO dto)
    {
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id)
    {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorIdCategoria(@PathVariable Long id)
    {
        //Llamamos al servicio que ya nos devuelve los dto listos
        List<ProductoResponseDTO> productos = productoService.filtrarPorCategoria(id);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombre(@RequestParam String Nombre)
    {
        List<ProductoResponseDTO> productos = productoService.buscarPorNombre(Nombre);

        return ResponseEntity.ok(productos);
    }
}
