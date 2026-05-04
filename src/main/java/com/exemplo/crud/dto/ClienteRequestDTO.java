package com.exemplo.crud.dto;

import java.time.LocalDate;

public class ClienteRequestDTO{

private String nome;
private String cpf;
private String endereco;
private LocalDate dataNascimento;

public String getNome(){
    return nome;
}


public void setNome(String nome){
    this.nome = nome;
}


public String getCpf(){
    return cpf;
}


public void setCpf(String cpf){
    this.cpf = cpf;
}


public String getEndereco(){
    return endereco;
}


public void setEndereco(String endereco) {
    this.endereco = endereco;
}


public LocalDate getDataNascimento() {
    return dataNascimento;
}


public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
}
}

