package com.exemplo.crud.service;

import com.exemplo.crud.dto.LoginRequestDTO;
import com.exemplo.crud.enums.NivelAcesso;
import com.exemplo.crud.exception.RegraNegocioException;
import com.exemplo.crud.model.Usuario;
import com.exemplo.crud.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(String username, String senha, NivelAcesso nivelAcesso) {

        usuarioRepository.findByUsername(username)
                .ifPresent(u -> {
                    throw new RegraNegocioException("Usuário já existe");
                });

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setNivelAcesso(nivelAcesso);

        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(LoginRequestDTO dto) {

        Usuario usuario = usuarioRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RegraNegocioException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RegraNegocioException("Usuário ou senha inválidos");
        }

        return usuario;
    }
}

