package org.xsoto.spring.msvc.msvc_patterns_sales.strategy;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Proteccion;

public class ProteccionBurbuja implements  ProteccionStrategy{
    @Override
    public Proteccion proteger() {
        return Proteccion.BOLITAS_BURBUJA;
    }
}
