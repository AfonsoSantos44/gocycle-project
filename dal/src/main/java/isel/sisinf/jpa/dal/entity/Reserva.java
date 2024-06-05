package isel.sisinf.jpa.dal.entity;

import isel.sisinf.jpa.dal.repo.BicicletaRepo;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reserva")
public class Reserva {

    @Id
    private Integer numeroReserva;
    private Integer numerobicicleta;
    private Integer numeroCliente;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double valorPagar;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "numerobicicleta", insertable = false, updatable = false)
    private Bicicleta bicicleta;

    public Reserva(LocalDateTime dataInicio, LocalDateTime dataFim, double valorPagar, Integer numeroCliente, Integer bicicletaId) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorPagar = valorPagar;
        this.numeroCliente = numeroCliente;
        this.numerobicicleta = bicicletaId;
    }

    public Reserva() {
    }

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

    public Integer getNumeroReserva() {
        return numeroReserva;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    public Bicicleta getbicicleta() {
        return bicicleta;
    }


    // Setters

    public void setNumeroReserva(Integer numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
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


    public void setBicicleta(Bicicleta selectedBike) {
       numerobicicleta = selectedBike.getIdentificador();
    }

}