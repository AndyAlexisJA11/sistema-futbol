package com.andyjimenez.sistemafutbol.registration;

import com.andyjimenez.sistemafutbol.player.Jugador;
import com.andyjimenez.sistemafutbol.player.JugadorDuplicadoException;
import com.andyjimenez.sistemafutbol.player.JugadorService;
import com.andyjimenez.sistemafutbol.registration.dto.InscripcionRequest;
import com.andyjimenez.sistemafutbol.registration.dto.InscripcionResponse;
import com.andyjimenez.sistemafutbol.team.Equipo;
import com.andyjimenez.sistemafutbol.team.EquipoService;
import com.andyjimenez.sistemafutbol.tournament.Torneo;
import com.andyjimenez.sistemafutbol.tournament.TorneoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final JugadorService jugadorService;
    private final EquipoService equipoService;
    private final TorneoService torneoService;

    public InscripcionService(InscripcionRepository inscripcionRepository, JugadorService jugadorService, EquipoService equipoService, TorneoService torneoService) {
        this.inscripcionRepository = inscripcionRepository;
        this.jugadorService = jugadorService;
        this.equipoService = equipoService;
        this.torneoService = torneoService;
    }

    public InscripcionResponse guardar(InscripcionRequest request){

        if (inscripcionRepository.existsByJugador_IdJugadorAndTorneo_IdTorneo(request.getIdJugador(), request.getIdTorneo())){
            throw new InscripcionDuplicadaException(request.getIdJugador(), request.getIdTorneo());
        }

        Jugador jugador = jugadorService.buscarJugadorPorId(request.getIdJugador());
        Equipo equipo = equipoService.buscarEquipoPorId(request.getIdEquipo());
        Torneo torneo = torneoService.buscarTorneoPorId(request.getIdTorneo());

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setJugador(jugador);
        inscripcion.setEquipo(equipo);
        inscripcion.setTorneo(torneo);
        inscripcion.setEstado(request.getEstado());

        Inscripcion inscripcionGuardada = inscripcionRepository.save(inscripcion);

        return convertirAResponse(inscripcionGuardada);
    }

    private InscripcionResponse convertirAResponse(Inscripcion inscripcion){
        return new InscripcionResponse(
        inscripcion.getIdInscripcion(),
        inscripcion.getJugador().getIdJugador(),
        inscripcion.getEquipo().getIdEquipo(),
        inscripcion.getTorneo().getIdTorneo(),
        inscripcion.getEstado());
    }

    public List<InscripcionResponse> listarTodos(){
        return inscripcionRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Inscripcion buscarInscripcionPorId(Long id){
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new InscripcionNoEncontradaException(id));
    }

    public InscripcionResponse buscarPorId(Long id){
        Inscripcion inscripcionEncontrada = buscarInscripcionPorId(id);

        return convertirAResponse(inscripcionEncontrada);
    }

    public InscripcionResponse enviar(Long id){
        Inscripcion inscripcion = buscarInscripcionPorId(id);

        if (inscripcion.getEstado() != EstadoInscripcion.BORRADOR){
            throw new EstadoInscripcionInvalidoException(
                    "Solo se puede enviar una inscripcion en estado BORRADOR"
            );
        }

        inscripcion.setEstado(EstadoInscripcion.ENVIADA);

        Inscripcion inscripcionActualizada = inscripcionRepository.save(inscripcion);

        return convertirAResponse(inscripcionActualizada);
    }

    public InscripcionResponse aprobar(Long id){
        Inscripcion inscripcion = buscarInscripcionPorId(id);

        if (inscripcion.getEstado() != EstadoInscripcion.ENVIADA){
            throw new EstadoInscripcionInvalidoException(
                    "solo se puede aprobar una inscripcion en estado ENVIADA"
            );
        }

        inscripcion.setEstado(EstadoInscripcion.APROBADA);

        Inscripcion inscripcionActualizada = inscripcionRepository.save(inscripcion);

        return convertirAResponse(inscripcionActualizada);
    }

    public InscripcionResponse rechazar(Long id){
        Inscripcion inscripcion = buscarInscripcionPorId(id);

        if (inscripcion.getEstado() != EstadoInscripcion.ENVIADA){
            throw new EstadoInscripcionInvalidoException(
                    "Solo se puede rechazar una inscripcion en estado ENVIDA"
            );
        }

        inscripcion.setEstado(EstadoInscripcion.RECHAZADA);

        Inscripcion inscripcionActualizada = inscripcionRepository.save(inscripcion);

        return convertirAResponse(inscripcionActualizada);
    }


}
