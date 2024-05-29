package isel.sisinf.model.dto;

import java.time.LocalDateTime;

public class ReservaDTO {
    private String numeroReserva;

    // tirar numero de cliente
    private String numeroCliente;
    private String numeroBicicleta;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double valorPagar;


    // Getters

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public String getNumeroReserva() {
        return numeroReserva;
    }

    public String getNumeroCliente() {
        return numeroCliente;
    }

    public String getNumeroBicicleta() {
        return numeroBicicleta;
    }

    // Setters

    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public void setNumeroBicicleta(String numeroBicicleta) {
        this.numeroBicicleta = numeroBicicleta;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }
}
