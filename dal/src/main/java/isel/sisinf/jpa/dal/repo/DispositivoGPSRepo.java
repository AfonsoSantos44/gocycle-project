package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.entity.DispositivoGPS;
import jakarta.persistence.EntityManager;

public class DispositivoGPSRepo {

    public interface IDispositivoGPSDataMapper extends IDataMapper<DispositivoGPS> {
        DispositivoGPS read(int id);

        DispositivoGPS update(DispositivoGPS entity);

        void delete(DispositivoGPS entity);
    }

    public interface DispositivoGPSRepository {

        static void addDispositivoGPS(DispositivoGPS dispositivoGPS) {
            EntityManager em = Dal.getEntityManager();
            em.getTransaction().begin();
            em.persist(dispositivoGPS);
            em.getTransaction().commit();
            em.close();
        }


        // Adds a new dispositivoGPS to the database.
        DispositivoGPS save(DispositivoGPS dispositivoGPS);
    }
}
