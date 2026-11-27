package com.andyjimenez.sistemafutbol.standings;

import com.andyjimenez.sistemafutbol.standings.dto.PosicionResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class TablaPosicionesController {

    private final TablaPosicionesService tablaPosicionesService;

    public TablaPosicionesController(TablaPosicionesService tablaPosicionesService){
        this.tablaPosicionesService = tablaPosicionesService;
    }

    @GetMapping("/{idTorneo}/tabla")
    public List<PosicionResponse> obtenerTabla(@PathVariable Long idTorneo){
        return tablaPosicionesService.obtenerTabla(idTorneo);
    }

}
