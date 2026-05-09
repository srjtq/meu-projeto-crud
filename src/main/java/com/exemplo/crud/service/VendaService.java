package com.exemplo.crud.service;

import com.exemplo.crud.dto.VendaRequestDTO;
import com.exemplo.crud.exception.RegraNegocioException;
import com.exemplo.crud.model.Cliente;
import com.exemplo.crud.model.Disco;
import com.exemplo.crud.model.ItemVenda;
import com.exemplo.crud.model.Venda;
import com.exemplo.crud.repository.ClienteRepository;
import com.exemplo.crud.repository.DiscoRepository;
import com.exemplo.crud.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final DiscoRepository discoRepository;

    public VendaService(VendaRepository vendaRepository,
                        ClienteRepository clienteRepository,
                        DiscoRepository discoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.discoRepository = discoRepository;
    }

    public Venda realizarVenda(VendaRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setDataVenda(LocalDateTime.now());

        List<ItemVenda> itens = dto.getItens().stream().map(itemDto -> {

            Disco disco = discoRepository.findById(itemDto.getDiscoId())
                    .orElseThrow(() -> new RegraNegocioException("Disco não encontrado"));

            ItemVenda item = new ItemVenda();
            item.setVenda(venda);
            item.setDisco(disco);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(itemDto.getPrecoUnitario());

            // ✅ subtotal = quantidade × preço
            BigDecimal subtotal = itemDto.getPrecoUnitario()
                    .multiply(BigDecimal.valueOf(itemDto.getQuantidade()));
            item.setSubtotal(subtotal);

            return item;
        }).toList();

        venda.setItens(itens);

        // ✅ calcular valor total da venda
        BigDecimal total = itens.stream()
                .map(ItemVenda::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        venda.setValorTotal(total);

        return vendaRepository.save(venda);
    }
}

