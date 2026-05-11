package com.exemplo.crud.controller;

import com.exemplo.crud.dto.LoginRequestDTO;
import com.exemplo.crud.dto.LoginResponseDTO;
import com.exemplo.crud.model.Usuario;
import com.exemplo.crud.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO dto) {

        Usuario usuario = usuarioService.autenticar(dto);

        return ResponseEntity.ok(
                new LoginResponseDTO(
                        usuario.getUsername(),
                        usuario.getNivelAcesso()
                )
        );
    }
}

