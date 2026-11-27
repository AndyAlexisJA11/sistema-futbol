package com.andyjimenez.sistemafutbol.statistics;

import com.andyjimenez.sistemafutbol.gol.GolRepository;
import com.andyjimenez.sistemafutbol.standings.TablaPosicionesService;
import com.andyjimenez.sistemafutbol.standings.dto.PosicionResponse;
import com.andyjimenez.sistemafutbol.statistics.dto.GoleadorResponse;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EstadisticasService {

    private final GolRepository golRepository;
    private final TablaPosicionesService tablaPosicionesService;

    public EstadisticasService(GolRepository golRepository,
                               TablaPosicionesService tablaPosicionesService){
        this.golRepository = golRepository;
        this.tablaPosicionesService = tablaPosicionesService;
    }

    public List<GoleadorResponse> obtenerGoleadores(Long idTorneo){
        return golRepository.obtenerGoleadoresPorTorneo(idTorneo);
    }

    public PosicionResponse obtenerMenosVencido(Long idTorneo){

        return tablaPosicionesService.obtenerTabla(idTorneo)
                .stream()
                .min(Comparator.comparing(PosicionResponse::getGolesContra))
                .orElseThrow();
    }


}
