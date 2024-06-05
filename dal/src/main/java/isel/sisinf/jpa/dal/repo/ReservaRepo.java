package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
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

         EntityManager em = getEntityManager();

        static void createBooking(Reserva reserva) {
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
            em.close();
        }

        static List<Reserva> listBookings() {
            List<Reserva> bookings = em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
            em.close();
            return bookings;
        }

        static List<Object[]> listReservedBikesWithBookings() {
            List<Object[]> result = em.createQuery(
                    "SELECT r.numeroReserva, b.identificador FROM Reserva r JOIN r.bicicleta b WHERE b.estado = 'em reserva'",
                    Object[].class
            ).getResultList();
            return result;
        }

        static Integer getNextBookingNumber() {
            Integer maxBookingNumber = em.createQuery("SELECT MAX(r.numeroReserva) FROM Reserva r", Integer.class).getSingleResult();
            em.close();
            return (maxBookingNumber == null ? 1 : maxBookingNumber + 1);
        }

         static void cancelBooking(String numeroReserva) {
            EntityManager em = getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();

                // Converter o ID da reserva para Integer
                Integer bookingId = Integer.valueOf(numeroReserva);

                // Encontrar a reserva
                Reserva reserva = em.find(Reserva.class, bookingId);
                if (reserva != null) {
                    // Obter a bicicleta associada à reserva
                    Bicicleta bike = reserva.getbicicleta();
                    if (bike != null) {
                        // Atualizar o estado da bicicleta para "livre"
                        Query query = em.createNativeQuery("CALL update_bike_state(?, ?)");
                        query.setParameter(1, bike.getIdentificador());
                        query.setParameter(2, "livre");
                        query.executeUpdate();
                    }

                    // Remover a reserva
                    em.remove(reserva);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;
            } finally {
                em.close();
            }
        }

    }



}

/*

    // Adds a new reserva to the database.
    Reserva save(Reserva reserva);

    // Gets the reserva with the given numeroReserva.
    Reserva getReservaByNumeroReserva(int numeroReserva);
}


 */