package isel.sisinf.jpa.repo;

import isel.sisinf.jpa.Loja;


public interface ILojaRepo extends IRespository<Loja, Long>{

    // Adds a new loja to the database.
    Loja save(Loja loja);
}