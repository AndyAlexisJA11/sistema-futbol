package com.andyjimenez.sistemafutbol.tournament;

import com.andyjimenez.sistemafutbol.season.Temporada;
import com.andyjimenez.sistemafutbol.season.TemporadaService;
import com.andyjimenez.sistemafutbol.tournament.dto.TorneoRequest;
import com.andyjimenez.sistemafutbol.tournament.dto.TorneoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorneoService {

    private final TorneoRepository torneoRepository;
    private final TemporadaService temporadaService;

    public TorneoService(TorneoRepository torneoRepository, TemporadaService temporadaService){
        this.torneoRepository = torneoRepository;
        this.temporadaService = temporadaService;
    }

    private TorneoResponse convertirAResponse(Torneo torneo){
        return new TorneoResponse(
                torneo.getIdTorneo(),
                torneo.getNombre(),
                torneo.getFechaInicio(),
                torneo.getEstado(),
                torneo.getJornadaLimiteInscripcion(),
                torneo.getTemporada().getIdTemporada()
        );
    }

    public TorneoResponse guardar(TorneoRequest request){

        if (torneoRepository.existsByTemporada_IdTemporada(request.getIdTemporada())){
            throw new TorneoDuplicadoException(request.getIdTemporada());
        }


        Temporada temporada = temporadaService.buscarTemporadaPorId(request.getIdTemporada());

        Torneo torneo = new Torneo();

        torneo.setNombre(request.getNombre());
        torneo.setFechaInicio(request.getFechaInicio());
        torneo.setEstado(request.getEstado());
        torneo.setJornadaLimiteInscripcion(request.getJornadaLimiteInscripcion());
        torneo.setTemporada(temporada);

        Torneo torneoGuardado = torneoRepository.save(torneo);

        return convertirAResponse(torneoGuardado);
    }

    public List<TorneoResponse> listarTodos(){
        return torneoRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Torneo buscarTorneoPorId(Long id){
        return torneoRepository.findById(id)
                .orElseThrow(() -> new TorneoNoEncontradoException(id));
    }

    public TorneoResponse obtenerPorId(Long id){
        Torneo torneo = buscarTorneoPorId(id);
        return convertirAResponse(torneo);

    }

    public TorneoResponse actualizar(Long id, TorneoRequest request){
        Torneo torneoExistente = buscarTorneoPorId(id);

        if (torneoRepository.existsByTemporada_IdTemporada(request.getIdTemporada()) &&
                !torneoExistente.getTemporada().getIdTemporada().equals(request.getIdTemporada())){

            throw new TorneoDuplicadoException(request.getIdTemporada());
        }

        Temporada temporada = temporadaService.buscarTemporadaPorId(request.getIdTemporada());

        torneoExistente.setNombre(request.getNombre());
        torneoExistente.setFechaInicio(request.getFechaInicio());
        torneoExistente.setEstado(request.getEstado());
        torneoExistente.setJornadaLimiteInscripcion(request.getJornadaLimiteInscripcion());
        torneoExistente.setTemporada(temporada);

        Torneo torneoActualizado = torneoRepository.save(torneoExistente);

        return convertirAResponse(torneoActualizado);
    }

    public void eliminar(Long id){
        Torneo torneo = buscarTorneoPorId(id);
        torneoRepository.delete(torneo);
    }

}
