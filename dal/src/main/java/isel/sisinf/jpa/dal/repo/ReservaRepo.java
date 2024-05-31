package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.entity.Reserva;
import jakarta.persistence.EntityManager;

public class ReservaRepo {

    public interface IReservaDataMapper extends IDataMapper<Reserva> {
        Reserva read(int id);

        Reserva update(Reserva entity);

        void delete(Reserva entity);
    }

    public interface ReservaRepository {

        static void addReserva(Reserva reserva) {
            EntityManager em = Dal.getEntityManager();
            em.getTransaction().begin();
            em.persist(reserva);
            em.getTransaction().commit();
            em.close();
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