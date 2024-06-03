package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reserva")
public class Reserva {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer numeroReserva;
    private Integer numeroCliente;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double valorPagar;

    @ManyToOne
    @JoinColumn(name = "numeroBicicleta", insertable = false, updatable = false)
    private Bicicleta bicicleta;

    public Reserva(Integer numeroReserva, LocalDateTime dataInicio, LocalDateTime dataFim, double valorPagar, Integer numeroCliente, Bicicleta bicicleta) {
        this.numeroReserva = numeroReserva;
        this.bicicleta = bicicleta;
        this.numeroCliente = numeroCliente;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorPagar = valorPagar;
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

    public Bicicleta getBicicleta() {
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

}