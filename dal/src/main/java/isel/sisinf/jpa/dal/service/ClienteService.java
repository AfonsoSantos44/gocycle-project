package isel.sisinf.jpa.dal.service;

import isel.sisinf.jpa.dal.entity.Cliente;
import isel.sisinf.jpa.dal.repo.ClientRepo;

import java.util.List;


public class ClienteService {

    public void createClient(Cliente cliente) {
        // Obtém a lista de clientes existentes
        List<Cliente> clientes = ClientRepo.ClienteRepository.listClientes();

        int numeroCliente = clientes.size() + 1;
        Cliente clienteEntity = convertToEntity(cliente, numeroCliente);

        // Adiciona o cliente ao repositório
        ClientRepo.ClienteRepository.addCliente(clienteEntity);
    }

    private Cliente convertToEntity(Cliente cliente, int numeroCliente) {
        return new Cliente(numeroCliente, cliente.getNome(), cliente.getMorada(), cliente.getNumeroTelefone(), cliente.getEnderecoEletronico(), cliente.getNumeroCCPassaporte(), cliente.getNacionalidade());
    }

    public boolean checkIfExistsCCPassaporte(String numeroCCPassaporte) {
        List<Cliente> clientes = ClientRepo.ClienteRepository.listClientes();
        for (Cliente cliente : clientes) {
            if (cliente.getNumeroCCPassaporte().equals(numeroCCPassaporte)) {
                return true;
            }
        }
        return false;
    }
}

