package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
