package com.exemplo.crud.dto;

import java.math.BigDecimal;

public class ItemVendaRequestDTO {

    private Long discoId;
    private Integer quantidade;
    private BigDecimal precoUnitario;

    // getters e setters


    public Long getDiscoId() {
        return discoId;
    }

    public void setDiscoId(Long discoId) {
        this.discoId = discoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
