package isel.sisinf.jpa;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;


@Entity
public class Reserva
{
    @Id
    private Long id;
    private String numeroReserva;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double valorPagar;

    @ManyToOne
    private Bicicleta bicicleta;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
