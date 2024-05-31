package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DispositivoGPS")
public class DispositivoGPS
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String numeroSerie;
    private double latitude;
    private double longitude;
    private int percentagemBateria;

    public DispositivoGPS(DispositivoGPS dispositivoGPS) {
        this.numeroSerie = dispositivoGPS.numeroSerie;
        this.latitude = dispositivoGPS.latitude;
        this.longitude = dispositivoGPS.longitude;
        this.percentagemBateria = dispositivoGPS.percentagemBateria;
    }

    public DispositivoGPS(){

    }

    //Getters

    public String getNumeroSerie() { return numeroSerie;}

    public double getLatitude() { return latitude;}

    public double getLongitude() { return longitude;}

    public int getPercentagemBateria() { return percentagemBateria;}

}
