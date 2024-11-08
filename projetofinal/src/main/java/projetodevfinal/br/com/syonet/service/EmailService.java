package projetodevfinal.br.com.syonet.service;

import projetodevfinal.br.com.syonet.model.Cliente;
import projetodevfinal.br.com.syonet.model.Noticia;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailService {

    @Inject
    ClienteService clienteService;

    @Inject
    NoticiaService noticiaService;

    @Transactional
    public void enviarEmailsDiarios() {
        // Busca todos os clientes do banco de dados
        List<Cliente> clientes = clienteService.listarClientes();
        // Busca as notícias não enviadas do banco de dados
        List<Noticia> noticias = noticiaService.listarNoticiasNaoEnviadas();
    
        for (Cliente cliente : clientes) {
            // Verifica se hoje é o aniversário do cliente
            boolean isAniversario = cliente.getNascimento() != null && 
                cliente.getNascimento().getMonth() == LocalDate.now().getMonth() && 
                cliente.getNascimento().getDayOfMonth() == LocalDate.now().getDayOfMonth();
    
            String assunto = "Notícias do dia!";
            String corpo = construirCorpoEmail(cliente, noticias, isAniversario);
            enviarEmail(cliente.getEmail(), assunto, corpo);
    
            // Atualiza o estado de cada notícia para "enviada" no banco de dados
            for (Noticia noticia : noticias) {
                if (!noticia.isEnviada()) { // Verifica se a notícia ainda não foi enviada
                    noticia.setEnviada(true); // Marca a notícia como enviada
                    noticiaService.persist(noticia); // Persistir as alterações no banco de dados
                }
            }
        }
    }

    private String construirCorpoEmail(Cliente cliente, List<Noticia> noticias, boolean isAniversario) {
        String template = carregarTemplate();
        
        // Substitui as variáveis no template
        String corpo = template
                .replace("{{nome_cliente}}", cliente.getNome())
                .replace("{{mensagem_aniversario}}", isAniversario ? "Feliz aniversário!" : "")
                .replace("{{noticias}}", gerarHtmlNoticias(noticias));
        
        return corpo;
    }

    private String carregarTemplate() {
        StringBuilder template = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("email_template.html")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                template.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return template.toString();
    }

    private String gerarHtmlNoticias(List<Noticia> noticias) {
        StringBuilder html = new StringBuilder();
        for (Noticia noticia : noticias) {
            html.append("<li><strong>").append(noticia.getTitulo()).append("</strong><br>");
            html.append(noticia.getDescricao()).append("<br>");
            if (noticia.getLink() != null) {
                html.append("<a href=\"").append(noticia.getLink()).append("\">Leia mais</a>");
            }
            html.append("</li>");
        }
        return html.toString();
    }

    public void enviarEmail(String para, String assunto, String corpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "sandbox.smtp.mailtrap.io");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String usuario = "3c8699cea57318";
        final String senha = "4684f5402c396c";

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("projetodev@syonet.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
            message.setSubject(assunto);
            message.setContent(corpo, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Email enviado para: " + para);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
