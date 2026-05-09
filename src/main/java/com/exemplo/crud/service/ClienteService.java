package com.exemplo.crud.service;

import com.exemplo.crud.model.Cliente;
import com.exemplo.crud.repository.ClienteRepository;
import com.exemplo.crud.exception.RegraNegocioException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    //injeção de dependência do repository
    private final ClienteRepository clienteRepository;

    //construtor da injeção de dependência do repository
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

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new RegraNegocioException("Cliente não encontrado")
                );

        clienteRepository.findByCpf(clienteAtualizado.getCpf())
                .ifPresent(clienteComMesmoCpf -> {
                    if (!clienteComMesmoCpf.getId().equals(id)) {
                        throw new RegraNegocioException("CPF já cadastrado");
                    }
                });

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setDataNascimento(clienteAtualizado.getDataNascimento());

        return clienteRepository.save(clienteExistente);
    }

    // Lista com paginas
    public Page<Cliente> listarPaginado(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    // Lista sem paginas
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
}

