package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;

import java.util.List;

import static isel.sisinf.jpa.dal.entity.Dal.getEntityManager;

public class BicicletaRepo {

    public interface IBicicletaDataMapper extends IDataMapper<Bicicleta> {
        Bicicleta read(int id);

        Bicicleta update(Bicicleta entity);

        void delete(Bicicleta entity);
    }

    public static class BicicletaRepository {

        public static List<Bicicleta> listBikes() {
            EntityManager em = getEntityManager();
            try {
                // Ajuste na consulta para incluir uma cláusula ORDER BY
                List<Bicicleta> bikes = em.createQuery("SELECT b FROM Bicicleta b WHERE b.estado = 'livre' ORDER BY b.identificador", Bicicleta.class).getResultList();
                return bikes;
            } finally {
                em.close();
            }
        }

        public static boolean checkBikeAvailability(Integer bikeIdentifier) {
            EntityManager em = getEntityManager();
            try {
                Bicicleta foundBike = em.find(Bicicleta.class, bikeIdentifier);
                return foundBike != null && "livre".equals(foundBike.getEstado());
            } finally {
                em.close();
            }
        }

        public static Bicicleta getBicicleta(Integer bikeIdentifier) {
            EntityManager em = getEntityManager();
            try {
                return em.find(Bicicleta.class, bikeIdentifier);
            } finally {
                em.close();
            }
        }

        public static void updateBikeState(Integer bikeIdentifier, String newState) {
            EntityManager em = getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                // Chama o procedimento armazenado
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
                em.close();
            }
        }


    }
}
