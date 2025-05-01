package happy.paws.services;

import java.util.List;

import org.springframework.stereotype.Service;

import happy.paws.model.Recorrido;
import happy.paws.repositories.RecorridoRepository;

@Service
public class RecorridoService {

    private RecorridoRepository recorridoRepository;

    public RecorridoService(RecorridoRepository recorridoRepository) {
        this.recorridoRepository = recorridoRepository;
    }

    public Recorrido add(Recorrido rec){
        return recorridoRepository.save(rec);
    }

    public Recorrido getLast(){
        Recorrido reco = recorridoRepository.findTopByOrderByIdDesc();
        return reco;
        
    }

}
