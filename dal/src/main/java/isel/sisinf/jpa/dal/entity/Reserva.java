package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reserva")
public class Reserva
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Assumindo que é único

    private String numeroReserva;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double valorPagar;

    @ManyToOne
    private Bicicleta bicicleta;

    public Reserva(String numeroReserva, LocalDateTime dataInicio, LocalDateTime dataFim, double valorPagar) {
        this.numeroReserva = numeroReserva;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.valorPagar = valorPagar;
    }

    public Reserva(){

    }

}
