package happy.paws.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import happy.paws.model.Recordatory;
import happy.paws.repositories.RecordatoryRepository;
import jakarta.mail.MessagingException;

@Service
public class EmailScheduleService {

    private final EmailService emailService;

    private RecordatoryRepository recordatoryRepository;

    

    public EmailScheduleService(EmailService emailService, RecordatoryRepository recordatoryRepository) {
        this.emailService = emailService;
        this.recordatoryRepository = recordatoryRepository;
    }



    @Scheduled(cron = "0 17 14 * * *", zone = "America/Bogota") // Configurar hora
    public void enviarCorreoDiario() throws Exception {
        System.out.println("Entra");
        List<Recordatory> lista = recordatoryRepository.rec();
        List<Recordatory> recUpdated = new ArrayList<>();
        System.out.println(lista.size());
        for (int i = 0; i < lista.size(); i++) {
            Recordatory recordatory = lista.get(i);
            String destinatario = recordatory.getPet().getUser().getEmail();
            
            try {
                String asunto = "Recordatorio Vacuna " + recordatory.getVaccine();;
                String contenidoHtml = "<h1>¡Debes Vacunar a " + recordatory.getPet().getName()+ "!</h1>" +
                         "<p>Recuerda Aplicarle a "+ recordatory.getPet().getName() + " la vacuna de <strong>" + recordatory.getVaccine() + ".</strong></p>";
    
                emailService.sendEmail(destinatario, asunto, contenidoHtml);
                recordatory.setEstado(true);
            recUpdated.add(recordatory);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

        }

        if (!recUpdated.isEmpty()) {
            recordatoryRepository.saveAll(recUpdated);
        }
        
    }
}
