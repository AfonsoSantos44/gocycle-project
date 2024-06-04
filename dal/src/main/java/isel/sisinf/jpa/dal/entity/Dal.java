package isel.sisinf.jpa.dal.entity;

import isel.sisinf.model.dto.BicicletaDTO;
import isel.sisinf.model.dto.ReservaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Dal
{
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("gocycle-project");
    }

    public static String version(){ return "1.0";}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    public void cancelBooking(String numeroReserva) {
        EntityManager em = getEntityManager();
        ReservaDTO reserva = em.find(ReservaDTO.class, numeroReserva);
        if (reserva != null) {
            em.getTransaction().begin();
            em.remove(reserva);
            em.getTransaction().commit();
        }
        em.close();
    }

}