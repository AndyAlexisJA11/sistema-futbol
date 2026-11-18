package com.andyjimenez.sistemafutbol.matchday;

import com.andyjimenez.sistemafutbol.matchday.dto.JornadaRequest;
import com.andyjimenez.sistemafutbol.matchday.dto.JornadaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jornadas")
public class JornadaController {

    private final JornadaService jornadaService;

    public JornadaController(JornadaService jornadaService){
        this.jornadaService = jornadaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JornadaResponse guardar(@Valid @RequestBody JornadaRequest request){
        return jornadaService.guardar(request);
    }

    @GetMapping
    public List<JornadaResponse> listar(){
        return jornadaService.listarTodas();
    }

    @GetMapping("/{id}")
    public JornadaResponse buscarPorId(@PathVariable Long id){
        return jornadaService.buscarPorId(id);
    }


}
