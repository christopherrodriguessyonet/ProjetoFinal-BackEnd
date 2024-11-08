package cursodev.projetofinal.syonnet.com.resource;

import cursodev.projetofinal.syonnet.com.service.EmailService;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/email")
public class EmailController {

    @Inject
    EmailService emailService;

    @POST
    @Path("/testar-envio")
    public Response testarEnvioEmail() {
        emailService.enviarEmailsDiarios(); // Chama o método que envia os e-mails
        return Response.ok("E-mails de teste enviados!").build();
    }
}
