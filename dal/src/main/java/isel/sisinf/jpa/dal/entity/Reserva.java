package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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

}
