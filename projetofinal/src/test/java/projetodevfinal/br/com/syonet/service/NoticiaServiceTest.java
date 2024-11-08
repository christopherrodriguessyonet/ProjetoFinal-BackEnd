package projetodevfinal.br.com.syonet.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import projetodevfinal.br.com.syonet.model.Noticia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class NoticiaServiceTest {

    @Inject
    NoticiaService noticiaService;

    @Test
    @Transactional // Garante que a transação esteja ativa
    public void testCadastrarNoticiaComLinkValido() {
        Noticia noticia = new Noticia("Título Teste", "Descrição Teste", "http://link.valido");
        noticiaService.cadastrarNoticia(noticia);

        assertNotNull(noticia.getId()); // Verifica se o ID foi gerado
    }

    @Test
    public void testCadastrarNoticiaComLinkInvalido() {
        Noticia noticia = new Noticia("Título Teste", "Descrição Teste", "linkinvalido");

        // Verifica se a exceção RuntimeException é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            noticiaService.cadastrarNoticia(noticia);
        });

        String expectedMessage = "O link deve ser uma URL válida que comece com http:// ou https://";
        String actualMessage = exception.getMessage();

        // Verifica se a mensagem contém a mensagem esperada
        assertTrue(actualMessage.contains(expectedMessage));

        // Verifica que o tipo da exceção é RuntimeException
        assertEquals(RuntimeException.class, exception.getClass());
    }

    @Test
    public void testCadastrarNoticiaComLinkVazio() {
        Noticia noticia = new Noticia("Título Teste", "Descrição Teste", "");

        // Verifica se a exceção RuntimeException é lançada
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            noticiaService.cadastrarNoticia(noticia);
        });

        String expectedMessage = "O link deve ser uma URL válida que comece com http:// ou https://";
        String actualMessage = exception.getMessage();

        // Verifica se a mensagem contém a mensagem esperada
        assertTrue(actualMessage.contains(expectedMessage));

        // Verifica que o tipo da exceção é RuntimeException
        assertEquals(RuntimeException.class, exception.getClass());
    }
}
