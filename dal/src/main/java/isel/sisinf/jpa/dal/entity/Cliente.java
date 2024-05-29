package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    private int numeroCliente;
    private String nome;
    private String morada;
    private String telefone;
    private String email;
    private String numeroCCPassaporte;
    private String nacionalidade;

    public Cliente(int numeroCliente, String nome, String morada, String telefone, String email, String numeroCCPassaporte, String nacionalidade) {
        this.numeroCliente = numeroCliente;
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.email = email;
        this.numeroCCPassaporte = numeroCCPassaporte;
        this.nacionalidade = nacionalidade;
    }

    public Cliente() {
    }
}