package com.emporio.service;

import com.emporio.dto.ProductoRequestDTO;
import com.emporio.dto.ProductoResponseDTO;
import com.emporio.exception.ResourceNotFoundException;
import com.emporio.model.Categoria;
import com.emporio.model.Producto;
import com.emporio.repository.CategoriaRepository;
import com.emporio.repository.ProductoRepository;
import com.emporio.service.impl.ProductoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = Categoria.builder().id(1L).nombre("Electronica").activo(true).build();
    }

    @Test
    void crear_debeGuardarProductoCuandoCategoriaExiste() {
        ProductoRequestDTO request = new ProductoRequestDTO(
                "Mouse inalambrico", "Mouse ergonomico", BigDecimal.valueOf(299.90), 15, 1L);

        Producto productoGuardado = Producto.builder()
                .id(10L)
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .precio(request.getPrecio())
                .stock(request.getStock())
                .activo(true)
                .categoria(categoria)
                .build();

        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoGuardado);

        ProductoResponseDTO resultado = productoService.crear(request);

        assertThat(resultado.getId()).isEqualTo(10L);
        assertThat(resultado.getCategoriaNombre()).isEqualTo("Electronica");
    }

    @Test
    void crear_debeLanzarExcepcionCuandoCategoriaNoExiste() {
        ProductoRequestDTO request = new ProductoRequestDTO(
                "Teclado", "Teclado mecanico", BigDecimal.valueOf(899.0), 5, 99L);

        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.crear(request))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }
}
