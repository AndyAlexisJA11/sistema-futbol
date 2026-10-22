package com.andyjimenez.sistemafutbol.season;
import com.andyjimenez.sistemafutbol.season.dto.TemporadaRequest;
import com.andyjimenez.sistemafutbol.season.dto.TemporadaResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/temporadas")
public class TemporadaController {

    private final TemporadaService temporadaService;

    public TemporadaController(TemporadaService temporadaService){
        this.temporadaService = temporadaService;
    }

    @GetMapping
    public List<TemporadaResponse> listarTodas(){
        return temporadaService.listarTodas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TemporadaResponse guardar(@Valid @RequestBody TemporadaRequest request){
        return temporadaService.guardar(request);
    }

    @GetMapping("/{id}")
    public TemporadaResponse buscarTemporadaPorid(@PathVariable Long id){
        return temporadaService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public TemporadaResponse actualizarTemporada(@PathVariable Long id, @Valid @RequestBody TemporadaRequest request){
        return temporadaService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarTemporada(@PathVariable Long id){
        temporadaService.eliminarTemporada(id);
    }
}
