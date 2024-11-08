package cursodev.projetofinal.syonnet.com.resource;

import cursodev.projetofinal.syonnet.com.model.Noticia;
import cursodev.projetofinal.syonnet.com.service.NoticiaService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/noticias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoticiaResource {

    private final NoticiaService noticiaService = new NoticiaService();

    @POST
    @Transactional
    public Response cadastrarNoticia(Noticia noticia) {
        // Validar link antes de cadastrar
        noticia.validateLink();
        noticiaService.cadastrarNoticia(noticia);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/nao-enviadas")
    public List<Noticia> listarNoticiasNaoEnviadas() {
        return noticiaService.listarNoticiasNaoEnviadas();
    }

    @GET
    @Path("/enviadas")
    public List<Noticia> listarNoticiasEnviadas() {
        return noticiaService.listarNoticiasEnviadas();
    }
}
