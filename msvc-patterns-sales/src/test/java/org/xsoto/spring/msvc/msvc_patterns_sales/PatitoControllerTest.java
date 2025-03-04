package org.xsoto.spring.msvc.msvc_patterns_sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.xsoto.spring.msvc.msvc_patterns_sales.controller.PatitoController;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Color;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Tamanio;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.Patito;
import org.xsoto.spring.msvc.msvc_patterns_sales.service.PatitoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PatitoControllerTest {

    // Instancia del controlador que vamos a probar
    @InjectMocks
    private PatitoController patitoController;

    @Mock
    private PatitoService patitoService;

    // Inicializamos Mockito antes de cada prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba 1: Verificar el comportamiento de agregar
    @Test
    void testAgregarPatito() {
        Patito patito = Patito.builder()
                .color(Color.AMARILLO)
                .tamanio(Tamanio.MEDIUM)
                .precio(200.00)
                .cantidad(1000)
                .borrado(false).build();

        // configurar el Mock
        Patito expectedPatito = Patito.builder()
                .color(Color.AMARILLO)
                .tamanio(Tamanio.MEDIUM)
                .precio(200.00)
                .cantidad(1000)
                .borrado(false).build();
        when(patitoService.agregar(patito)).thenReturn(expectedPatito);

        // Ejecutamos el método
        ResponseEntity<Patito> response = patitoController.agregar(patito);

        // Verificamos el resultado
        assertEquals(200, response.getStatusCodeValue(), "El código de estado debe ser 200");
        assertEquals(patito, response.getBody(), "El response debe ser 'El objeto Patito!' con el valor ingresado");
    }

    // Prueba 2: Verificar el comportamiento editar
    @Test
    void testEditarPatito() {
        // Preparar datos de prueba
        Patito patito = Patito.builder()
                .cantidad(5000)
                .precio(250.0).build();
        // configurar el mock
        Patito expectedEdit = Patito.builder()
                .color(Color.AMARILLO)
                .tamanio(Tamanio.MEDIUM)
                .precio(250.0)
                .cantidad(5000)
                .borrado(false).build();
        when(patitoService.editar(1,patito)).thenReturn(expectedEdit);

        // Ejecutamos el método
        ResponseEntity<Patito> response = patitoController.editar(1, patito);

        // Verificamos el resultado
        assertEquals(200, response.getStatusCodeValue(), "El código de estado debe ser 200");
        assertNotNull(response.getBody(),"El cuerpo de la respuesta no debe ser NULL");
        assertEquals(expectedEdit, response.getBody(), "El response debe ser 'El objeto Patito', con los valores editados");
    }

    // Prueba 3: Verificar el comportamiento de la lista de objetos
    @Test
    void testListar() {
        // Preparar datos de prueba
        Patito patito = Patito.builder()
                .color(Color.AMARILLO)
                .tamanio(Tamanio.MEDIUM)
                .precio(200.00)
                .cantidad(1000)
                .borrado(false).build();

        // configurar el mock
        List<Patito> expectedList = new ArrayList<>();
        expectedList.add(patito);
        expectedList.add(patito);
        when(patitoService.listar()).thenReturn(expectedList);

        // Ejecutamos el método con un nombre específico
        ResponseEntity<List<Patito>> response = patitoController.lista();

        // Verificamos el resultado
        assertEquals(200, response.getStatusCodeValue(), "El código de estado debe ser 200");
        assertNotNull(response.getBody());
        assertEquals(expectedList, response.getBody(), "El response debe proporcionar la lista de objetos");
    }
}