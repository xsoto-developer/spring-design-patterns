package org.xsoto.spring.msvc.msvc_patterns_sales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Color;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Tamanio;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.Patito;

import java.util.List;
import java.util.Optional;

public interface IPatitoRepository extends JpaRepository<Patito,Integer> {

    List<Patito> findAllByBorradoFalseOrderByCantidadDesc();
//    Optional<Patito> findByColorAndTamanioAndPrecio(Color color, Tamanio tamanio, Double precio);
    Optional<Patito> findByColorAndTamanioAndPrecio(Color color, Tamanio tamanio, Double precio);

}
