package happy.paws.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledFuture;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import happy.paws.model.Pet;
import happy.paws.model.RitmoCardiaco;
import happy.paws.repositories.PetRepository;
import happy.paws.repositories.RitmoCardiacoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RitmoCardiacoService {

    private final RitmoCardiacoRepository ritmoRepo;
    private final PetRepository petRepo;
    private final HeartRateSimulatorService simulator;

    public RitmoCardiacoService(RitmoCardiacoRepository ritmoRepo,
                                PetRepository petRepo,
                                HeartRateSimulatorService simulator) {
        this.ritmoRepo  = ritmoRepo;
        this.petRepo    = petRepo;
        this.simulator  = simulator;
    }

    public RitmoCardiaco generarYGuardar(int petId) {
        Pet pet = petRepo.findById(petId)
            .orElseThrow(() -> new EntityNotFoundException("Pet no encontrado con id " + petId));

        // obtengo un BPM que varía suavemente en cada llamada para esa mascota
        int bpm = simulator.getCurrentBpm(petId);

        RitmoCardiaco ritmo = new RitmoCardiaco();
        ritmo.setPet_id(pet);
        ritmo.setValor(bpm);
        ritmo.setDate(LocalDateTime.now());

        return ritmoRepo.save(ritmo);
    }

    public Double filtroPorFecha(LocalDate fecha,int petId){
        Double lista = ritmoRepo.findByDateOnly(fecha,petId);
        return lista;
    }
}

