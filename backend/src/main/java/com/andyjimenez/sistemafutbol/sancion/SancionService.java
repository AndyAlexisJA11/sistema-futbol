package com.andyjimenez.sistemafutbol.sancion;

import com.andyjimenez.sistemafutbol.match.Partido;
import com.andyjimenez.sistemafutbol.player.Jugador;
import com.andyjimenez.sistemafutbol.player.JugadorService;
import com.andyjimenez.sistemafutbol.registration.EstadoInscripcion;
import com.andyjimenez.sistemafutbol.registration.Inscripcion;
import com.andyjimenez.sistemafutbol.registration.InscripcionRepository;
import com.andyjimenez.sistemafutbol.sancion.dto.SancionRequest;
import com.andyjimenez.sistemafutbol.sancion.dto.SancionResponse;
import com.andyjimenez.sistemafutbol.tournament.Torneo;
import com.andyjimenez.sistemafutbol.tournament.TorneoService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SancionService {

    private final SancionRepository sancionRepository;
    private final JugadorService jugadorService;
    private final TorneoService torneoService;
    private final InscripcionRepository inscripcionRepository;

    public SancionService(SancionRepository sancionRepository,
                          JugadorService jugadorService,
                          TorneoService torneoService,
                          InscripcionRepository inscripcionRepository){
        this.sancionRepository = sancionRepository;
        this.jugadorService = jugadorService;
        this.torneoService = torneoService;
        this.inscripcionRepository = inscripcionRepository;
    }

    public SancionResponse guardar(SancionRequest request){
        Jugador jugador = jugadorService.buscarJugadorPorId(request.getIdJugador());

        Torneo torneo = torneoService.buscarTorneoPorId(request.getIdTorneo());

        Sancion sancion = new Sancion();

        sancion.setJugador(jugador);
        sancion.setTorneo(torneo);
        sancion.setPartidosSuspension(request.getPartidosSuspension());

        sancion.setPartidosPendientes(
                request.getPartidosSuspension()
        );

        sancion.setMotivo(request.getMotivo());
        sancion.setEstado(EstadoSancion.ACTIVA);

        Sancion sancionGuardada = sancionRepository.save(sancion);

        return convertirAResponse(sancionGuardada);
    }

    private SancionResponse convertirAResponse(Sancion sancion){
        return new SancionResponse(
                sancion.getIdSancion(),
                sancion.getJugador().getIdJugador(),
                sancion.getTorneo().getIdTorneo(),
                sancion.getPartidosSuspension(),
                sancion.getPartidosPendientes(),
                sancion.getMotivo(),
                sancion.getEstado()
        );
    }

    public Sancion crearSancionAutomatica(Jugador jugador,
                                          Torneo torneo,
                                          Partido partidoOrigen,
                                          Integer partidosSuspension,
                                          String motivo){
        Sancion sancion = new Sancion();

        sancion.setJugador(jugador);
        sancion.setTorneo(torneo);
        sancion.setPartidoOrigen(partidoOrigen);
        sancion.setPartidosSuspension(partidosSuspension);
        sancion.setPartidosPendientes(partidosSuspension);
        sancion.setMotivo(motivo);
        sancion.setEstado(EstadoSancion.ACTIVA);

        return sancionRepository.save(sancion);
    }

    public List<SancionResponse> listarTodas(){
        return sancionRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Sancion buscarSancionPorId(Long id){
        return sancionRepository.findById(id)
                .orElseThrow(() -> new SancionNoEncontradaException(id));
    }

    public SancionResponse obtenerPorId(Long id){
        Sancion sancion = buscarSancionPorId(id);
        return convertirAResponse(sancion);
    }

    public SancionResponse marcarCumplida(Long id){

        Sancion sancion = buscarSancionPorId(id);

        if (sancion.getEstado() != EstadoSancion.ACTIVA){
            throw new EstadoSancionInvalidoException(
                    "Solo una sancion ACTIVA puede marcarse como cumplida"
            );
        }

        sancion.setEstado(EstadoSancion.CUMPLIDA);

        Sancion sancionActualizada = sancionRepository.save(sancion);

        return convertirAResponse(sancionActualizada);
    }

    public SancionResponse anular(Long id){

        Sancion sancion = buscarSancionPorId(id);

        if (sancion.getEstado() != EstadoSancion.ACTIVA){
            throw new EstadoSancionInvalidoException(
                    "Solo una sancion ACTIVA puede anularse"
            );
        }

        sancion.setEstado(EstadoSancion.ANULADA);

        Sancion sancionActualizada = sancionRepository.save(sancion);

        return convertirAResponse(sancionActualizada);
    }

    public void procesarSancionesPorPartido(Partido partido){
        Long idTorneo = partido.getJornada().getTorneo().getIdTorneo();

        List<Sancion> sancionesActivas = sancionRepository.findByTorneo_IdTorneoAndEstadoOrderByIdSancionAsc(
                idTorneo,
                EstadoSancion.ACTIVA
        );

        Set<Long> jugadoresProcesados = new HashSet<>();

        for (Sancion sancion : sancionesActivas){
            Long idJugador = sancion.getJugador().getIdJugador();

            if (jugadoresProcesados.contains(idJugador)){
                continue;
            }

            if (sancion.getPartidoOrigen() != null && sancion.getPartidoOrigen().getIdPartido().equals(partido.getIdPartido())){
                continue;
            }

            Optional<Inscripcion> inscripcion = inscripcionRepository.findByJugador_IdJugadorAndTorneo_IdTorneoAndEstado(
                    idJugador,
                    idTorneo,
                    EstadoInscripcion.APROBADA
            );

            if (inscripcion.isEmpty()){
                continue;
            }

            Long idEquipoJugador = inscripcion.get().getEquipo().getIdEquipo();

            boolean equipoJugo = partido.getEquipoLocal().getIdEquipo().equals(idEquipoJugador)
                    || partido.getEquipoVisitante().getIdEquipo().equals(idEquipoJugador);

            if (!equipoJugo){
                continue;
            }

            int pendientes = sancion.getPartidosPendientes() - 1;

            sancion.setPartidosPendientes(pendientes);

            if (pendientes == 0){
                sancion.setEstado(EstadoSancion.CUMPLIDA);
            }

            sancionRepository.save(sancion);

            jugadoresProcesados.add(idJugador);
        }


    }
}
