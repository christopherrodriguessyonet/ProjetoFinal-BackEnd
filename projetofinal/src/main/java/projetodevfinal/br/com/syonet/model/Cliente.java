package projetodevfinal.br.com.syonet.model;

import java.time.LocalDate;
import java.util.regex.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String email;
    private LocalDate nascimento;

    public Cliente(String nome, String email, LocalDate nascimento) {
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
    }

    // Método para validar o e-mail
    public void validateEmail() {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(this.email).matches()) {
            throw new IllegalArgumentException("E-mail inválido.");
        }
    }
}
