package com.exemplo.crud.model;

import com.exemplo.crud.model.enums.GeneroDisco;
import jakarta.persistence.*;

@Entity
@Table(name = "discos")
public class Disco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoDisco;

    @Column(nullable = false)
    private String nomeDisco;

    @Column(nullable = false)
    private String artista;

    // ✅ AGORA CORRETO: ENUM DE VERDADE
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeneroDisco genero;

    @Column(nullable = false)
    private Integer anoLancamento;

    // construtor vazio (JPA)
    public Disco() {
    }

    // getters e setters

    public Long getId() {
        return id;
    }

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
