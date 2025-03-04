package org.xsoto.spring.msvc.msvc_patterns_sales.model.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Empaque;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Proteccion;
import java.util.List;
import java.util.Map;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class OrdenResponse {
    private Empaque tipoPaquete;
    private List<Proteccion> tipoProteccion;
    private Double totalPagar;
    private Map detalleDescuento;
    private Map detalleIncremento;
}
