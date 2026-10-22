package com.andyjimenez.sistemafutbol.tournament;

import com.andyjimenez.sistemafutbol.tournament.dto.TorneoRequest;
import com.andyjimenez.sistemafutbol.tournament.dto.TorneoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    private final TorneoService torneoService;

    public TorneoController(TorneoService torneoService){
        this.torneoService = torneoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TorneoResponse guardar(@Valid @RequestBody TorneoRequest request){
        return torneoService.guardar(request);
    }

    @GetMapping
    public List<TorneoResponse> listarTodos(){
        return torneoService.listarTodos();
    }

    @GetMapping("/{id}")
    public TorneoResponse buscarPorId(@PathVariable Long id){
        return torneoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public TorneoResponse actualiza(@PathVariable Long id, @Valid @RequestBody TorneoRequest request){
        return torneoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        torneoService.eliminar(id);
    }
}
