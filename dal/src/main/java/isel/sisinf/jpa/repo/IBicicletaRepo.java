package isel.sisinf.jpa.repo;

import isel.sisinf.jpa.Bicicleta;

public interface IBicicletaRepo extends IRespository<Bicicleta, Long>{

    // Adds a new bicicleta to the database.
    Bicicleta save(Bicicleta bicicleta);
}
