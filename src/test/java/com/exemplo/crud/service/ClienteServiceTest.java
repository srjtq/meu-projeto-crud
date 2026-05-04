package com.exemplo.crud.service;

import com.exemplo.crud.exception.RegraNegocioException;
import com.exemplo.crud.model.Cliente;
import com.exemplo.crud.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveCadastrarClienteComSucesso() {
        Cliente cliente = criarCliente();

        when(clienteRepository.findByCpf(cliente.getCpf()))
                .thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class)))
                .thenReturn(cliente);

        Cliente salvo = clienteService.cadastrar(cliente);

        assertNotNull(salvo);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void naoDeveCadastrarClienteComCpfDuplicado() {
        Cliente cliente = criarCliente();

        when(clienteRepository.findByCpf(cliente.getCpf()))
                .thenReturn(Optional.of(cliente));

        assertThrows(RegraNegocioException.class,
                () -> clienteService.cadastrar(cliente));
    }

    @Test
    void deveAtualizarClienteComMesmoCpf() {
        Cliente existente = criarCliente();
        existente.setId(1L);

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(existente));
        when(clienteRepository.findByCpf(existente.getCpf()))
                .thenReturn(Optional.of(existente));
        when(clienteRepository.save(any()))
                .thenReturn(existente);

        Cliente atualizado = clienteService.atualizar(1L, existente);

        assertEquals("João Silva", atualizado.getNome());
    }

    @Test
    void naoDeveAtualizarClienteComCpfDeOutro() {
        Cliente existente = criarCliente();
        existente.setId(1L);

        Cliente outro = criarCliente();
        outro.setId(2L);

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(existente));
        when(clienteRepository.findByCpf(outro.getCpf()))
                .thenReturn(Optional.of(outro));

        assertThrows(RegraNegocioException.class,
                () -> clienteService.atualizar(1L, outro));
    }

    @Test
    void deveRemoverClienteExistente() {
        when(clienteRepository.existsById(1L))
                .thenReturn(true);

        clienteService.remover(1L);

        verify(clienteRepository).deleteById(1L);
    }

    @Test
    void naoDeveRemoverClienteInexistente() {
        when(clienteRepository.existsById(1L))
                .thenReturn(false);

        assertThrows(RegraNegocioException.class,
                () -> clienteService.remover(1L));
    }

    private Cliente criarCliente() {
        Cliente c = new Cliente();
        c.setNome("João Silva");
        c.setCpf("12345678900");
        c.setEndereco("Rua A");
        c.setDataNascimento(LocalDate.of(1990, 1, 1));
        return c;
    }
}
