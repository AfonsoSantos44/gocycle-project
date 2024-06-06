package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.repo.ClientRepo;
import isel.sisinf.model.dto.ClienteDTO;


public class ClienteService {

    private static int nextNumeroCliente = 1;

    public void createClient(Cliente cliente)
    {
        Cliente clienteEntity = convertToEntity(cliente);
        ClientRepo.ClienteRepository.addCliente(clienteEntity);
        System.out.println("Cliente adicionado com sucesso");
        nextNumeroCliente++;
    }

    private Cliente convertToEntity(Cliente cliente) {
        return new Cliente(nextNumeroCliente, cliente.getNome(), cliente.getMorada(), cliente.getNumeroTelefone(), cliente.getEnderecoEletronico(), cliente.getNumeroCCPassaporte(), cliente.getNacionalidade());
    }

}

