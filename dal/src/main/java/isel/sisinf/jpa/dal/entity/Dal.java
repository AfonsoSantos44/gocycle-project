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


    public boolean checkBikeAvailability(BicicletaDTO bike) {
        EntityManager em = getEntityManager();
       // BicicletaDTO foundBike = em.find(BicicletaDTO.class, bike.getIdentificador());
        em.close();
       // return foundBike != null && "Disponivel".equals(foundBike.getEstado());
        return true;
    }

    public List<ReservaDTO> listBookings() {
        EntityManager em = getEntityManager();
        List<ReservaDTO> bookings = em.createQuery("SELECT r FROM Reserva r", ReservaDTO.class).getResultList();
        em.close();
        return bookings;
    }

    public void createBooking(ReservaDTO reservaDTO) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(reservaDTO);
        em.getTransaction().commit();
        em.close();
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