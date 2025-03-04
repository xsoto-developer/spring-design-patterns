package org.xsoto.spring.msvc.msvc_patterns_sales.service;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.*;
import org.xsoto.spring.msvc.msvc_patterns_sales.factory.Envio;
import org.xsoto.spring.msvc.msvc_patterns_sales.factory.EnvioAire;
import org.xsoto.spring.msvc.msvc_patterns_sales.factory.EnvioMar;
import org.xsoto.spring.msvc.msvc_patterns_sales.factory.EnvioTierra;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO.OrdenRequest;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO.OrdenResponse;
import org.xsoto.spring.msvc.msvc_patterns_sales.strategy.*;
import java.util.List;
import java.util.Map;

//@Slf4j
@Service
public class OrdenService {
    private static final Logger log = LoggerFactory.getLogger(OrdenService.class);

    public OrdenResponse procesarOrden(OrdenRequest request){
        Double precio = 50.0;
        OrdenResponse response = new OrdenResponse();
        //- Tipo de paquete
        EmpaqueStrategy empaqueStrategy = getTipoEmpaque(request.getTamanio());
        Empaque empaque = empaqueStrategy.getTipoEmpaque();
        response.setTipoPaquete(empaque);
        log.info("Se obtiene tipo de empaque", empaque.name());

//        - Tipo de proteccion (bolidas de plastoformo, burbujas, bolitaas absorbente)
        Envio envio = getTipoProteccion(request.getModoEnvio());
        List<Proteccion> listProteccion =  envio.listProteccion(empaque);
        response.setTipoProteccion(listProteccion);
        log.info("Se obtiene Tipo de proteccion ", listProteccion);

//        - Detalle de descuentos
        CostoDescuento costoDescuento = new CostoDescuento();
        Map<String, Double> detalleDesc = costoDescuento.calcularCosto (precio , request.getCantidad(), request.getPais(), request.getModoEnvio(), empaque);
        Double totalDescuentos = detalleDesc.get("TotalDescuentos");
        response.setDetalleDescuento(detalleDesc);
        log.info("Total Descuentos", totalDescuentos);
//        - Detalle de incrementos
        CostoIncremento costoIncremento = new CostoIncremento();
        Map<String, Double>  detalleInc = costoIncremento.calcularCosto(precio , request.getCantidad(), request.getPais(), request.getModoEnvio(), empaque);
        Double totalIncrementos = detalleInc.get("TotalIncremento");
        response.setDetalleIncremento(detalleInc);
        log.info("Total Incremento", totalIncrementos);
//        - Total a apagar
        Double totalMonto = ((request.getCantidad() * precio) +  totalIncrementos ) - totalDescuentos;
        response.setTotalPagar(totalMonto);
        log.info("Total monto a pagar ", totalMonto);
        return response;
    }

    private EmpaqueStrategy getTipoEmpaque(Tamanio tamanio){
        return switch (tamanio){
            case LARGE, XLARGE -> new EmpaqueMadera();
            case MEDIUM -> new EmpaqueCarton();
            case SMALL, XSMALL -> new EmpaquePlastico();
        };
    }

    private Envio getTipoProteccion(ModoEnvio modoEnvio){
        return switch (modoEnvio){
            case AIRE -> new EnvioAire();
            case TIERRA -> new EnvioTierra();
            case MAR -> new EnvioMar();
        };
    }

}
