package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Bicicleta")
public class Bicicleta
{


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String identificador;
    private int pesoGramas;
    private String modelo;
    private String marca;
    private int numeroVelocidades;
    private String estado;
    private int autonomia; // para bicicletas elétricas
    private int velocidadeMaxima; // para bicicletas elétricas


    // Verificar depois
   // @OneToOne
   // private DispositivoGPS dispositivoGPS;

    public Bicicleta(Bicicleta bicicleta) {
        this.pesoGramas = bicicleta.pesoGramas;
        this.modelo = bicicleta.modelo;
        this.marca = bicicleta.marca;
        this.numeroVelocidades = bicicleta.numeroVelocidades;
        this.estado = bicicleta.estado;
        this.autonomia = bicicleta.autonomia;
        this.velocidadeMaxima = bicicleta.velocidadeMaxima;
    }

    public Bicicleta() {

    }

    // Getters

    public String getIdentificador() {
        return identificador;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getNumeroVelocidades() {
        return numeroVelocidades;
    }

    public String getEstado() {
        return estado;
    }

    public int getPesoGramas() {
        return pesoGramas;
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public int getAutonomia() {
        return autonomia;
    }

}
