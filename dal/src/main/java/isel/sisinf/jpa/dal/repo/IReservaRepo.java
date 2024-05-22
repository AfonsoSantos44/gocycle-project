package isel.sisinf.jpa.dal.repo;

import isel.sisinf.jpa.dal.entity.Reserva;

public interface IReservaRepo extends IRespository<Reserva, Long>{

    // Adds a new reserva to the database.
    Reserva save(Reserva reserva);

    // Gets the reserva with the given numeroReserva.
    Reserva getReservaByNumeroReserva(int numeroReserva);
}
