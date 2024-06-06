package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClientRepo{


    public interface IClienteDataMapper extends IDataMapper<Cliente>{
        Cliente read(int id);
        Cliente update(Cliente entity);
        void delete(Cliente entity);
    }

    public interface ClienteRepository {

         static void addCliente(Cliente cliente) {
            EntityManager em = Dal.getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            em.close();
        }

         static List<Cliente> listClientes() {
            EntityManager em = Dal.getEntityManager();
            try {
                return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
            } finally {
                Dal.closeEntityManager(em);
            }
        }

    }
    }



