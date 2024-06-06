package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.entity.Reserva;
import isel.sisinf.model.dto.ReservaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static isel.sisinf.jpa.dal.entity.Dal.getEntityManager;

public class ReservaRepo {

    public interface IReservaDataMapper extends IDataMapper<Reserva> {
        Reserva read(int id);

        Reserva update(Reserva entity);

        void delete(Reserva entity);
    }

    public interface ReservaRepository {

        static void createBooking(Reserva reserva) {
            EntityManager em = Dal.getEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(reserva);
                em.getTransaction().commit();
            } finally {
                Dal.closeEntityManager(em);
            }
        }

        static List<Reserva> listBookings() {
            EntityManager em = Dal.getEntityManager();
            try {

                // Query to list all bookings
                List<Reserva> bookings = em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
                em.close();
                return bookings;
            } finally {
                Dal.closeEntityManager(em);
            }
        }

        static List<Object[]> listReservedBikesWithBookings() {
            EntityManager em = Dal.getEntityManager();
            try {

                // Query to list all reserved bikes with bookings
            return em.createQuery(
                    "SELECT r.numeroReserva, b.identificador FROM Reserva r JOIN r.bicicleta b WHERE b.estado = 'em reserva'",
                    Object[].class
            ).getResultList();
            } finally {
                Dal.closeEntityManager(em);
            }
        }

        static Integer getNextBookingNumber() {
            EntityManager em = getEntityManager();
            try {

                // Get the highest booking number
                Integer maxBookingNumber = em.createQuery("SELECT MAX(r.numeroReserva) FROM Reserva r", Integer.class).getSingleResult();
                em.close();
                return (maxBookingNumber == null ? 1 : maxBookingNumber + 1);
            }finally {
                Dal.closeEntityManager(em);
            }
        }

         static void cancelBooking(Integer numeroReserva) {
            EntityManager em = getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();

                // Find booking by ID
                Reserva reserva = em.find(Reserva.class, numeroReserva);
                if (reserva != null) {

                    // Obtains the bike from the booking
                    Bicicleta bike = reserva.getbicicleta();
                    if (bike != null) {

                        // Update bike state to "livre"
                        Query query = em.createNativeQuery("CALL update_bike_state(?, ?)");
                        query.setParameter(1, bike.getIdentificador());
                        query.setParameter(2, "livre");
                        query.executeUpdate();
                    }

                    // Remove booking
                    em.remove(reserva);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            } finally {
                Dal.closeEntityManager(em);
            }
        }

         static boolean customerExists(int customerId) {
            List<Cliente> customers = getEntityManager().createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            for (Cliente cliente : customers) {
                if (cliente.getNumeroCliente() == customerId) {
                    return true; // Customer found
                }
            }
            return false; // Customer not found
        }
    }

}
