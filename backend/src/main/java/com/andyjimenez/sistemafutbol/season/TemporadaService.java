package com.andyjimenez.sistemafutbol.season;
import com.andyjimenez.sistemafutbol.season.dto.TemporadaRequest;
import com.andyjimenez.sistemafutbol.season.dto.TemporadaResponse;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TemporadaService {

    private final TemporadaRepository temporadaRepository;

    public TemporadaService(TemporadaRepository temporadaRepository){
        this.temporadaRepository = temporadaRepository;
    }

    private TemporadaResponse convertirAResponse(Temporada temporada){
        return new TemporadaResponse(
                temporada.getIdTemporada(),
                temporada.getNombre(),
                temporada.getAnio(),
                temporada.getEstado()
        );
    }

    public List<TemporadaResponse> listarTodas(){
        return temporadaRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public TemporadaResponse guardar(TemporadaRequest request){

        if (temporadaRepository.existsByAnio(request.getAnio())){
            throw new TemporadaDuplicadaException(request.getAnio());
        }

        Temporada temporada = new Temporada();

        temporada.setNombre(request.getNombre());
        temporada.setAnio(request.getAnio());
        temporada.setEstado(request.getEstado());

        Temporada temporadaGuardada = temporadaRepository.save(temporada);


        return convertirAResponse(temporadaGuardada);
    }

    public TemporadaResponse obtenerPorId(Long id){
        Temporada temporada = buscarTemporadaPorId(id);
        return convertirAResponse(temporada);
    }

    public Temporada buscarTemporadaPorId(Long id){
        return temporadaRepository.findById(id)
                .orElseThrow(() -> new TemporaNoEncontradaException(id));
    }

    public TemporadaResponse actualizar(Long id, TemporadaRequest request){

        Temporada temporadaExistente = buscarTemporadaPorId(id);

        if (temporadaRepository.existsByAnioAndIdTemporadaNot(request.getAnio(),
                id )) {

            throw new TemporadaDuplicadaException(request.getAnio());
        }

        temporadaExistente.setNombre(request.getNombre());
        temporadaExistente.setAnio(request.getAnio());
        temporadaExistente.setEstado(request.getEstado());

        Temporada temporadaActualizada = temporadaRepository.save(temporadaExistente);

        return convertirAResponse(temporadaActualizada);
    }


    public void eliminarTemporada(Long id){
        Temporada temporada = buscarTemporadaPorId(id);
        temporadaRepository.delete(temporada);
    }


}
