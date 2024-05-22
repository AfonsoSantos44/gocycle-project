package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.DispositivoGPS;

public interface IDispositivoGPSRepo extends IRespository<DispositivoGPS, Long>{

    // Adds a new dispositivoGPS to the database.
    DispositivoGPS save(DispositivoGPS dispositivoGPS);
}
