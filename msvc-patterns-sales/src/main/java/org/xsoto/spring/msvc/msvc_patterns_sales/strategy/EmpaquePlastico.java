package org.xsoto.spring.msvc.msvc_patterns_sales.strategy;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;

public class EmpaquePlastico implements EmpaqueStrategy{
    @Override
    public Empaque getTipoEmpaque() {
        return Empaque.PLASTICO;
    }
}
