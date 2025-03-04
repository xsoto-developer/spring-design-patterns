package org.xsoto.spring.msvc.msvc_patterns_sales.strategy;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.ModoEnvio;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Pais;

import java.util.HashMap;
import java.util.Map;

public class CostoDescuento extends CostoStrategy{
    @Override
    public  Map<String, Double> calcularCosto(Double costoBase, Integer cantidad, Pais pais, ModoEnvio modoEnvio, Empaque empaque) {
        Double costoParcial = this.calcularCostoBase(cantidad, costoBase);
        Double descuentoCantidad = 0.0;
        Double descuentoEmpaque = 0.0;
        Double descuentoEnvio = 0.0;
        Map<String, Double> detalleDescuentos = new HashMap<>();

        if (cantidad > 100){
            descuentoCantidad = descuentoPorcentaje(costoParcial, 20.0);
            detalleDescuentos.put("Si el pedido es mayor a 100 unidades, aplicar un descuento del 20 % al costo total:", descuentoCantidad);
        }

        if (empaque == Empaque.CARTON){
            descuentoEmpaque = descuentoPorcentaje(costoParcial, 1.0);
            detalleDescuentos.put("Si el paquete es de carton, aplicar un descuento del 1% del costo total:", descuentoEmpaque);
        }

        if (modoEnvio == ModoEnvio.AIRE && cantidad > 1000){
            descuentoEnvio = descuentoPorcentaje(costoParcial, 15.0);
        }

        detalleDescuentos.put("TotalDescuentos",(descuentoCantidad+descuentoEmpaque+descuentoEnvio));
        return detalleDescuentos;
    }

    private Double descuentoPorcentaje(Double costoParcial, Double porcentaje){
        return  (costoParcial * (porcentaje/100));
    }
}
