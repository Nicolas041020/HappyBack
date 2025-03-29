package happy.paws.services;

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

    

}
