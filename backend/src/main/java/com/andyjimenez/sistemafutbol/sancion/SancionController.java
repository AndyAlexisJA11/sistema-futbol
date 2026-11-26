package com.andyjimenez.sistemafutbol.sancion;

import com.andyjimenez.sistemafutbol.sancion.dto.SancionRequest;
import com.andyjimenez.sistemafutbol.sancion.dto.SancionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sanciones")
public class SancionController {

    private final SancionService sancionService;

    public SancionController(SancionService sancionService){
        this.sancionService = sancionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SancionResponse guardar(@Valid @RequestBody SancionRequest request){
        return sancionService.guardar(request);
    }

    @GetMapping
    public List<SancionResponse> listarTodas(){
        return sancionService.listarTodas();
    }

    @GetMapping("/{id}")
    public SancionResponse buscarPorId(@PathVariable Long id){
        return sancionService.obtenerPorId(id);
    }

    @PutMapping("/{id}/cumplir")
    public SancionResponse marcarCumplida(@PathVariable Long id){
        return sancionService.marcarCumplida(id);
    }

    @PutMapping("/{id}/anular")
    public SancionResponse anular(@PathVariable Long id){
        return sancionService.anular(id);
    }


}
