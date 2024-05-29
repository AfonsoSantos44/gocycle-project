package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.model.dto.ReservaDTO;
import java.util.List;

public class ReservaService
{
    private static Dal dal;

    public ReservaService()
    {
        this.dal = new Dal();
    }

    public static void createBooking(ReservaDTO reservaDTO)
    {
        dal.createBooking(reservaDTO);
        System.out.println("Booking created successfully!");
    }

    public void cancelBooking(String numeroReserva) {
        try {
            dal.cancelBooking(numeroReserva);
            System.out.println("Booking canceled successfully!");
        } catch (Exception e) {
            System.err.println("Error canceling booking: " + e.getMessage());
        }
    }



}
