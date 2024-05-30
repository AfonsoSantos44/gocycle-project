package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DispositivoGPS")
public class DispositivoGPS
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroSerie;
    private double latitude;
    private double longitude;
    private int percentagemBateria;

    public DispositivoGPS(Long id, String numeroSerie, double latitude, double longitude, int percentagemBateria) {
        this.id = id;
        this.numeroSerie = numeroSerie;
        this.latitude = latitude;
        this.longitude = longitude;
        this.percentagemBateria = percentagemBateria;
    }

    public DispositivoGPS() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
