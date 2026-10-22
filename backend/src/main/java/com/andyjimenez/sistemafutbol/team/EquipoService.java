package com.andyjimenez.sistemafutbol.team;

import com.andyjimenez.sistemafutbol.season.dto.TemporadaRequest;
import com.andyjimenez.sistemafutbol.season.dto.TemporadaResponse;
import com.andyjimenez.sistemafutbol.team.dto.EquipoRequest;
import com.andyjimenez.sistemafutbol.team.dto.EquipoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository){
        this.equipoRepository = equipoRepository;
    }

    public EquipoResponse guardar(EquipoRequest equipoRequest){

        if (equipoRepository.existsByNombre(equipoRequest.getNombre())){
            throw new EquipoDuplicadoException(equipoRequest.getNombre());
        }

        Equipo equipo = new Equipo();

        equipo.setNombre(equipoRequest.getNombre());
        equipo.setNombreCorto(equipoRequest.getNombreCorto());
        equipo.setCancha(equipoRequest.getCancha());
        equipo.setEstado(equipoRequest.getEstado());

        Equipo equipoGuardado = equipoRepository.save(equipo);

        return  convertirAResponse(equipoGuardado);
    }

    public EquipoResponse convertirAResponse(Equipo equipo){
        return new EquipoResponse(
                equipo.getIdEquipo(),
                equipo.getNombre(),
                equipo.getNombreCorto(),
                equipo.getCancha(),
                equipo.getEstado()
        );
    }

    public List<EquipoResponse> listarTodos(){
        return equipoRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Equipo buscarEquipoPorId(Long id){
        return equipoRepository.findById(id)
                .orElseThrow(() -> new EquipoNoEncontradoException(id));
    }

    public EquipoResponse obtenerPorId(Long id){
        Equipo equipo = buscarEquipoPorId(id);
        return convertirAResponse(equipo);
    }

    public EquipoResponse actualizar(Long id, EquipoRequest request){

        Equipo equipoExistente = buscarEquipoPorId(id);

        if (equipoRepository.existsByNombreAndIdEquipoNot(request.getNombre(), id)){
            throw new EquipoDuplicadoException(request.getNombre());
        }

        equipoExistente.setNombre(request.getNombre());
        equipoExistente.setNombreCorto(request.getNombreCorto());
        equipoExistente.setCancha(request.getCancha());
        equipoExistente.setEstado(request.getEstado());

        Equipo equipoActualizado = equipoRepository.save(equipoExistente);

        return convertirAResponse(equipoActualizado);

    }

    public void eliminar(Long id){
        Equipo equipoExistente = buscarEquipoPorId(id);
        equipoRepository.delete(equipoExistente);
    }



}
