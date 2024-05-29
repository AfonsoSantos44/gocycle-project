package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.repo.BicicletaRepo;
import isel.sisinf.model.dto.BicicletaDTO;

import java.util.List;

public class BicicletaService {

    private static int nextNumeroBicicleta = 1;

    public BicicletaService()
    {
        dal = new Dal();
    }

    public void createBicicleta(BicicletaDTO bicicletaDTO)
    {
        Bicicleta bicicleta = convertToEntity(bicicletaDTO);
        BicicletaRepo.BicicletaRepository.addBicicleta(bicicleta);
        System.out.println("Bicicleta adicionada com sucesso");
        nextNumeroBicicleta++;
    }

    private Bicicleta convertToEntity(BicicletaDTO dto) {
        return new Bicicleta(nextNumeroBicicleta++, dto.getIdentificador(), dto.getPesoGramas(), dto.getModelo(), dto.getMarca(), dto.getNumeroVelocidades(), dto.getEstado(), dto.getAutonomia());
    }

    private BicicletaService bicicletaService;
    private static Dal dal;


    public static List<BicicletaDTO> listBikes() {
        return dal.listBikes();
    }


    public static boolean checkBikeAvailability(BicicletaDTO bike) {
        return dal.checkBikeAvailability(bike);
    }


}
