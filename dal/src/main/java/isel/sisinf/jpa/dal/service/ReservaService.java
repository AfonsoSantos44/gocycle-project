package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.entity.Reserva;
import isel.sisinf.jpa.dal.repo.ReservaRepo;

public class ReservaService
{
    private static Dal dal;

    public ReservaService()
    {
        this.dal = new Dal();
    }

    public static void createBooking(Reserva reserva) {
        ReservaRepo.ReservaRepository.createBooking(reserva);
    }

    public void removeBooking(Integer numeroReserva) {
        ReservaRepo.ReservaRepository.removeBooking(numeroReserva);
    }

    public void removeBookingWithOptimisticLock(Integer numeroReserva) {
        ReservaRepo.ReservaRepository.cancelBookingWithOptimisticLocking(numeroReserva);
    }

}
