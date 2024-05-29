package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.repo.BicicletaRepo;
import isel.sisinf.model.dto.BicicletaDTO;

import java.util.List;

public class BicicletaService {

    /* public BicicletaService()
    {
        dal = new Dal();
    }

     */
/*

    private Bicicleta convertToEntity(BicicletaDTO dto) {
        return new Bicicleta(dto.getIdentificador(), dto.getPesoGramas(), dto.getModelo(), dto.getMarca(), dto.getNumeroVelocidades(), dto.getEstado(), dto.getAutonomia());
    }

 */


    public static List<Bicicleta> listBikes() {
         // list all bikes using the function in BicicletaRepo
        return BicicletaRepo.BicicletaRepository.listBikes();
    }

/*
    public static boolean checkBikeAvailability(BicicletaDTO bike) {
        return dal.checkBikeAvailability(bike);
    }

 */


}
