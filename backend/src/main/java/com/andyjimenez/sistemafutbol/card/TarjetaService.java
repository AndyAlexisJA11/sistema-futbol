package com.andyjimenez.sistemafutbol.card;

import com.andyjimenez.sistemafutbol.card.dto.TarjetaRequest;
import com.andyjimenez.sistemafutbol.card.dto.TarjetaResponse;
import com.andyjimenez.sistemafutbol.match.EquipoNoParticipaEnPartidoException;
import com.andyjimenez.sistemafutbol.match.Partido;
import com.andyjimenez.sistemafutbol.match.PartidoService;
import com.andyjimenez.sistemafutbol.player.Jugador;
import com.andyjimenez.sistemafutbol.player.JugadorNoPerteneceEquipoException;
import com.andyjimenez.sistemafutbol.player.JugadorService;
import com.andyjimenez.sistemafutbol.registration.EstadoInscripcion;
import com.andyjimenez.sistemafutbol.registration.InscripcionRepository;
import com.andyjimenez.sistemafutbol.sancion.SancionService;
import com.andyjimenez.sistemafutbol.team.Equipo;
import com.andyjimenez.sistemafutbol.team.EquipoService;
import org.springframework.stereotype.Service;

@Service
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;
    private final PartidoService partidoService;
    private final JugadorService jugadorService;
    private final EquipoService equipoService;
    private final InscripcionRepository inscripcionRepository;
    private final SancionService sancionService;

    public TarjetaService(TarjetaRepository tarjetaRepository,
                          PartidoService partidoService,
                          JugadorService jugadorService,
                          EquipoService equipoService,
                          InscripcionRepository inscripcionRepository,
                          SancionService sancionService) {

        this.tarjetaRepository = tarjetaRepository;
        this.partidoService = partidoService;
        this.jugadorService = jugadorService;
        this.equipoService = equipoService;
        this.inscripcionRepository = inscripcionRepository;
        this.sancionService = sancionService;
    }

    public TarjetaResponse guardar(TarjetaRequest request) {

        Partido partido =
                partidoService.buscarPartidoPorId(request.getIdPartido());

        Jugador jugador =
                jugadorService.buscarJugadorPorId(request.getIdJugador());

        Equipo equipo =
                equipoService.buscarEquipoPorId(request.getIdEquipo());

        if (!partido.getEquipoLocal().getIdEquipo().equals(request.getIdEquipo())
                && !partido.getEquipoVisitante().getIdEquipo().equals(request.getIdEquipo())) {

            throw new EquipoNoParticipaEnPartidoException(
                    request.getIdEquipo(),
                    partido.getJornada().getTorneo().getIdTorneo()
            );
        }

        Long idTorneo =
                partido.getJornada().getTorneo().getIdTorneo();

        boolean jugadorPerteneceEquipo =
                inscripcionRepository
                        .existsByJugador_IdJugadorAndEquipo_IdEquipoAndTorneo_IdTorneoAndEstado(
                                request.getIdJugador(),
                                request.getIdEquipo(),
                                idTorneo,
                                EstadoInscripcion.APROBADA
                        );

        if (!jugadorPerteneceEquipo) {
            throw new JugadorNoPerteneceEquipoException(
                    request.getIdJugador(),
                    request.getIdEquipo()
            );
        }

        if (request.getTipo() == TipoTarjeta.AMARILLA && tarjetaRepository.existsByPartido_IdPartidoAndJugador_IdJugadorAndTipo(
                request.getIdPartido(),
                request.getIdJugador(),
                TipoTarjeta.AMARILLA
        )){
            throw new TarjetaDuplicadaException(
                    request.getIdJugador(),
                    request.getIdPartido()
            );
        }

        Tarjeta tarjeta = new Tarjeta();

        tarjeta.setPartido(partido);
        tarjeta.setJugador(jugador);
        tarjeta.setEquipo(equipo);
        tarjeta.setTipo(request.getTipo());

        Tarjeta tarjetaGuardada =
                tarjetaRepository.save(tarjeta);

        if (request.getTipo() == TipoTarjeta.ROJA_DIRECTA){
            sancionService.crearSancionAutomatica(
                    jugador,
                    partido.getJornada().getTorneo(),
                    2,
                    "Roja directa"
            );
        }

        if (request.getTipo() == TipoTarjeta.ROJA_DOBLE_AMARILLA){
            sancionService.crearSancionAutomatica(
                    jugador,
                    partido.getJornada().getTorneo(),
                    1,
                    "Doble amarilla"
            );
        }

        if (request.getTipo() == TipoTarjeta.AMARILLA){

            long totalAmarillas = tarjetaRepository.countByJugador_IdJugadorAndPartido_Jornada_Torneo_IdTorneoAndTipo(
                    request.getIdJugador(),
                    idTorneo,
                    TipoTarjeta.AMARILLA
            );

            if (totalAmarillas % 4 == 0){
                sancionService.crearSancionAutomatica(
                        jugador,
                        partido.getJornada().getTorneo(),
                        1,
                        "Acumulacion de " + totalAmarillas + " tarjetas amarrillas"
                );
            }
        }

        return convertirAResponse(tarjetaGuardada);
    }

    private TarjetaResponse convertirAResponse(Tarjeta tarjeta) {
        return new TarjetaResponse(
                tarjeta.getIdTarjeta(),
                tarjeta.getPartido().getIdPartido(),
                tarjeta.getJugador().getIdJugador(),
                tarjeta.getEquipo().getIdEquipo(),
                tarjeta.getTipo()
        );
    }
}
