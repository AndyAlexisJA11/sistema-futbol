package com.andyjimenez.sistemafutbol.team;

import com.andyjimenez.sistemafutbol.team.dto.EquipoRequest;
import com.andyjimenez.sistemafutbol.team.dto.EquipoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController (EquipoService equipoService){
        this.equipoService = equipoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipoResponse guardar(@Valid @RequestBody EquipoRequest request){
        return equipoService.guardar(request);
    }

    @GetMapping
    public List<EquipoResponse> listarTodos(){
        return equipoService.listarTodos();
    }

    @GetMapping("/{id}")
    public EquipoResponse buscarPorId(@PathVariable Long id){
        return equipoService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public EquipoResponse actualizar(@PathVariable Long id,@Valid @RequestBody EquipoRequest request){
        return equipoService.actualizar(id, request );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void elimiar(@PathVariable Long id){
        equipoService.eliminar(id);
    }
}
