package com.exemplo.crud.dto;

import com.exemplo.crud.model.enums.GeneroDisco;
import io.swagger.v3.oas.annotations.media.Schema;

public class DiscoResponseDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "D001")
    private String codigoDisco;

    @Schema(example = "Hybrid Theory")
    private String nomeDisco;

    @Schema(example = "Linkin Park")
    private String artista;

    @Schema(example = "ROCK")
    private GeneroDisco genero;

    @Schema(example = "2000")
    private Integer anoLancamento;

    // ✅ CONSTRUTOR ALINHADO COM ENUM
    public DiscoResponseDTO(Long id,
                            String codigoDisco,
                            String nomeDisco,
                            String artista,
                            GeneroDisco genero,
                            Integer anoLancamento) {
        this.id = id;
        this.codigoDisco = codigoDisco;
        this.nomeDisco = nomeDisco;
        this.artista = artista;
        this.genero = genero;
        this.anoLancamento = anoLancamento;
    }

    // getters

    public Long getId() {
        return id;
    }

    public String getCodigoDisco() {
        return codigoDisco;
    }

    public String getNomeDisco() {
        return nomeDisco;
    }

    public String getArtista() {
        return artista;
    }

    public GeneroDisco getGenero() {
        return genero;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }
}
