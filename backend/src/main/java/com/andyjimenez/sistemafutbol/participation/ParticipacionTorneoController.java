package com.andyjimenez.sistemafutbol.participation;

import com.andyjimenez.sistemafutbol.participation.dto.ParticipacionTorneoRequest;
import com.andyjimenez.sistemafutbol.participation.dto.ParticipacionTorneoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<ParticipacionTorneoResponse> listarTodas(){
        return participacionTorneoService.listarTodas();
    }

    @GetMapping("/{id}")
    public ParticipacionTorneoResponse obtenerPorId(@PathVariable Long id){
        return participacionTorneoService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        participacionTorneoService.elimimar(id);
    }

}
