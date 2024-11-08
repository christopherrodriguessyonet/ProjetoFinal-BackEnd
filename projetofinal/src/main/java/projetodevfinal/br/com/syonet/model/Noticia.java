package projetodevfinal.br.com.syonet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

@Entity
@Table(name = "Noticia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noticia {
    @Id
    @GeneratedValue
    private Long id;
    private String titulo;
    private String descricao;
    private String link;
    private boolean enviada = false; // Define o padrão como não enviada

    public Noticia(String titulo, String descricao, String link) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        this.enviada = false;
    }

    // Método para validar o link
    public void validateLink() {
        if (link != null && !link.isEmpty()) {
            String linkRegex = "^(http|https)://.*$";
            if (!Pattern.matches(linkRegex, link)) {
                throw new IllegalArgumentException("O link deve ser uma URL válida que comece com http:// ou https://");
            }
        } else {
            throw new IllegalArgumentException("O link não pode ser vazio.");
        }
    }
}
