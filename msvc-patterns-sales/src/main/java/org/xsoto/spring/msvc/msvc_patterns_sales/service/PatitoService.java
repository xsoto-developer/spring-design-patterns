package org.xsoto.spring.msvc.msvc_patterns_sales.service;

import org.springframework.stereotype.Service;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Color;
import org.xsoto.spring.msvc.msvc_patterns_sales.enums.Tamanio;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.Patito;
import org.xsoto.spring.msvc.msvc_patterns_sales.repository.IPatitoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatitoService {

//    @Autowired
//    private IPatitoRepository patitoRepository;

    private final IPatitoRepository patitoRepository;

    public PatitoService(IPatitoRepository patitoRepository) {
        this.patitoRepository = patitoRepository;
    }

    public List<Patito> listar (){
        return patitoRepository.findAllByBorradoFalseOrderByCantidadDesc();
    }

    public Patito agregar (Patito patito){
//        Optional<Patito> optionalPatito = patitoRepository.findByColorAndTamanioAndPrecio(Color.valueOf(patito.getColor()),
//                                                                                          Tamanio.valueOf(patito.getTamanio()),
//                                                                                          patito.getPrecio());
        Optional<Patito> optionalPatito = patitoRepository.findByColorAndTamanioAndPrecio(patito.getColor(),
                patito.getTamanio(),
                patito.getPrecio());
        if(optionalPatito.isPresent()) {
            Patito patitoActual = optionalPatito.get();
            patitoActual.setCantidad(patitoActual.getCantidad() + patito.getCantidad());
            return patitoRepository.save(patitoActual);
        }else{
            return patitoRepository.save(patito);
        }
    }


    public Patito editar(Integer id, Patito patitoActualizado){
        Patito patitoActual = patitoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Patito no encontrado"));
        patitoActual.setPrecio(patitoActualizado.getPrecio());
        patitoActual.setCantidad(patitoActualizado.getCantidad());
        return patitoRepository.save(patitoActual);
    }

    public void borrar (Integer id){
        Patito patitoActual = patitoRepository.findById(id)
                                              .orElseThrow(()-> new RuntimeException("Patito no encontrado"));
            patitoActual.setBorrado(true);
            patitoRepository.save(patitoActual);
    }

}
