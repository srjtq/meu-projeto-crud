package com.exemplo.crud.service;

import com.exemplo.crud.dto.ItemVendaRequestDTO;
import com.exemplo.crud.dto.VendaRequestDTO;
import com.exemplo.crud.exception.RegraNegocioException;
import com.exemplo.crud.model.Cliente;
import com.exemplo.crud.model.Disco;
import com.exemplo.crud.model.Venda;
import com.exemplo.crud.model.enums.FormaPagamento;
import com.exemplo.crud.repository.ClienteRepository;
import com.exemplo.crud.repository.DiscoRepository;
import com.exemplo.crud.repository.VendaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VendaServiceTest {

    @Mock
    private VendaRepository vendaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private DiscoRepository discoRepository;

    @InjectMocks
    private VendaService vendaService;

    // ✅ TESTE 1: REALIZAR VENDA COM SUCESSO
    @Test
    void deveRealizarVendaComSucesso() {

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");

        Disco disco = new Disco();
        disco.setCodigoDisco("D001");
        disco.setNomeDisco("Disco Teste");

        ItemVendaRequestDTO itemDto = new ItemVendaRequestDTO();
        itemDto.setDiscoId(1L);
        itemDto.setQuantidade(2);
        itemDto.setPrecoUnitario(new BigDecimal("50.00"));

        VendaRequestDTO vendaDto = new VendaRequestDTO();
        vendaDto.setClienteId(1L);
        vendaDto.setFormaPagamento(FormaPagamento.PIX);
        vendaDto.setItens(List.of(itemDto));

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));
        when(discoRepository.findById(1L))
                .thenReturn(Optional.of(disco));
        when(vendaRepository.save(any(Venda.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Venda venda = vendaService.realizarVenda(vendaDto);

        assertNotNull(venda);
        assertEquals(cliente, venda.getCliente());
        assertEquals(FormaPagamento.PIX, venda.getFormaPagamento());
        assertEquals(1, venda.getItens().size());

        BigDecimal valorEsperado = new BigDecimal("100.00");
        assertEquals(0, valorEsperado.compareTo(venda.getValorTotal()));

        verify(vendaRepository).save(any(Venda.class));
    }

    // ✅ TESTE 2: NÃO DEVE PERMITIR QUANTIDADE INVÁLIDA
    @Test
    void naoDevePermitirQuantidadeInvalida() {

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");

        ItemVendaRequestDTO itemDto = new ItemVendaRequestDTO();
        itemDto.setDiscoId(1L);
        itemDto.setQuantidade(0); // ❌ inválida
        itemDto.setPrecoUnitario(new BigDecimal("50.00"));

        VendaRequestDTO vendaDto = new VendaRequestDTO();
        vendaDto.setClienteId(1L);
        vendaDto.setFormaPagamento(FormaPagamento.PIX);
        vendaDto.setItens(List.of(itemDto));

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        assertThrows(RegraNegocioException.class,
                () -> vendaService.realizarVenda(vendaDto));

        verify(vendaRepository, never()).save(any());
        verify(discoRepository, never()).findById(any());
    }

    // ✅ TESTE 3: NÃO DEVE PERMITIR VENDA COM CLIENTE INEXISTENTE
    @Test
    void naoDevePermitirVendaComClienteInexistente() {

        ItemVendaRequestDTO itemDto = new ItemVendaRequestDTO();
        itemDto.setDiscoId(1L);
        itemDto.setQuantidade(1);
        itemDto.setPrecoUnitario(new BigDecimal("50.00"));

        VendaRequestDTO vendaDto = new VendaRequestDTO();
        vendaDto.setClienteId(1L);
        vendaDto.setFormaPagamento(FormaPagamento.PIX);
        vendaDto.setItens(List.of(itemDto));

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RegraNegocioException.class,
                () -> vendaService.realizarVenda(vendaDto));

        verify(discoRepository, never()).findById(any());
        verify(vendaRepository, never()).save(any());
    }

    // ✅ TESTE 4: NÃO DEVE PERMITIR VENDA COM DISCO INEXISTENTE
    @Test
    void naoDevePermitirVendaComDiscoInexistente() {

        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");

        ItemVendaRequestDTO itemDto = new ItemVendaRequestDTO();
        itemDto.setDiscoId(1L);
        itemDto.setQuantidade(1);
        itemDto.setPrecoUnitario(new BigDecimal("50.00"));

        VendaRequestDTO vendaDto = new VendaRequestDTO();
        vendaDto.setClienteId(1L);
        vendaDto.setFormaPagamento(FormaPagamento.PIX);
        vendaDto.setItens(List.of(itemDto));

        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        // ❌ disco não existe
        when(discoRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RegraNegocioException.class,
                () -> vendaService.realizarVenda(vendaDto));

        verify(vendaRepository, never()).save(any());
    }
}
