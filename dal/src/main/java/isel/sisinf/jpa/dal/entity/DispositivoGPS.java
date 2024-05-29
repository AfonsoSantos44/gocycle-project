package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
public class DispositivoGPS
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numeroSerie;
    private double latitude;
    private double longitude;
    private int percentagemBateria;

}
