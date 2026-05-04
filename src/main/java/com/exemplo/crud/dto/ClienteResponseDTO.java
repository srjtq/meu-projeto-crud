package com.exemplo.crud.dto;

import java.time.LocalDate;

public class ClienteResponseDTO{

    private Long id;
    private String nome;
    private String cpf;
    private String endereco;
    private LocalDate dataNascimento;

    public ClienteResponseDTO(
            Long id,
            String nome,
            String cpf,
            String endereço,
            LocalDate dataNascimento){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereço;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getCpf() {
        return cpf;
    }


    public String getEndereco() {
        return endereco;
    }


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
}
