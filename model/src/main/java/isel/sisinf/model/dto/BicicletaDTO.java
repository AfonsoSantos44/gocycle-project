package isel.sisinf.model.dto;

public class BicicletaDTO {
    private String identificador;
    private int pesoGramas;
    private String modelo;
    private String marca;
    private int numeroVelocidades;
    private String estado;
    private int autonomia; // for electric bicycles
    private int velocidadeMaxima;

    public BicicletaDTO(String identificador) {
        this.identificador = identificador;
    }

    // Setters

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;

    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
