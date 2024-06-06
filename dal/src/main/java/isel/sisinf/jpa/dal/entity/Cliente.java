package isel.sisinf.jpa.dal.entity;

import isel.sisinf.jpa.dal.repo.ClientRepo;
import isel.sisinf.jpa.dal.service.ClienteService;
import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    private int numeroCliente;
    private String nome;
    private String morada;
    private String numeroTelefone;
    private String enderecoEletronico;
    private String numeroCCPassaporte;
    private String nacionalidade;

    public Cliente(int nextNumeroCliente, String nome, String morada, String numeroTelefone, String enderecoEletronico, String numeroCCPassaporte, String nacionalidade) {
        this.numeroCliente = nextNumeroCliente;
        this.nome = nome;
        this.morada = morada;
        this.numeroTelefone = numeroTelefone;
        this.enderecoEletronico = enderecoEletronico;
        this.numeroCCPassaporte = numeroCCPassaporte;
        this.nacionalidade = nacionalidade;
    }

    public Cliente() {
    }

    // Getters

    public int getNumeroCliente(){return numeroCliente;}

    public String getNome() {
        return nome;
    }

    public String getMorada() {
        return morada;
    }

    public String getEnderecoEletronico() {
        return enderecoEletronico;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getNumeroCCPassaporte() {
        return numeroCCPassaporte;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    // Setters

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setEnderecoEletronico(String enderecoEletronico) {
        this.enderecoEletronico = enderecoEletronico;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public void setNumeroCCPassaporte(String numeroCCPassaporte) {
        this.numeroCCPassaporte = numeroCCPassaporte;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}