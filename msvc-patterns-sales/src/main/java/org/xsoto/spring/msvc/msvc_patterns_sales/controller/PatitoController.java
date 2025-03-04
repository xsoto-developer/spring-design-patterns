package org.xsoto.spring.msvc.msvc_patterns_sales.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xsoto.spring.msvc.msvc_patterns_sales.model.Patito;
import org.xsoto.spring.msvc.msvc_patterns_sales.service.PatitoService;

import java.util.List;

@RestController
@RequestMapping("/patito")
@Tag(name = "Producto", description = "Producto = Patito que representa el producto generico")
public class PatitoController {

    //@Autowired
    private final PatitoService patitoService;

    public PatitoController(PatitoService patitoService) {
        this.patitoService = patitoService;
    }

    @Operation(summary = "Lista de productos", description = "Lista los patitos que no fueron eliminados logicamente")
    @GetMapping
    public ResponseEntity<List<Patito>> lista (){
        return ResponseEntity.ok(patitoService.listar());
    }

    @Operation(summary = "Agregar producto con color, tamaño y cantidad",
               description = "Si en la BD ya existe un patito con el mismo precio, color y tamaño que el nuevo patito que se quiere agregar, \n" +
                    ", se mantiene el patito existente y solo se suma las cantidades (actual + nueva)")
    @PostMapping
    public ResponseEntity<Patito> agregar(@Parameter(description = "Agregar patito, colectando color, tamaño y cantidad", example = "ROJO,XLARGE,1000")
                          @RequestBody Patito patito) {
        return ResponseEntity.ok(patitoService.agregar(patito));
    }

    @Operation(summary = "Editar patitos", description = "Solo se puede editar cantidad y precio. Color y tamaño son de lectura")
    @PutMapping("/{id}")
    public ResponseEntity<Patito> editar(@Parameter(description = "cantidad y precio", example = "500, 58.50")
            @PathVariable Integer id, @RequestBody Patito patito){
        return ResponseEntity.ok(patitoService.editar(id,patito));
    }

    @Operation(summary = "Borrar patitos" , description = "El borrado de los patitos ocurre desde el listado de patitos")
    @DeleteMapping("/{id}")
    public void borrar (@Parameter(description = "Implementar borrado logico, eso significa que no se borra el registro de la base de datos, solo se stablece el valor de Borrado como true",
                                   example = "borrado:TRUE")
                        @PathVariable Integer id){
        patitoService.borrar(id);
    }

}
