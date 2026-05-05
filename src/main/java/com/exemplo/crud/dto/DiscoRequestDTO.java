package com.exemplo.crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DiscoRequestDTO {

    @NotBlank(message = "Código do disco é obrigatório")
    @Schema(example = "D001", description = "Código único do disco")
    private String codigoDisco;


    @NotBlank(message = "Nome do disco é obrigatório")
    @Schema(example = "Hybrid Theory", description = "Nome do disco")
    private String nomeDisco;


    @NotBlank(message = "Artista é obrigatório")
    @Schema(example = "Linkin Park", description = "Artista ou banda")
    private String artista;


    @NotBlank(message = "Gênero é obrigatório")
    @Schema(example = "Rock", description = "Gênero musical")
    private String genero;


    @NotNull(message = "Ano de lançamento é obrigatório")
    @Positive(message = "Ano de lançamento deve ser positivo")
    @Schema(example = "2000", description = "Ano de lançamento")
    private Integer anoLancamento;

    //GETTERS E SETTERS

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
}
