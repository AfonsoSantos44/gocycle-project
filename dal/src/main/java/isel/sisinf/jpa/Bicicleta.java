package isel.sisinf.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Bicicleta
{
    @Id
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
