package projetodevfinal.br.com.syonet.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.MediaType;


@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN) // Força o tipo de resposta como texto puro
                .build();
    }
}

