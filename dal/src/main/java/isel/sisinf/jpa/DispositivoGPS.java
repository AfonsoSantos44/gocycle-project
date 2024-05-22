package isel.sisinf.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class DispositivoGPS
{
    @Id
    private Long id;
    private String numeroSerie;
    private double latitude;
    private double longitude;
    private int percentagemBateria;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
