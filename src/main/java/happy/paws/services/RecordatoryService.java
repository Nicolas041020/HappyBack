package happy.paws.services;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import happy.paws.model.Pet;
import happy.paws.model.Recordatory;
import happy.paws.repositories.PetRepository;
import happy.paws.repositories.RecordatoryRepository;

@Service
public class RecordatoryService {

    private RecordatoryRepository recordatoryRepository;

    private PetRepository petRepository;

    private EmailScheduleService emailScheduleService;

 //   private EmailService emailService;

 public RecordatoryService(RecordatoryRepository recordatoryRepository, PetRepository petRepository,
 EmailScheduleService emailScheduleService) {
    this.recordatoryRepository = recordatoryRepository;
    this.petRepository = petRepository;
    this.emailScheduleService = emailScheduleService;
}
    


    public Recordatory add(Recordatory recordatory, int pet_id) throws Exception{
        Pet pet = petRepository.findById(pet_id).orElse(null);
        if(pet != null){
            pet.getRecordatories().add(recordatory);
            petRepository.save(pet);
            recordatory.setPet(pet);
            //emailScheduleService.enviarCorreoDiario();
 //           String destinatario = pet.getUser().getEmail();
 //           String asunto = "Recordatorio Vacuna" + recordatory.getVaccine();
 //           String cuerpo = "<h1>¡Debes Vacunar a tu mascota!</h1>" +
 //                        "<p>Recuerda Aplicarle a tu mascota la vacuna de <strong>" + recordatory.getVaccine() + ".</strong></p>";
 //           try {
 //           emailService.sendEmail(destinatario, asunto, cuerpo);
 //       } catch (MessagingException e) {
 //           System.out.println("Error al enviar el correo: " + e.getMessage());
 //       }
        }
        return recordatoryRepository.save(recordatory);

    }

    public List<Recordatory> verRecs(int user_id){
        return recordatoryRepository.findAllByUserId(user_id);
    }

    public Recordatory updateRecordatory(Recordatory recordatory,int recordatory_id){
        Recordatory rec = recordatoryRepository.findById(recordatory_id).orElse(null);
        if (recordatory.getDate()!= null)  rec.setDate(recordatory.getDate());
        if(!recordatory.getVaccine().isEmpty()) rec.setVaccine(recordatory.getVaccine());
        return recordatoryRepository.save(rec);
    }

    public Recordatory getRec(int rec_id){
        return recordatoryRepository.findById(rec_id).orElse(null);
    }

    public Recordatory deleteRec(int rec_id){
        Recordatory rec = recordatoryRepository.findById(rec_id).orElse(null);
        recordatoryRepository.delete(rec);
        return rec;
        
    }

}
