package com.andyjimenez.sistemafutbol.player;

import com.andyjimenez.sistemafutbol.player.dto.JugadorRequest;
import com.andyjimenez.sistemafutbol.player.dto.JugadorResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository){
        this.jugadorRepository = jugadorRepository;
    }

    public JugadorResponse guardar(JugadorRequest request){

        if (jugadorRepository.existsByDocumentoIdentificacion(request.getDocumentoIdentificacion())){
            throw new JugadorDuplicadoException(
                    request.getDocumentoIdentificacion()
            );
        }
        Jugador jugador = new Jugador();

        jugador.setNombreCompleto(request.getNombreCompleto());
        jugador.setFechaNacimiento(request.getFechaNacimiento());
        jugador.setLugarNacimiento(request.getLugarNacimiento());
        jugador.setDocumentoIdentificacion(request.getDocumentoIdentificacion());
        jugador.setFoto(request.getFoto());

        Jugador jugadorGuardaddo = jugadorRepository.save(jugador);

        return convertirAResponse(jugadorGuardaddo);
    }

    private JugadorResponse convertirAResponse(Jugador jugador){
        return new JugadorResponse(
        jugador.getIdJugador(),
        jugador.getNombreCompleto(),
        jugador.getFechaNacimiento(),
        jugador.getLugarNacimiento(),
        jugador.getDocumentoIdentificacion(),
        jugador.getFoto());
    }

    public List<JugadorResponse> listarTodos(){
        return jugadorRepository.findAll().
                stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Jugador buscarJugadorPorId(Long id){
        return jugadorRepository.findById(id)
                .orElseThrow(() -> new JugadorNoEncontradoException(id));
    }

    public JugadorResponse buscarPorId(Long id){
        Jugador jugador = buscarJugadorPorId(id);

        return convertirAResponse(jugador);
    }

    public JugadorResponse actualizar(Long id, JugadorRequest request){

        Jugador jugadorExistente = buscarJugadorPorId(id);

        if (jugadorRepository.existsByDocumentoIdentificacionAndIdJugadorNot(request.getDocumentoIdentificacion(), id)){

            throw new JugadorDuplicadoException(
                    request.getDocumentoIdentificacion()
            );
        }

        jugadorExistente.setNombreCompleto(request.getNombreCompleto());
        jugadorExistente.setFechaNacimiento(request.getFechaNacimiento());
        jugadorExistente.setLugarNacimiento(request.getLugarNacimiento());
        jugadorExistente.setDocumentoIdentificacion(request.getDocumentoIdentificacion());
        jugadorExistente.setFoto(request.getFoto());

        Jugador jugadorAcualizado = jugadorRepository.save(jugadorExistente);

        return convertirAResponse(jugadorAcualizado);
    }

    public void elimiar(Long id){
        Jugador jugadorExistente = buscarJugadorPorId(id);

        jugadorRepository.delete(jugadorExistente);
    }


}
