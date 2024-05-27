package isel.sisinf.model.dto;

public class LojaDTO {
    private String codigo;
    private String gestor;
    private String morada;
    private String localidade;
    private String numeroTelefone;
    private String enderecoEletronico;


    // Getters

    public String getCodigo() {
        return codigo;
    }

    public String getGestor() {
        return gestor;
    }

    public String getMorada() {
        return morada;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public String getEnderecoEletronico() {
        return enderecoEletronico;
    }

    // Setters

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setGestor(String gestor) {
        this.gestor = gestor;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public void setEnderecoEletronico(String enderecoEletronico) {
        this.enderecoEletronico = enderecoEletronico;
    }

}
