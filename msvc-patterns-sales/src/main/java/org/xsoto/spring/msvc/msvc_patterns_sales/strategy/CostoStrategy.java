package org.xsoto.spring.msvc.msvc_patterns_sales.strategy;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.ModoEnvio;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Pais;

import java.util.Map;

public abstract class CostoStrategy {

    Double calcularCostoBase(Integer cantidad, Double precio){
        return cantidad * precio;
    }
    abstract Map<String, Double> calcularCosto(Double costoBase , Integer cantidad, Pais pais, ModoEnvio modoEnvio, Empaque empaque);
}
