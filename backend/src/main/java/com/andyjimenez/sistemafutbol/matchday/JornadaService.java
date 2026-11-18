package com.andyjimenez.sistemafutbol.matchday;

import com.andyjimenez.sistemafutbol.matchday.dto.JornadaRequest;
import com.andyjimenez.sistemafutbol.matchday.dto.JornadaResponse;
import com.andyjimenez.sistemafutbol.tournament.Torneo;
import com.andyjimenez.sistemafutbol.tournament.TorneoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JornadaService {

    private final JornadaRepository jornadaRepository;
    private final TorneoService torneoService;

    public JornadaService(JornadaRepository jornadaRepository, TorneoService torneoService) {
        this.jornadaRepository = jornadaRepository;
        this.torneoService = torneoService;
    }

    public JornadaResponse guardar(JornadaRequest request){

        if (jornadaRepository.existsByTorneo_IdTorneoAndNumero(request.getIdTorneo(), request.getNumero())){
            throw new JornadaDuplicadaException(
                    request.getIdTorneo(),
                    request.getNumero()
            );
        }

        Torneo torneo = torneoService.buscarTorneoPorId(request.getIdTorneo());

        Jornada jornada = new Jornada();

        jornada.setNumero(request.getNumero());
        jornada.setTorneo(torneo);

        Jornada jornadaGuardada = jornadaRepository.save(jornada);

        return convertirAResponse(jornadaGuardada);
    }

    private JornadaResponse convertirAResponse(Jornada jornada){
        return new JornadaResponse(
                jornada.getIdJornada(),
                jornada.getNumero(),
                jornada.getTorneo().getIdTorneo()
        );
    }

    public List<JornadaResponse> listarTodas(){
        return jornadaRepository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public Jornada buscarJornadaPorId(Long id){
        return jornadaRepository.findById(id).orElseThrow(() -> new JornadaNoEncontradaException(id));
    }

    public JornadaResponse buscarPorId(Long id){
        Jornada jornada = buscarJornadaPorId(id);
        return convertirAResponse(jornada);
    }
}
