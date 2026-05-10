package com.exemplo.crud.dto;

import com.exemplo.crud.model.enums.GeneroDisco;

public class DiscoPatchDTO {

    private String codigoDisco;
    private String nomeDisco;
    private String artista;

    // ✅ PATCH também usa ENUM
    private GeneroDisco genero;

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
