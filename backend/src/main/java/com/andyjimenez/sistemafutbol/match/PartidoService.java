package com.andyjimenez.sistemafutbol.match;

import com.andyjimenez.sistemafutbol.correction.CorreccionResultado;
import com.andyjimenez.sistemafutbol.correction.CorreccionResultadoRepository;
import com.andyjimenez.sistemafutbol.correction.dto.CorreccionResultadoRequest;
import com.andyjimenez.sistemafutbol.match.dto.PartidoRequest;
import com.andyjimenez.sistemafutbol.match.dto.PartidoResponse;
import com.andyjimenez.sistemafutbol.matchday.Jornada;
import com.andyjimenez.sistemafutbol.matchday.JornadaService;
import com.andyjimenez.sistemafutbol.participation.ParticipacionTorneoRepository;
import com.andyjimenez.sistemafutbol.sancion.SancionService;
import com.andyjimenez.sistemafutbol.team.Equipo;
import com.andyjimenez.sistemafutbol.team.EquipoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PartidoService {
    private final PartidoRepository partidoRepository;
    private final JornadaService jornadaService;
    private final EquipoService equipoService;
    private final ParticipacionTorneoRepository participacionTorneoRepository;
    private final CorreccionResultadoRepository correccionResultadoRepository;
    private final SancionService sancionService;

    public PartidoService(PartidoRepository partidoRepository,
                          JornadaService jornadaService,
                          EquipoService equipoService,
                          ParticipacionTorneoRepository participacionTorneoRepository,
                          CorreccionResultadoRepository correccionResultadoRepository,
                          SancionService sancionService) {
        this.partidoRepository = partidoRepository;
        this.jornadaService = jornadaService;
        this.equipoService = equipoService;
        this.participacionTorneoRepository = participacionTorneoRepository;
        this.correccionResultadoRepository = correccionResultadoRepository;
        this.sancionService = sancionService;
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

        Partido partidoGuardado = partidoRepository.save(partido);

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

    public List<PartidoResponse> listarTodos(){
        return partidoRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Partido buscarPartidoPorId(Long id){
        return partidoRepository.findById(id).orElseThrow(() -> new PartidoNoEncontradoException(id));
    }

    public PartidoResponse buscarPorId(Long id){
        Partido partido = buscarPartidoPorId(id);
        return convertirAResponse(partido);
    }

    public PartidoResponse registrarResultado(Long id, RestultadoPartidoRequest request){
        Partido partido = buscarPartidoPorId(id);

        if (partido.getEstado() != EstadoPartido.PROGRAMADO){
            throw new EstadoPartidoInvalidoException(
                    "Solo se puede registar en un partido PROGRAMADO"
            );
        }

        partido.setGolesLocal(request.getGolesLocal());
        partido.setGolesVisitante(request.getGolesVisitante());
        partido.setEstado(EstadoPartido.JUGADO);

        Partido partidoActualizado = partidoRepository.save(partido);

        sancionService.procesarSancionesPorPartido(partidoActualizado);

        return convertirAResponse(partidoActualizado);
    }

    public PartidoResponse corregirResultado(Long id, CorreccionResultadoRequest resultadoRequest){
        Partido partido = buscarPartidoPorId(id);

        if (partido.getEstado() != EstadoPartido.JUGADO){
            throw new EstadoPartidoInvalidoException(
                    "Solo se puede corregir el resultado de un partido JUGADO"
            );
        }

        CorreccionResultado correccion = new CorreccionResultado();

        correccion.setPartido(partido);
        correccion.setGolesLocalAnterior(partido.getGolesLocal());
        correccion.setGolesVisitanteAnterior(partido.getGolesVisitante());
        correccion.setGolesLocalNuevo(resultadoRequest.getGolesLocal());
        correccion.setGolesVisitaneNuevo(resultadoRequest.getGolesVisitante());
        correccion.setMotivo(resultadoRequest.getMotivo());
        correccion.setFechaCorreccion(LocalDateTime.now());


        correccionResultadoRepository.save(correccion);

        partido.setGolesLocal(resultadoRequest.getGolesLocal());
        partido.setGolesVisitante(resultadoRequest.getGolesVisitante());

        Partido partidoActualizado = partidoRepository.save(partido);

        return convertirAResponse(partidoActualizado);


    }
}
