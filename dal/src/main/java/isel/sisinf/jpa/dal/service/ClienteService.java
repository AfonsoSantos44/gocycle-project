package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.entity.Dal;
import isel.sisinf.jpa.dal.repo.IClienteRepo;
import isel.sisinf.model.dto.ClienteDTO;


public class ClienteService {

    private Dal dal;

    public ClienteService() {
        this.dal = new Dal();
    }

    public void createClient(String nome, String morada, String enderecoEletronico, String numeroTelefone) {
        // Create a ClienteDTO object with the provided information
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome(nome);
        clienteDTO.setMorada(morada);
        clienteDTO.setEnderecoEletronico(enderecoEletronico);
        clienteDTO.setNumeroTelefone(numeroTelefone);

        // Pass the ClienteDTO object to the createCustomer method of the Dal class
        dal.createCustomer(clienteDTO);

        System.out.println("Customer created successfully!");
    }
}
