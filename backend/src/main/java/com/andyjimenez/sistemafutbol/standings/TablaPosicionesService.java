package com.andyjimenez.sistemafutbol.standings;

import com.andyjimenez.sistemafutbol.match.EstadoPartido;
import com.andyjimenez.sistemafutbol.match.Partido;
import com.andyjimenez.sistemafutbol.match.PartidoRepository;
import com.andyjimenez.sistemafutbol.participation.ParticipacionTorneo;
import com.andyjimenez.sistemafutbol.participation.ParticipacionTorneoRepository;
import com.andyjimenez.sistemafutbol.standings.dto.PosicionResponse;
import com.andyjimenez.sistemafutbol.team.Equipo;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TablaPosicionesService {

    private final ParticipacionTorneoRepository participacionTorneoRepository;
    private final PartidoRepository partidoRepository;

    public TablaPosicionesService(ParticipacionTorneoRepository participacionTorneoRepository,
                                  PartidoRepository partidoRepository){
        this.participacionTorneoRepository = participacionTorneoRepository;
        this.partidoRepository = partidoRepository;
    }

    public List<PosicionResponse> obtenerTabla(Long idTorneo){
        List<ParticipacionTorneo> participaciones = participacionTorneoRepository.findByTorneo_IdTorneo(idTorneo);

        List<Partido> partidosJugados = partidoRepository.findByJornada_Torneo_IdTorneoAndEstado(idTorneo, EstadoPartido.JUGADO);

        Map<Long, PosicionTemporal> tabla = new HashMap<>();

        for (ParticipacionTorneo participacion : participaciones){

            Equipo equipo = participacion.getEquipo();

            tabla.put(
                    equipo.getIdEquipo(),
                    new PosicionTemporal(
                            equipo.getIdEquipo(),
                            equipo.getNombre()
                    )
            );
        }

        for (Partido partido : partidosJugados){
            PosicionTemporal local = tabla.get(partido.getEquipoLocal().getIdEquipo());

            PosicionTemporal visitante = tabla.get(partido.getEquipoVisitante().getIdEquipo());

            int golesLocal = partido.getGolesLocal();
            int golesVisitante = partido.getGolesVisitante();

            local.partidosJugados++;
            visitante.partidosJugados++;

            local.golesFavor += golesLocal;
            local.golesContra += golesVisitante;

            visitante.golesFavor += golesVisitante;
            visitante.golesContra += golesLocal;

            if (golesLocal > golesVisitante){
                local.ganados++;
                local.puntos += 3;

                visitante.perdidos++;
            }else if (golesLocal < golesVisitante){

                visitante.ganados++;
                visitante.puntos += 3;

                local.perdidos++;
            }else {

                local.empatados++;
                visitante.empatados++;

                local.puntos++;
                visitante.puntos++;
            }
        }

        return tabla.values()
                .stream()
                .map(posicion -> new PosicionResponse(
                        posicion.idEquipo,
                        posicion.nombreEquipo,
                        posicion.partidosJugados,
                        posicion.ganados,
                        posicion.empatados,
                        posicion.perdidos,
                        posicion.golesFavor,
                        posicion.golesContra,
                        posicion.golesFavor - posicion.golesContra,
                        posicion.puntos
                ))
                .sorted(
                        Comparator.comparing(PosicionResponse::getPuntos)
                                .reversed()
                                .thenComparing(
                                        PosicionResponse::getDiferenciaGoles,
                                        Comparator.reverseOrder()
                                )
                                .thenComparing(
                                        PosicionResponse::getGolesFavor,
                                        Comparator.reverseOrder()
                                )
                                .thenComparing(
                                        PosicionResponse::getGolesContra
                                )
                )
                .toList();
    }

    private static class PosicionTemporal{
        private Long idEquipo;
        private String nombreEquipo;

        private int partidosJugados;
        private int ganados;
        private int empatados;
        private int perdidos;
        private int golesFavor;
        private int golesContra;
        private int puntos;

        public PosicionTemporal(Long idEquipo, String nombreEquipo){
            this.idEquipo = idEquipo;
            this.nombreEquipo = nombreEquipo;
        }
    }

}
