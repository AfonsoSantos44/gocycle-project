package isel.sisinf.jpa.dal.entity;


import isel.sisinf.model.dto.ReservaDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Dal
{
    private static final EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("gocycle-project");
    }

    public static String version(){ return "1.0";}

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


}