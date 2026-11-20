package com.andyjimenez.sistemafutbol.match;

import com.andyjimenez.sistemafutbol.match.dto.PartidoRequest;
import com.andyjimenez.sistemafutbol.match.dto.PartidoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<PartidoResponse> listarTodos(){
        return partidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public PartidoResponse buscarPorId(@PathVariable Long id){
        return partidoService.buscarPorId(id);
    }

    @PutMapping("/{id}/resultado")
    public PartidoResponse registrarResultado(@PathVariable Long id, @Valid @RequestBody RestultadoPartidoRequest request){
        return partidoService.registrarResultado(id, request);
    }
}
