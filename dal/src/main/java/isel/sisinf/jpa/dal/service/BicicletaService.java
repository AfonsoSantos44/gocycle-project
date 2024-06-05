package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Bicicleta;
import isel.sisinf.jpa.dal.repo.BicicletaRepo;


import java.util.List;

public class BicicletaService {

    public static List<Bicicleta> listBikes() {
         // list all bikes using the function in BicicletaRepo
        return BicicletaRepo.BicicletaRepository.listBikes();
    }

}
