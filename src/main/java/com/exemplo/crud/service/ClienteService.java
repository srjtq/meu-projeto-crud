package com.exemplo.crud.service;

import com.exemplo.crud.model.Cliente;
import com.exemplo.crud.repository.ClienteRepository;
import com.exemplo.crud.exception.RegraNegocioException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrar(Cliente cliente) {

        // regra de negócio: CPF não pode repetir
        clienteRepository.findByCpf(cliente.getCpf())
                .ifPresent(c -> {
                    throw new RegraNegocioException("CPF já cadastrado");
                });

        return clienteRepository.save(cliente);
    }

    public void remover(Long id) {

        if (!clienteRepository.existsById(id)) {
            throw new RegraNegocioException("Cliente não encontrado");
        }

        clienteRepository.deleteById(id);

    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
}

