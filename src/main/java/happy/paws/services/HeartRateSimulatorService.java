package happy.paws.services;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class HeartRateSimulatorService {

     private final Random random = new Random();
    private final int minBpm    = 60;   // mínimo BPM
    private final int maxBpm    = 140;  // máximo BPM
    private final int variacion = 3;    // variación máxima ±3 BPM

    // Estado de pulso por mascota
    private final Map<Integer, Integer> currentBpms = new ConcurrentHashMap<>();

    /**
     * Cada vez que se pide, este método calcula un delta aleatorio en [-variacion, +variacion]
     * sobre el último valor de esa mascota, o inicializa uno nuevo si es la primera llamada.
     *
     * @param petId el ID de la mascota
     * @return el nuevo valor de BPM para esa mascota
     */
    public int getCurrentBpm(int petId) {
        return currentBpms.compute(petId, (id, lastBpm) -> {
            int base = (lastBpm != null)
                     ? lastBpm
                     : minBpm + random.nextInt(maxBpm - minBpm + 1);
            int delta = random.nextInt(variacion * 2 + 1) - variacion;
            int nuevo = base + delta;
            if (nuevo < minBpm) nuevo = minBpm;
            else if (nuevo > maxBpm) nuevo = maxBpm;
            return nuevo;
        });
    }
   
}

