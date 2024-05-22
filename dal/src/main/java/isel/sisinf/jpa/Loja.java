package isel.sisinf.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Loja {
    @Id
    private Long id;
    private String codigo;
    private String gestor;
    private String morada;
    private String localidade;
    private String numeroTelefone;
    private String enderecoEletronico;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
