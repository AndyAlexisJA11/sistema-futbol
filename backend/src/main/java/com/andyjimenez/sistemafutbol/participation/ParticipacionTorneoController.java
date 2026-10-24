package com.andyjimenez.sistemafutbol.participation;

import com.andyjimenez.sistemafutbol.participation.dto.ParticipacionTorneoRequest;
import com.andyjimenez.sistemafutbol.participation.dto.ParticipacionTorneoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/participaciones")
public class ParticipacionTorneoController {

    private final ParticipacionTorneoService participacionTorneoService;

    public ParticipacionTorneoController (ParticipacionTorneoService participacionTorneoService) {
        this.participacionTorneoService = participacionTorneoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipacionTorneoResponse guardar(@Valid @RequestBody ParticipacionTorneoRequest request){
        return participacionTorneoService.guardar(request);
    }

}
