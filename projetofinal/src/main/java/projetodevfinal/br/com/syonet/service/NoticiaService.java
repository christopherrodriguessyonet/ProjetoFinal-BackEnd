package projetodevfinal.br.com.syonet.service;

import projetodevfinal.br.com.syonet.model.Noticia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.regex.Pattern;

@ApplicationScoped
public class NoticiaService implements PanacheRepository<Noticia> {

    private static final String LINK_REGEX = "^(http://|https://).*"; // Regex para validar o link

    public void cadastrarNoticia(Noticia noticia) {
        // Verifica se o link é válido
        if (noticia.getLink() == null || !Pattern.matches(LINK_REGEX, noticia.getLink())) {
            throw new RuntimeException("O link deve ser uma URL válida que comece com http:// ou https://");
        }
        persist(noticia);
    }

    public List<Noticia> listarNoticiasNaoEnviadas() {
        return find("enviada", false).list();
    }

    public List<Noticia> listarNoticiasEnviadas() {
        return find("enviada", true).list();
    }
}
