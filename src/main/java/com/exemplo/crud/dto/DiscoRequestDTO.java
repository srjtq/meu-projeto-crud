package com.exemplo.crud.dto;

import com.exemplo.crud.model.enums.GeneroDisco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DiscoRequestDTO {

    @NotBlank
    private String codigoDisco;

    @NotBlank
    private String nomeDisco;

    @NotBlank
    private String artista;

    // ✅ AGORA É ENUM (NÃO STRING)
    @NotNull
    private GeneroDisco genero;

    @NotNull
    private Integer anoLancamento;

    public String getCodigoDisco() {
        return codigoDisco;
    }

    public void setCodigoDisco(String codigoDisco) {
        this.codigoDisco = codigoDisco;
    }

    public String getNomeDisco() {
        return nomeDisco;
    }

    public void setNomeDisco(String nomeDisco) {
        this.nomeDisco = nomeDisco;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public GeneroDisco getGenero() {
        return genero;
    }

    public void setGenero(GeneroDisco genero) {
        this.genero = genero;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
}
