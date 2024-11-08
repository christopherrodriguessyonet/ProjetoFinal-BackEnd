package cursodev.projetofinal.syonnet.com.service;

import io.quarkus.scheduler.Scheduled;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailScheduler {
    
    @Inject
    EmailService emailService;

    @Scheduled(cron = "0 0 8 * * ?") // Envia e-mails todos os dias às 08h
    public void enviarEmailsDiarios() {
        emailService.enviarEmailsDiarios();
    }
}
