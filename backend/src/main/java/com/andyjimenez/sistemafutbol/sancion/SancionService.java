package com.andyjimenez.sistemafutbol.sancion;

import com.andyjimenez.sistemafutbol.player.Jugador;
import com.andyjimenez.sistemafutbol.player.JugadorService;
import com.andyjimenez.sistemafutbol.sancion.dto.SancionRequest;
import com.andyjimenez.sistemafutbol.sancion.dto.SancionResponse;
import com.andyjimenez.sistemafutbol.tournament.Torneo;
import com.andyjimenez.sistemafutbol.tournament.TorneoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SancionService {

    private final SancionRepository sancionRepository;
    private final JugadorService jugadorService;
    private final TorneoService torneoService;

    public SancionService(SancionRepository sancionRepository,
                          JugadorService jugadorService,
                          TorneoService torneoService){
        this.sancionRepository = sancionRepository;
        this.jugadorService = jugadorService;
        this.torneoService = torneoService;
    }

    public SancionResponse guardar(SancionRequest request){
        Jugador jugador = jugadorService.buscarJugadorPorId(request.getIdJugador());

        Torneo torneo = torneoService.buscarTorneoPorId(request.getIdTorneo());

        Sancion sancion = new Sancion();

        sancion.setJugador(jugador);
        sancion.setTorneo(torneo);
        sancion.setPartidosSuspension(request.getPartidosSuspension());
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
                sancion.getMotivo(),
                sancion.getEstado()
        );
    }

    public Sancion crearSancionAutomatica(Jugador jugador,
                                          Torneo torneo,
                                          Integer partidosSuspension,
                                          String motivo){
        Sancion sancion = new Sancion();

        sancion.setJugador(jugador);
        sancion.setTorneo(torneo);
        sancion.setPartidosSuspension(partidosSuspension);
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
}
