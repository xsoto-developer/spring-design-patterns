package org.xsoto.spring.msvc.msvc_patterns_sales.strategy;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Proteccion;

public class ProteccionAbsorbentes implements ProteccionStrategy{
    @Override
    public Proteccion proteger() {
        return Proteccion.BOLITAS_ABSORBENTES;
    }
}
