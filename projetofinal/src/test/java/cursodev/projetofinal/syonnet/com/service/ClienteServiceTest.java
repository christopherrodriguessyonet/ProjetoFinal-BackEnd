package cursodev.projetofinal.syonnet.com.service;

import cursodev.projetofinal.syonnet.com.model.Cliente;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class ClienteServiceTest {

    @Inject
    ClienteService clienteService;

    @Test
    @Transactional // Garante que a transação esteja ativa
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("Teste", "teste@teste.com", null);
        clienteService.cadastrarCliente(cliente);

        assertNotNull(cliente.getId()); // Verifica se o ID foi gerado
    }

    @Test
    public void testEmailInvalido() {
        Cliente cliente = new Cliente("Teste", "emailinvalido", null);

        // Verifica se a exceção RuntimeException é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.cadastrarCliente(cliente);
        });

        // Verifica se a mensagem de erro contém a mensagem esperada
        String expectedMessage = "E-mail inválido.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
