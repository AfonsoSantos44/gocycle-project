package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Bicicleta;

public interface IBicicletaRepo extends IRespository<Bicicleta, Long>{

    // Adds a new bicicleta to the database.
    Bicicleta save(Bicicleta bicicleta);
}
