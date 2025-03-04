package org.xsoto.spring.msvc.msvc_patterns_sales.factory;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Proteccion;

import java.util.ArrayList;
import java.util.List;

public class EnvioAire implements Envio {

    @Override
    public List<Proteccion> listProteccion(Empaque empaque) {
        List <Proteccion> list = new ArrayList<>();

        if (empaque == Empaque.CARTON || empaque == Empaque.MADERA){
            list.add(Proteccion.BOLITAS_PLASTOFORM);
        }else if (empaque == Empaque.PLASTICO){
            list.add(Proteccion.BOLITAS_BURBUJA);
        }
        return list;
    }
}
