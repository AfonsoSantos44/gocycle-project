package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Cliente;



public interface IClienteRepo extends IRespository<Cliente, Long>{

    // Adds a new cliente to the database.
   Cliente save(Cliente cliente);
}
