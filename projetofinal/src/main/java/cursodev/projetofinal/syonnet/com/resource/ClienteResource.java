package cursodev.projetofinal.syonnet.com.resource;

import cursodev.projetofinal.syonnet.com.model.Cliente;
import cursodev.projetofinal.syonnet.com.service.ClienteService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private final ClienteService clienteService = new ClienteService();

    @POST
    @Transactional
    public Response cadastrarCliente(Cliente cliente) {
        // Validar e-mail antes de cadastrar
        cliente.validateEmail();
        clienteService.cadastrarCliente(cliente);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }
}
