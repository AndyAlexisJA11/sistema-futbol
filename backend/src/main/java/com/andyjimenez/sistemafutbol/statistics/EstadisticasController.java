package com.andyjimenez.sistemafutbol.statistics;

import com.andyjimenez.sistemafutbol.standings.dto.PosicionResponse;
import com.andyjimenez.sistemafutbol.statistics.dto.GoleadorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/torneos")
public class EstadisticasController {

    private final EstadisticasService estadisticasService;

    public EstadisticasController(EstadisticasService estadisticasService){
        this.estadisticasService = estadisticasService;
    }

    @GetMapping("/{idTorneo}/goleadores")
    public List<GoleadorResponse> obtenerGoleadores(@PathVariable Long idTorneo){
        return estadisticasService.obtenerGoleadores(idTorneo);
    }

    @GetMapping("/{idTorneo}/menos-vencido")
    public PosicionResponse obtenerMenosVencido(@PathVariable Long idTorneo){

        return estadisticasService.obtenerMenosVencido(idTorneo);
    }
}
