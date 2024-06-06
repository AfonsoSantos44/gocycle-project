package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.entity.Dal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class BicicletaRepo {

    public interface IBicicletaDataMapper extends IDataMapper<Bicicleta> {
        Bicicleta read(int id);

        Bicicleta update(Bicicleta entity);

        void delete(Bicicleta entity);
    }

    public static class BicicletaRepository {

        public static List<Bicicleta> listBikes() {
            EntityManager em = Dal.getEntityManager(); // Open EntityManager
            try {

                // Query to list all bikes
                return em.createQuery("SELECT b FROM Bicicleta b WHERE b.estado = 'livre' ORDER BY b.identificador", Bicicleta.class).getResultList();
            } finally {
                Dal.closeEntityManager(em);
            }
        }

        public static boolean checkBikeAvailability(Integer bikeIdentifier) {
            EntityManager em = Dal.getEntityManager();
            try {
                Bicicleta foundBike = em.find(Bicicleta.class, bikeIdentifier);
                return foundBike != null && "livre".equals(foundBike.getEstado());
            } finally {
                Dal.closeEntityManager(em);
            }
        }

        public static Bicicleta getBicicleta(Integer bikeIdentifier) {
            EntityManager em = Dal.getEntityManager();
            try {
                return em.find(Bicicleta.class, bikeIdentifier);
            } finally {
                Dal.closeEntityManager(em);
            }
        }

        public static void updateBikeState(Integer bikeIdentifier, String newState) {
            EntityManager em = Dal.getEntityManager(); // Open EntityManager
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();

                // Calls a stored procedure to update the bike state
                Query query = em.createNativeQuery("CALL update_bike_state(?, ?)");
                query.setParameter(1, bikeIdentifier);
                query.setParameter(2, newState);
                query.executeUpdate();
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
    }
}
