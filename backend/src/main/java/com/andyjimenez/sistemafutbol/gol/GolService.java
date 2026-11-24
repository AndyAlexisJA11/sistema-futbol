package com.andyjimenez.sistemafutbol.gol;

import com.andyjimenez.sistemafutbol.gol.dto.GolRequest;
import com.andyjimenez.sistemafutbol.gol.dto.GolResponse;
import com.andyjimenez.sistemafutbol.match.EquipoNoParticipaEnPartidoException;
import com.andyjimenez.sistemafutbol.match.Partido;
import com.andyjimenez.sistemafutbol.match.PartidoService;
import com.andyjimenez.sistemafutbol.player.Jugador;
import com.andyjimenez.sistemafutbol.player.JugadorNoPerteneceEquipoException;
import com.andyjimenez.sistemafutbol.player.JugadorService;
import com.andyjimenez.sistemafutbol.registration.EstadoInscripcion;
import com.andyjimenez.sistemafutbol.registration.InscripcionRepository;
import com.andyjimenez.sistemafutbol.team.Equipo;
import com.andyjimenez.sistemafutbol.team.EquipoService;
import org.springframework.stereotype.Service;

@Service
public class GolService {

    private final GolRepository golRepository;
    private final PartidoService partidoService;
    private final JugadorService jugadorService;
    private final EquipoService equipoService;
    private final InscripcionRepository inscripcionRepository;

    public GolService(GolRepository golRepository,
                      PartidoService partidoService,
                      JugadorService jugadorService,
                      EquipoService equipoService,
                      InscripcionRepository inscripcionRepository) {
        this.golRepository = golRepository;
        this.partidoService = partidoService;
        this.jugadorService = jugadorService;
        this.equipoService = equipoService;
        this.inscripcionRepository = inscripcionRepository;
    }

    public GolResponse guardar(GolRequest request) {
        Partido partido = partidoService.buscarPartidoPorId(request.getIdEquipo());
        Jugador jugador = jugadorService.buscarJugadorPorId(request.getIdJugador());
        Equipo equipo = equipoService.buscarEquipoPorId(request.getIdEquipo());

        if (!partido.getEquipoLocal().getIdEquipo().equals(request.getIdEquipo()) && !partido.getEquipoVisitante().getIdEquipo().equals(request.getIdEquipo())) {
            throw new EquipoNoParticipaEnPartidoException(
                    request.getIdEquipo(),
                    partido.getJornada().getTorneo().getIdTorneo()
            );
        }

        Long idTorneo = partido.getJornada().getTorneo().getIdTorneo();

        boolean jugadorPerteneceEquipo =
                inscripcionRepository.existsByJugador_IdJugadorAndEquipo_IdEquipoAndTorneo_IdTorneoAndEstado(
                        request.getIdJugador(),
                        request.getIdEquipo(),
                        idTorneo,
                        EstadoInscripcion.APROBADA
                );

        if (!jugadorPerteneceEquipo){
            throw new JugadorNoPerteneceEquipoException(
                    request.getIdJugador(),
                    request.getIdEquipo()
            );
        }

        Gol gol = new Gol();

        gol.setPartido(partido);
        gol.setJugador(jugador);
        gol.setEquipo(equipo);

        Gol golGuardado = golRepository.save(gol);

        return convertirAResponse(golGuardado);
    }

    private GolResponse convertirAResponse(Gol gol){
        return new GolResponse(
                gol.getIdGol(),
                gol.getPartido().getIdPartido(),
                gol.getJugador().getIdJugador(),
                gol.getEquipo().getIdEquipo()
        );
    }

}

