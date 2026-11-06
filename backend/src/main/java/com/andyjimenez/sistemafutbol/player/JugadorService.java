package com.andyjimenez.sistemafutbol.player;

import com.andyjimenez.sistemafutbol.player.dto.JugadorRequest;
import com.andyjimenez.sistemafutbol.player.dto.JugadorResponse;
import org.springframework.stereotype.Service;

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
}
