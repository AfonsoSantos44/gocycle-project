package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.entity.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

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

         static boolean removeBooking(Integer numeroReserva) {
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
             return false;
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

        static boolean cancelBookingWithOptimisticLocking(int bookingId) {
            // Retrieve the booking from the database
            Reserva reserva = ReservaRepo.ReservaRepository.getBookingById(bookingId);

            if (reserva == null) {
                System.out.println("Booking with ID " + bookingId + " does not exist.");
                return false;
            }

            // Perform optimistic locking by checking the version
            int currentVersion = reserva.getVersion();

            // Retrieve the latest version of the booking from the database
            Reserva latestReserva = ReservaRepo.ReservaRepository.getBookingById(bookingId);

            // Check if the version has changed since we retrieved the booking
            if (latestReserva.getVersion() != currentVersion) {
                System.out.println("Failed to cancel booking. It may have been modified by another transaction.");
                return false;
            }

            // Proceed with cancellation by incrementing the version
            reserva.setVersion(currentVersion + 1); // Assuming there's a version column

            // Change the bike state to "em reserva" (reserved)
            Bicicleta bike = reserva.getbicicleta();
            bike.setEstado("em reserva");

            // Remove the booking from the database
            boolean success = ReservaRepo.ReservaRepository.removeBooking(bookingId);

            if (!success) {
                System.out.println("Failed to cancel booking due to an unexpected error.");
                return false;
            }

            return true;
        }


        static Reserva getBookingById(int bookingId) {
            EntityManager em = getEntityManager();
            try {
                return em.find(Reserva.class, bookingId);
            } finally {
                Dal.closeEntityManager(em);
            }
        }

    }

}
