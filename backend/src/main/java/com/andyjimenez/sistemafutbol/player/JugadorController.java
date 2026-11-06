package com.andyjimenez.sistemafutbol.player;

import com.andyjimenez.sistemafutbol.player.dto.JugadorRequest;
import com.andyjimenez.sistemafutbol.player.dto.JugadorResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<JugadorResponse> listarTodos(){
        return jugadorService.listarTodos();
    }

    @GetMapping("/{id}")
    public JugadorResponse buscarPorId(@PathVariable Long id){
        return jugadorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public JugadorResponse actualizar(@PathVariable Long id,@Valid @RequestBody JugadorRequest request){
        return jugadorService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        jugadorService.elimiar(id);
    }


}
