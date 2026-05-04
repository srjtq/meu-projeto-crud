package com.exemplo.crud.controller;

import com.exemplo.crud.dto.ClienteRequestDTO;
import com.exemplo.crud.dto.ClienteResponseDTO;
import com.exemplo.crud.model.Cliente;
import com.exemplo.crud.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> cadastrar(
            @RequestBody ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEndereco(dto.getEndereco());
        cliente.setDataNascimento(dto.getDataNascimento());

        Cliente salvo = clienteService.cadastrar(cliente);

        ClienteResponseDTO response = new ClienteResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getCpf(),
                salvo.getEndereco(),
                salvo.getDataNascimento()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listar() {

        List<ClienteResponseDTO> lista = clienteService.listarTodos()
                .stream()
                .map(c -> new ClienteResponseDTO(
                        c.getId(),
                        c.getNome(),
                        c.getCpf(),
                        c.getEndereco(),
                        c.getDataNascimento()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>remover(@PathVariable Long id) {

        clienteService.remover(id);

        return ResponseEntity.noContent().build();
    }

@PutMapping("/{id})")
    public ResponseEntity<ClienteResponseDTO>atualizar(
            @PathVariable Long id,
            @RequestBody ClienteRequestDTO dto) {

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEndereco(dto.getEndereco());
        cliente.setDataNascimento(dto.getDataNascimento());

        Cliente atualizado = clienteService.atualizar(id, cliente);

        ClienteResponseDTO response = new ClienteResponseDTO(
            atualizado.getId(),
            atualizado.getNome(),
            atualizado.getCpf(),
            atualizado.getEndereco(),
            atualizado.getDataNascimento()
    );

    return ResponseEntity.ok(response);
}






}

