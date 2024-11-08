package projetodevfinal.br.com.syonet.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class EmailServiceTest {

    @Inject
    EmailService emailService;

    @Test
    public void testEnviarEmail() {
        //Dados de teste
        String emailDestinatario = "naruto@example.com";
        String assunto = "Sasuke está morto";
        String corpo = "<p>Ele está vivo, venha busca-lo.</p>";

        //Chamando o método de envio de e-mail
        try {
            emailService.enviarEmail(emailDestinatario, assunto, corpo);
            assertTrue(true, "O e-mail foi enviado com sucesso.");
        } catch (Exception e) {
            fail("O envio do e-mail falhou: " + e.getMessage());
        }
    }
}
