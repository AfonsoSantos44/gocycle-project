package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.model.dto.BicicletaDTO;
import isel.sisinf.model.dto.ReservaDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BicicletaService
{
    private BicicletaService bicicletaService;
    private static Dal dal;

    public BicicletaService()
    {
        dal = new Dal();
    }

    public static List<BicicletaDTO> listBikes() {
        try {
            return dal.listBikes();
        } catch (SQLException e) {
            System.err.println("Error listing bikes: " + e.getMessage());
            return null;
        }
    }


    public static boolean checkBikeAvailability(BicicletaDTO bike) {
        return dal.checkBikeAvailability(bike);
    }


}
