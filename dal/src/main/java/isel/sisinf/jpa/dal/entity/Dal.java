package isel.sisinf.jpa.dal.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Dal {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("gocycle-project");
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    public static String version(){ return "1.0";}

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocal.get();
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
            threadLocal.set(em);
        }
        return em;
    }

    public static void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public static void closeEntityManagerFactory() {
        emf.close();
    }
}


