package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.entity.Dal;
import jakarta.persistence.EntityManager;

public class BicicletaRepo{

    // use for read only
    public interface IBicicletaDataMapper extends IDataMapper<Bicicleta> {
        Bicicleta read(int id);

        Bicicleta update(Bicicleta entity);

        void delete(Bicicleta entity);
    }

    // use for write only
    public interface BicicletaRepository {

        static void addBicicleta(Bicicleta bicicleta) {
            EntityManager em = Dal.getEntityManager();
            em.getTransaction().begin();
            em.persist(bicicleta);
            em.getTransaction().commit();
            em.close();
        }
    }
}

/*
    // Adds a new bicicleta to the database.
    Bicicleta save(Bicicleta bicicleta);
}

 */
