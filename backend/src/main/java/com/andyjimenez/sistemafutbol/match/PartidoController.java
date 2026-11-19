package com.andyjimenez.sistemafutbol.match;

import com.andyjimenez.sistemafutbol.match.dto.PartidoRequest;
import com.andyjimenez.sistemafutbol.match.dto.PartidoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController (PartidoService partidoService){
        this.partidoService = partidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartidoResponse guardar(@Valid @RequestBody PartidoRequest request){
        return partidoService.guardar(request);
    }
}
