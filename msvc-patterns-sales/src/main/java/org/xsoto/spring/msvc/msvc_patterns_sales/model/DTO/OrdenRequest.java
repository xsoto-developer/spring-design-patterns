package org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Color;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.ModoEnvio;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Pais;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Tamanio;

@Data
@Builder
@EqualsAndHashCode
public class OrdenRequest {
    @NotNull(message = "Color no puede ser nulo")
    private Color color;
    @NotNull(message = "Tamanio no puede ser nulo")
    private Tamanio tamanio;
    @NotNull(message = "Cantidad no puede ser nulo")
    private Integer cantidad;
    @NotNull(message = "El pais no puede ser nulo")
    private Pais pais;
    @NotNull(message = "El modo de envio no puede ser nulo")
    private ModoEnvio modoEnvio;
//
//    public Color getColor() {
//        return color;
//    }
//
//    public void setColor(Color color) {
//        this.color = color;
//    }
//
//    public Tamanio getTamanio() {
//        return tamanio;
//    }
//
//    public void setTamanio(Tamanio tamanio) {
//        this.tamanio = tamanio;
//    }
//
//    public Integer getCantidad() {
//        return cantidad;
//    }
//
//    public void setCantidad(Integer cantidad) {
//        this.cantidad = cantidad;
//    }
//
//    public Pais getPais() {
//        return pais;
//    }
//
//    public void setPais(Pais pais) {
//        this.pais = pais;
//    }
//
//    public ModoEnvio getModoEnvio() {
//        return modoEnvio;
//    }
//
//    public void setModoEnvio(ModoEnvio modoEnvio) {
//        this.modoEnvio = modoEnvio;
//    }
}
