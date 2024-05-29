package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.model.dto.BicicletaDTO;
import jakarta.persistence.EntityManager;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BicicletaRepo{

    // use for read only
    public interface IBicicletaDataMapper extends IDataMapper<Bicicleta> {
        Bicicleta read(int id);

        Bicicleta update(Bicicleta entity);

        void delete(Bicicleta entity);
    }

    // use for write only
    public interface BicicletaRepository {

        static List<Bicicleta> listBikes(){
            EntityManager em = Dal.getEntityManager();
            List<Bicicleta> bikes = em.createQuery("SELECT b FROM Bicicleta b", Bicicleta.class).getResultList();
            em.close();
            Collections.reverse(bikes);
            return Collections.unmodifiableList(bikes);
        }

    }
}

/*
    // Adds a new bicicleta to the database.
    Bicicleta save(Bicicleta bicicleta);
}

 */
