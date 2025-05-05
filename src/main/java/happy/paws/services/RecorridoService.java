package happy.paws.services;


import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import happy.paws.model.Pet;
import happy.paws.model.Recorrido;
import happy.paws.repositories.PetRepository;
import happy.paws.repositories.RecorridoRepository;

@Service
public class RecorridoService {

    private RecorridoRepository recorridoRepository;

    private PetRepository petRepository;

    

    //public Recorrido add(Recorrido rec){
     //   return recorridoRepository.save(rec);
   // }

   public RecorridoService(RecorridoRepository recorridoRepository, PetRepository petRepository) {
        this.recorridoRepository = recorridoRepository;
        this.petRepository = petRepository;
    }


   public void actualizarUbicacion(int idRecorrido, Pet pet, Double latitud, Double longitud) {
    Recorrido recorrido = recorridoRepository.findById(idRecorrido)
        .orElseThrow(() -> new RuntimeException("Recorrido no encontrado"));
    //Pet pet = petRepository.findById(idMascota).orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

    recorrido.setLat(latitud);
    recorrido.setLon(longitud);
    recorrido.setPet_id(pet);

    recorridoRepository.save(recorrido);
}


    public Recorrido getLast(){
        Recorrido reco = recorridoRepository.findTopByOrderByIdDesc();
        return reco;
        
    }

}
