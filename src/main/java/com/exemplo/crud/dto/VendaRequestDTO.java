package com.exemplo.crud.dto;

import com.exemplo.crud.model.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class VendaRequestDTO {

    @NotNull
    private Long clienteId;

    @NotNull
    private FormaPagamento formaPagamento;

    @NotNull
    private List<ItemVendaRequestDTO> itens;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ItemVendaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaRequestDTO> itens) {
        this.itens = itens;
    }
}
