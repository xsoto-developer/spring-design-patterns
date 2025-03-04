package org.xsoto.spring.msvc.msvc_patterns_sales.strategy;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.ModoEnvio;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Pais;

import java.util.HashMap;
import java.util.Map;

public class CostoIncremento extends CostoStrategy{

    Map<String, Double> detalleIncrementos = new HashMap<>();

    @Override
    public  Map<String, Double> calcularCosto(Double costoBase, Integer cantidad, Pais pais, ModoEnvio modoEnvio, Empaque empaque ) {
        Double costoParcial = this.calcularCostoBase(cantidad, costoBase);
        Double montoIncEmpaque = incrementoEmpaque(empaque, costoParcial);
        Double montoIncPais = incrementoPais(pais, costoParcial);
        Double montoIncmodoEnvio = incrementoModoEnvio(modoEnvio, cantidad);
        detalleIncrementos.put("TotalIncremento",(montoIncEmpaque+montoIncPais+montoIncmodoEnvio));
        return detalleIncrementos;
    }


    private Double incrementoEmpaque(Empaque empaque, Double costoParcial){
        Double montoIncremento;
        switch (empaque) {
            case MADERA -> {
                montoIncremento = incrementoPorcentaje(costoParcial, 5.0);
                detalleIncrementos.put("Si el paquete es de madera, agregar un 5% del costo total:", montoIncremento);
            }
            case PLASTICO -> {
                montoIncremento = incrementoPorcentaje(costoParcial, 10.0);
                detalleIncrementos.put("Si el paquete es de plastico, agregar un 10% del costo total:", montoIncremento);
            }
            default -> throw new IllegalStateException("Unexpected value: " + empaque);
        }
        return montoIncremento;
    }


    private Double incrementoPais(Pais pais, Double costoParcial){
        Double montoIncremento;
        switch (pais) {
            case EEUU -> {
                montoIncremento = incrementoPorcentaje(costoParcial, 18.0);
                detalleIncrementos.put("Si el pais de destino es USA, agregar un 18% del costo total:", montoIncremento);
            }
            case BOLIVIA -> {
                montoIncremento = incrementoPorcentaje(costoParcial, 13.0);
                detalleIncrementos.put("Si el pais de destino es Bolivia, agregar un 13% del costo total:", montoIncremento);
            }
            case INDIA -> {
                montoIncremento = incrementoPorcentaje(costoParcial, 19.0);
                detalleIncrementos.put("Si el pais de destino es India, agregar un 19% del costo total:", montoIncremento);
            }
            default -> {
                montoIncremento = incrementoPorcentaje(costoParcial, 15.0);
                detalleIncrementos.put("Para culaquier otro pais, agregar un pais un 15% del costo total:", montoIncremento);
            }
        }
        return  montoIncremento;
    }

    private Double incrementoModoEnvio(ModoEnvio modoEnvio, Integer cantidad){
        Double montoIncremento;
        switch (modoEnvio) {
            case MAR -> {
                montoIncremento = incrementoUSD(400.0);
                detalleIncrementos.put("Si el envio es por mar, agregar 400 USD:", montoIncremento);
            }
            case TIERRA -> {
                montoIncremento = incrementoUSD(10.0 * cantidad);
                detalleIncrementos.put("Si el envio es por tierra, agregar 10 USD por la cantidad del pedido:", montoIncremento);
            }
            case AIRE -> {
                montoIncremento = incrementoUSD(30.0 * cantidad);
                detalleIncrementos.put("si el envio es por avion, agregar 30 USD por la cantidad del pedido menos un 15% si el pedido excede las 1000 unidades:", montoIncremento);
            }
            default -> throw new IllegalStateException("Unexpected value: " + modoEnvio);
        }
        return montoIncremento;
    }

    private Double incrementoPorcentaje(Double costoParcial, Double porcentaje){
        return (costoParcial * (porcentaje/100));
    }

    private Double incrementoUSD( Double USD){
        return USD ;
    }


}
