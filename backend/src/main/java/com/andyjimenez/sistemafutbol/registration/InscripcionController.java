package com.andyjimenez.sistemafutbol.registration;

import com.andyjimenez.sistemafutbol.registration.dto.InscripcionRequest;
import com.andyjimenez.sistemafutbol.registration.dto.InscripcionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService){
        this.inscripcionService = inscripcionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InscripcionResponse guardar(@RequestBody InscripcionRequest request){
        return inscripcionService.guardar(request);
    }

    @GetMapping
    public List<InscripcionResponse> listar(){
        return inscripcionService.listarTodos();
    }

    @GetMapping("/{id}")
    public InscripcionResponse buscarPorId(@PathVariable Long id){
        return inscripcionService.buscarPorId(id);
    }

    @PutMapping("/{id}/enviar")
    public InscripcionResponse enviar(@PathVariable Long id){
        return inscripcionService.enviar(id);
    }

    @PutMapping("/{id}/aprobar")
    public InscripcionResponse aprobar(@PathVariable Long id){
        return inscripcionService.aprobar(id);
    }

    @PutMapping("/{id}/rechazar")
    public InscripcionResponse rechazar(@PathVariable Long id){
        return inscripcionService.rechazar(id);
    }


}
