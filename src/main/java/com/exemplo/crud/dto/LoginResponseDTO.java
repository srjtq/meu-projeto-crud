package com.exemplo.crud.dto;

import com.exemplo.crud.enums.NivelAcesso;

public class LoginResponseDTO {

    private String username;
    private NivelAcesso nivelAcesso;

    public LoginResponseDTO(String username, NivelAcesso nivelAcesso) {
        this.username = username;
        this.nivelAcesso = nivelAcesso;
    }

    public String getUsername() {
        return username;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }
}

