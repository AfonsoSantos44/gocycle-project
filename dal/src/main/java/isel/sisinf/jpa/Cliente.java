package isel.sisinf.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cliente
{
    @Id
    private Long id;
    private String nome;
    private String morada;
    private String enderecoEletronico;
    private String numeroTelefone;
    private String numeroCCPassaporte;
    private String nacionalidade;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
