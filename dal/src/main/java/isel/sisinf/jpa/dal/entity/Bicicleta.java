package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Bicicleta")
public class Bicicleta
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String identificador;
    private int pesoGramas;
    private String modelo;
    private String marca;
    private int numeroVelocidades;
    private String estado;
    private int autonomia; // para bicicletas elétricas
    private int velocidadeMaxima; // para bicicletas elétricas

    @OneToOne
    private DispositivoGPS dispositivoGPS;

    public Bicicleta(int i, String identificador, int pesoGramas, String modelo, String marca, int numeroVelocidades, String estado, int autonomia) {
        this.identificador = identificador;
        this.pesoGramas = pesoGramas;
        this.modelo = modelo;
        this.marca = marca;
        this.numeroVelocidades = numeroVelocidades;
        this.estado = estado;
        this.autonomia = autonomia;
    }

    public Bicicleta() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
