package com.exemplo.crud.model;

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

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Integer anoLancamento;

    //CONSTRUTOR VAZIO
    public Disco(){

    }


    //GETTERS E SETTERS


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
