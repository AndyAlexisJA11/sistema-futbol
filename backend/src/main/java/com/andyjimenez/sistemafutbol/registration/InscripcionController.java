package com.andyjimenez.sistemafutbol.registration;

import com.andyjimenez.sistemafutbol.registration.dto.InscripcionRequest;
import com.andyjimenez.sistemafutbol.registration.dto.InscripcionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
