package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
