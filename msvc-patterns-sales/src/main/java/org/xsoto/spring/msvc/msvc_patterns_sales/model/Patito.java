package org.xsoto.spring.msvc.msvc_patterns_sales.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.service.annotation.GetExchange;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Color;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Tamanio;

@Entity
@Table(name = "patito")
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Patito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Color no puede ser nulo")
    private Color color;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Tamanio no puede ser nulo")
    private Tamanio tamanio;
    @NotNull(message = "Precio no puede ser nulo")
    private Double precio;
    @NotNull(message = "Cantidad no puede ser nulo")
    private Integer cantidad;
    private Boolean borrado = false;
}
