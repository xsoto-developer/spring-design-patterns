package org.xsoto.spring.msvc.msvc_patterns_sales.factory;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Proteccion;

import java.util.List;

public interface Envio {

    List<Proteccion> listProteccion(Empaque empaque);
}
