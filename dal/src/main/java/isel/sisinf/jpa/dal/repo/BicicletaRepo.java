package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import jakarta.persistence.EntityManager;

import java.util.Collections;
import java.util.List;

import static isel.sisinf.jpa.dal.entity.Dal.getEntityManager;

public class BicicletaRepo{

    // use for read only
    public interface IBicicletaDataMapper extends IDataMapper<Bicicleta> {
        Bicicleta read(int id);

        Bicicleta update(Bicicleta entity);

        void delete(Bicicleta entity);
    }

    // use for write only
    public interface BicicletaRepository {

        EntityManager em = getEntityManager();

        static List<Bicicleta> listBikes(){
            List<Bicicleta> bikes = em.createQuery("SELECT b FROM Bicicleta b", Bicicleta.class).getResultList();
            em.close();
            Collections.reverse(bikes);
            return Collections.unmodifiableList(bikes);
        }

        static boolean checkBikeAvailability(Integer bikeIdentifier) {
            Bicicleta foundBike = getBicicleta(bikeIdentifier);
            return foundBike != null && "livre".equals(foundBike.getEstado());
        }

        static Bicicleta getBicicleta(Integer bikeIdentifier) {
                Bicicleta foundBike = em.find(Bicicleta.class, bikeIdentifier);
                em.close();
                return foundBike;
        }

    }
}

/*
    // Adds a new bicicleta to the database.
    Bicicleta save(Bicicleta bicicleta);
}

 */
