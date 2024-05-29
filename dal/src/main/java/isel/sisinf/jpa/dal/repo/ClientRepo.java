package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClientRepo{

    // use for read only
    public interface IClienteDataMapper extends IDataMapper<Cliente>{
        Cliente read(int id);
        Cliente update(Cliente entity);
        void delete(Cliente entity);
    }

    // use for write only
    public interface ClienteRepository {

         static void addCliente(Cliente cliente) {
            EntityManager em = Dal.getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            em.close();
        }
    }
    }



