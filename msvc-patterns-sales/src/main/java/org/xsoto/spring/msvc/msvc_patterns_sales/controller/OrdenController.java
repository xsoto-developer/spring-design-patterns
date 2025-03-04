package org.xsoto.spring.msvc.msvc_patterns_sales.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO.OrdenRequest;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO.OrdenResponse;
import org.xsoto.spring.msvc.msvc_patterns_sales.service.OrdenService;

@RestController
@RequestMapping("/orden")
@Tag(name = "Orden", description = "Operaciones de recepcion de ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

//    public OrdenController(OrdenService ordenService) {
//        this.ordenService = ordenService;
//    }

    @Operation(summary = "Recibe una orden", description = "Devuelve el total a pagar con el detalle de incrementos y/o descuentos")
    @PostMapping
    public ResponseEntity<OrdenResponse> procesarOrden (
            @Parameter(description = "De acuerdo al pais de destino, modo de envio, cantidad y tipo de paquete genera la orden", example = "Pais:EEUU, Modo envio: AIRE, Color: ROJO, Tamanio: XLARGE")
            @Valid @RequestBody OrdenRequest request){
        return ResponseEntity.ok(ordenService.procesarOrden(request));
    }
}
