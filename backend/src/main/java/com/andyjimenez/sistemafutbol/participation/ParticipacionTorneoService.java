package com.andyjimenez.sistemafutbol.participation;

import com.andyjimenez.sistemafutbol.participation.dto.ParticipacionTorneoRequest;
import com.andyjimenez.sistemafutbol.participation.dto.ParticipacionTorneoResponse;
import com.andyjimenez.sistemafutbol.team.Equipo;
import com.andyjimenez.sistemafutbol.team.EquipoService;
import com.andyjimenez.sistemafutbol.tournament.Torneo;
import com.andyjimenez.sistemafutbol.tournament.TorneoService;
import org.springframework.stereotype.Service;

@Service
public class ParticipacionTorneoService {

    private final ParticipacionTorneoRepository participacionTorneoRepository;
    private final TorneoService torneoService;
    private final EquipoService equipoService;

    public ParticipacionTorneoService (ParticipacionTorneoRepository participacionTorneoRepository,
                                       TorneoService torneoService,
                                       EquipoService equipoService){
        this.participacionTorneoRepository = participacionTorneoRepository;
        this.torneoService = torneoService;
        this.equipoService = equipoService;
    }


    public ParticipacionTorneoResponse guardar(ParticipacionTorneoRequest request){

        if (participacionTorneoRepository.existsByTorneo_IdTorneoAndEquipo_IdEquipo(
                request.getIdTorneo(),
                request.getIdEquipo())){
            throw new ParticipacionDuplicadaException(
                    request.getIdTorneo(),
                    request.getIdEquipo()
            );
        }

        Torneo torneo = torneoService.buscarTorneoPorId(request.getIdTorneo());

        Equipo equipo = equipoService.buscarEquipoPorId(request.getIdEquipo());

        ParticipacionTorneo participacionTorneo = new ParticipacionTorneo();

        participacionTorneo.setTorneo(torneo);
        participacionTorneo.setEquipo(equipo);

        ParticipacionTorneo participacionGuardada = participacionTorneoRepository.save(participacionTorneo);

        return convertirAResponse(participacionGuardada);
    }

    private ParticipacionTorneoResponse convertirAResponse(ParticipacionTorneo participacion){

        return new ParticipacionTorneoResponse(
                participacion.getIdParticipacion(),
                participacion.getTorneo().getIdTorneo(),
                participacion.getEquipo().getIdEquipo());
    }


}
