package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.entity.Reserva;
import isel.sisinf.model.dto.ReservaDTO;
import jakarta.persistence.EntityManager;

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
            EntityManager em = getEntityManager();
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
            em.close();
        }

        static List<Reserva> listBookings() {
            EntityManager em = getEntityManager();
            List<Reserva> bookings = em.createQuery("SELECT r FROM Reserva r", Reserva.class).getResultList();
            em.close();
            return bookings;
        }

        static Integer getNextBookingNumber() {
            EntityManager em = getEntityManager();
            Integer maxBookingNumber = em.createQuery("SELECT MAX(r.numeroReserva) FROM Reserva r", Integer.class).getSingleResult();
            em.close();
            return (maxBookingNumber == null ? 1 : maxBookingNumber + 1);
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