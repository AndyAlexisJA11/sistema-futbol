package com.andyjimenez.sistemafutbol.gol;

import com.andyjimenez.sistemafutbol.gol.dto.GolRequest;
import com.andyjimenez.sistemafutbol.gol.dto.GolResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

@RestController
@RequestMapping("/api/goles")
public class GolController {

    private final GolService golService;

    public GolController(GolService golService){
        this.golService = golService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GolResponse guardar(@Valid @RequestBody GolRequest request){
        return golService.guardar(request);
    }

}
