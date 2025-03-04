package org.xsoto.spring.msvc.msvc_patterns_sales.factory;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Proteccion;

import java.util.ArrayList;
import java.util.List;

public class EnvioTierra implements Envio {
    @Override
    public List<Proteccion> listProteccion(Empaque empaque) {
        List <Proteccion> list = new ArrayList<>();
        if (empaque == Empaque.MADERA || empaque == Empaque.CARTON || empaque == Empaque.PLASTICO){
            list.add(Proteccion.BOLITAS_PLASTOFORM);
        }
        return list;
    }
}
