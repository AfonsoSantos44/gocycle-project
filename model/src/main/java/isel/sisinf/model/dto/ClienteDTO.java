package isel.sisinf.model.dto;

public class ClienteDTO {
    private int numeroCliente;
    private String nome;
    private String morada;
    private String enderecoEletronico;
    private String numeroTelefone;
    private String numeroCCPassaporte;
    private String nacionalidade;


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

    public void setNumeroCliente(int numeroCliente){this.numeroCliente = numeroCliente;}

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
