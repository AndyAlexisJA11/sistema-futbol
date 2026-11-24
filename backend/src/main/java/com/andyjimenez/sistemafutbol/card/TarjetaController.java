package com.andyjimenez.sistemafutbol.card;

import com.andyjimenez.sistemafutbol.card.dto.TarjetaRequest;
import com.andyjimenez.sistemafutbol.card.dto.TarjetaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {

    private final TarjetaService tarjetaService;

    public TarjetaController(TarjetaService tarjetaService){
        this.tarjetaService = tarjetaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarjetaResponse guardar(@Valid @RequestBody TarjetaRequest request){
        return tarjetaService.guardar(request);
    }
}
