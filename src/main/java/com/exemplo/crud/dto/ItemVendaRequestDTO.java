package com.exemplo.crud.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemVendaRequestDTO {

    @NotNull(message = "Disco é obrigatório")
    private Long discoId;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantidade;

    // ✅ VALIDAÇÃO DE FORMATO / VALOR
    @NotNull(message = "Preço unitário é obrigatório")
    @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
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
