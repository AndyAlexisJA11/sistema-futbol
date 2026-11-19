package com.andyjimenez.sistemafutbol.match;

import com.andyjimenez.sistemafutbol.match.dto.PartidoRequest;
import com.andyjimenez.sistemafutbol.match.dto.PartidoResponse;
import com.andyjimenez.sistemafutbol.matchday.Jornada;
import com.andyjimenez.sistemafutbol.matchday.JornadaService;
import com.andyjimenez.sistemafutbol.participation.ParticipacionTorneo;
import com.andyjimenez.sistemafutbol.participation.ParticipacionTorneoRepository;
import com.andyjimenez.sistemafutbol.team.Equipo;
import com.andyjimenez.sistemafutbol.team.EquipoService;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {
    private final PartidoRespository partidoRespository;
    private final JornadaService jornadaService;
    private final EquipoService equipoService;
    private final ParticipacionTorneoRepository participacionTorneoRepository;

    public PartidoService(PartidoRespository partidoRespository,
                          JornadaService jornadaService,
                          EquipoService equipoService,
                          ParticipacionTorneoRepository participacionTorneoRepository) {
        this.partidoRespository = partidoRespository;
        this.jornadaService = jornadaService;
        this.equipoService = equipoService;
        this.participacionTorneoRepository = participacionTorneoRepository;
    }

    public PartidoResponse guardar(PartidoRequest request){
        if (request.getIdEquipoLocal().equals(request.getIdEquipoVisitante())){
            throw new PartidoEquiposIgualesException();
        }

        Jornada jornada = jornadaService.buscarJornadaPorId(request.getIdJornada());

        Long idTorneo = jornada.getTorneo().getIdTorneo();

        if (!participacionTorneoRepository.existsByTorneo_IdTorneoAndEquipo_IdEquipo(idTorneo, request.getIdEquipoLocal())){
            throw new EquipoNoParticipaEnPartidoException(
                    request.getIdEquipoLocal(), idTorneo
            );
        }

        if (!participacionTorneoRepository.existsByTorneo_IdTorneoAndEquipo_IdEquipo(idTorneo, request.getIdEquipoVisitante())){
            throw new EquipoNoParticipaEnPartidoException(request.getIdEquipoVisitante(),idTorneo);
        }
        Equipo equipoLocal = equipoService.buscarEquipoPorId(request.getIdEquipoLocal());

        Equipo equipoVisitante = equipoService.buscarEquipoPorId(request.getIdEquipoVisitante());

        Partido partido = new Partido();

        partido.setJornada(jornada);
        partido.setEquipoLocal(equipoLocal);
        partido.setEquipoVisitante(equipoVisitante);
        partido.setFechaHora(request.getFechaHora());
        partido.setEstado(EstadoPartido.PROGRAMADO);
        partido.setGolesLocal(null);
        partido.setGolesVisitante(null);

        Partido partidoGuardado = partidoRespository.save(partido);

        return convertirAResponse(partidoGuardado);
    }

    private PartidoResponse convertirAResponse(Partido partido){
        return new PartidoResponse(
                partido.getIdPartido(),
                partido.getJornada().getIdJornada(),
                partido.getEquipoLocal().getIdEquipo(),
                partido.getEquipoVisitante().getIdEquipo(),
                partido.getFechaHora(),
                partido.getEstado(),
                partido.getGolesLocal(),
                partido.getGolesVisitante());
    }
}
