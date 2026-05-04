package com.exemplo.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClienteRequestDTO{

@NotBlank(message = "Nome é obrigatório")
@Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
private String nome;

@NotBlank(message = "CPF é obrigatório")
@Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
private String cpf;

@NotBlank(message = "Endereço é obrigatório")
private String endereco;

@NotNull(message = "Data de nascimento é obrigatória")
@Past(message = "Data de nascimento deve ser inferior a data atual")
private LocalDate dataNascimento;


// GETTERS E SETTERS
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

