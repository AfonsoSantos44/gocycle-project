package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Loja;


public interface ILojaRepo extends IRespository<Loja, Long>{

    // Adds a new loja to the database.
    Loja save(Loja loja);
}