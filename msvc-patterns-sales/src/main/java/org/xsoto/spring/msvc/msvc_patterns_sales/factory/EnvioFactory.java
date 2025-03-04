package org.xsoto.spring.msvc.msvc_patterns_sales.factory;

import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.ModoEnvio;

public class EnvioFactory {

    public Envio obtenerModoEnvio(ModoEnvio modoEnvio, Empaque empaque){

        return switch (modoEnvio){
            case AIRE -> new EnvioAire();
            case MAR -> new EnvioMar();
            case TIERRA -> new EnvioTierra();
        };
//        if (modoEnvio == ModoEnvio.AIRE)
//            return new EnvioAire();
//        else if  (modoEnvio == ModoEnvio.TIERRA)
//            return new EnvioTierra();
//        else if  (modoEnvio == ModoEnvio.MAR)
//            return new EnvioMar();

    }

}
