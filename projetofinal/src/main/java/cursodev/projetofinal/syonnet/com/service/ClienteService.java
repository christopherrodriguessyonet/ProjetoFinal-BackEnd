package cursodev.projetofinal.syonnet.com.service;

import cursodev.projetofinal.syonnet.com.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.regex.Pattern;

@ApplicationScoped
public class ClienteService implements PanacheRepository<Cliente> {

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public void cadastrarCliente(Cliente cliente) {
        if (!isValidEmail(cliente.getEmail())) {
            throw new RuntimeException("E-mail inválido.");
        }
        persist(cliente);
    }

    private boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public List<Cliente> listarClientes() {
        return listAll();
    }
}
