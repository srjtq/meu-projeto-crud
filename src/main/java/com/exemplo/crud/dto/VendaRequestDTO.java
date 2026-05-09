package com.exemplo.crud.dto;

import java.util.List;

public class VendaRequestDTO {

    private Long clienteId;
    private List<ItemVendaRequestDTO> itens;

    // getters e setters

    public List<ItemVendaRequestDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemVendaRequestDTO> itens) {
        this.itens = itens;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }
}
