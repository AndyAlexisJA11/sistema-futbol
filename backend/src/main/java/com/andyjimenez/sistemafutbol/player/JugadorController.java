package com.andyjimenez.sistemafutbol.player;

import com.andyjimenez.sistemafutbol.player.dto.JugadorRequest;
import com.andyjimenez.sistemafutbol.player.dto.JugadorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService){
        this.jugadorService = jugadorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JugadorResponse guardar(@Valid @RequestBody JugadorRequest request){
        return jugadorService.guardar(request);
    }
}
