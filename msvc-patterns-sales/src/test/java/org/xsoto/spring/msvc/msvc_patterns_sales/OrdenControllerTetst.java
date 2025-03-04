package org.xsoto.spring.msvc.msvc_patterns_sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.xsoto.spring.msvc.msvc_patterns_sales.controller.OrdenController;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.*;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO.OrdenRequest;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO.OrdenResponse;
import org.xsoto.spring.msvc.msvc_patterns_sales.service.OrdenService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class OrdenControllerTetst {

    // Instancia del controlador que vamos a probar
    @InjectMocks
    private OrdenController ordenController;

    @Mock
    private OrdenService ordenService;

    // Inicializamos Mockito antes de cada prueba
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Prueba 1: Verificar el comportamiento con el valor por defecto
    @Test
    void testProcesarOrdenWithValidRequest() {
        // Preparar datos de prueba
        OrdenRequest request = OrdenRequest.builder()
                .color(Color.ROJO)
                .tamanio(Tamanio.XLARGE)
                .cantidad(100)
                .modoEnvio(ModoEnvio.AIRE)
                .pais(Pais.EEUU).build();

        Map<String, Double> detalleDesc = new HashMap<>();
        detalleDesc.put("TotalDescuentos", 0.0);

        Map<String, Double> detalleInc = new HashMap<>();
        detalleInc.put("TotalIncremento", 4150.0);
        detalleInc.put("Si el pais de destino es USA, agregar un 18% del costo total:",900.0);
        detalleInc.put("Si el paquete es de madera, agregar un 5% del costo total:", 250.0);
        detalleInc.put("si el envio es por avion, agregar 30 USD por la cantidad del pedido menos un 15% si el pedido excede las 1000 unidades:", 3000.0);

        // configurar el Mock
        OrdenResponse expectedResponse = OrdenResponse.builder()
                .tipoPaquete(Empaque.MADERA)
                .tipoProteccion(Collections.singletonList(Proteccion.BOLITAS_PLASTOFORM))
                .detalleDescuento(detalleDesc)
                .detalleIncremento(detalleInc)
                .totalPagar(9150.0).build();
        when(ordenService.procesarOrden(request)).thenReturn(expectedResponse);

        // Ejecutar el método
        ResponseEntity<OrdenResponse> response = ordenController.procesarOrden(request);

        // Verificamos el resultado
        assertEquals(200, response.getStatusCodeValue(),"El código de estado debe ser 200");
        assertNotNull(response.getBody(),"El cuerpo de la respuesta no debe ser null");
        assertEquals(expectedResponse.getTotalPagar(), response.getBody().getTotalPagar(),"El total a pagar debe coincidir");
    }

}