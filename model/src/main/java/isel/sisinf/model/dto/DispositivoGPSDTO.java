package isel.sisinf.model.dto;

public class DispositivoGPSDTO {
    private String numeroSerie;
    private double latitude;
    private double longitude;
    private int percentagemBateria;


    // Getters

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getPercentagemBateria() {
        return percentagemBateria;
    }

    // Setters

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setPercentagemBateria(int percentagemBateria) {
        this.percentagemBateria = percentagemBateria;
    }
}
