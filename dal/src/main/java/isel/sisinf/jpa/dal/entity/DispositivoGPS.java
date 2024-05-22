package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
public class DispositivoGPS
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
