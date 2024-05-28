package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.repo.ClientRepo;
import isel.sisinf.model.dto.ClienteDTO;


public class ClienteService {

    private static int nextnumeroCliente = 1;

    public ClienteService() {
        Dal dal = new Dal();
    }

    public void createClient(ClienteDTO clienteDTO)
    {
        Cliente cliente = convertToEntity(clienteDTO);
        ClientRepo.ClienteRepository.addCliente(cliente);
        System.out.println("Cliente adicionado com sucesso");
        nextnumeroCliente++;
    }

    private Cliente convertToEntity(ClienteDTO dto) {
        return new Cliente(nextnumeroCliente++, dto.getNome(), dto.getMorada(), dto.getNumeroTelefone(), dto.getEnderecoEletronico(), dto.getNumeroCCPassaporte(), dto.getNacionalidade());
    }
}
