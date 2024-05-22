package isel.sisinf.jpa.repo;

import isel.sisinf.jpa.DispositivoGPS;

public interface IDispositivoGPSRepo extends IRespository<DispositivoGPS, Long>{

    // Adds a new dispositivoGPS to the database.
    DispositivoGPS save(DispositivoGPS dispositivoGPS);
}
