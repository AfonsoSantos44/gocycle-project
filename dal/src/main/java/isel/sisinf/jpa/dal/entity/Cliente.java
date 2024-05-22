package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;
@Entity
public class Cliente
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
