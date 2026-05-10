package com.exemplo.crud.controller;

import com.exemplo.crud.dto.DiscoPatchDTO;
import com.exemplo.crud.dto.DiscoRequestDTO;
import com.exemplo.crud.dto.DiscoResponseDTO;
import com.exemplo.crud.model.Disco;
import com.exemplo.crud.service.DiscoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discos")
@Tag(name = "Discos", description = "Operações relacionadas a discos musicais")
public class DiscoController {

    private final DiscoService discoService;

    public DiscoController(DiscoService discoService) {
        this.discoService = discoService;
    }

    // ✅ POST - cadastrar disco
    @Operation(summary = "Cadastrar disco", description = "Cadastra um novo disco")
    @PostMapping
    public ResponseEntity<DiscoResponseDTO> cadastrar(
            @Valid @RequestBody DiscoRequestDTO dto) {

        Disco disco = new Disco();
        disco.setCodigoDisco(dto.getCodigoDisco());
        disco.setNomeDisco(dto.getNomeDisco());
        disco.setArtista(dto.getArtista());
        disco.setGenero(dto.getGenero()); // ✅ enum
        disco.setAnoLancamento(dto.getAnoLancamento());

        Disco salvo = discoService.cadastrar(disco);

        return ResponseEntity.ok(new DiscoResponseDTO(
                salvo.getId(),
                salvo.getCodigoDisco(),
                salvo.getNomeDisco(),
                salvo.getArtista(),
                salvo.getGenero(), // ✅ enum
                salvo.getAnoLancamento()
        ));
    }

    // ✅ POST - cadastrar discos em lote
    @Operation(
            summary = "Cadastrar discos em lote",
            description = "Cadastra mais de um disco na mesma operação"
    )
    @PostMapping("/lote")
    public ResponseEntity<List<DiscoResponseDTO>> cadastrarEmLote(
            @Valid @RequestBody List<DiscoRequestDTO> dtos) {

        List<DiscoResponseDTO> resposta = dtos.stream()
                .map(dto -> {
                    Disco disco = new Disco();
                    disco.setCodigoDisco(dto.getCodigoDisco());
                    disco.setNomeDisco(dto.getNomeDisco());
                    disco.setArtista(dto.getArtista());
                    disco.setGenero(dto.getGenero()); // ✅ enum
                    disco.setAnoLancamento(dto.getAnoLancamento());

                    Disco salvo = discoService.cadastrar(disco);

                    return new DiscoResponseDTO(
                            salvo.getId(),
                            salvo.getCodigoDisco(),
                            salvo.getNomeDisco(),
                            salvo.getArtista(),
                            salvo.getGenero(), // ✅ enum
                            salvo.getAnoLancamento()
                    );
                })
                .toList();

        return ResponseEntity.ok(resposta);
    }

    // ✅ GET - listar discos
    @Operation(summary = "Listar discos", description = "Lista todos os discos cadastrados")
    @GetMapping
    public ResponseEntity<List<DiscoResponseDTO>> listar() {

        List<DiscoResponseDTO> lista = discoService.listar()
                .stream()
                .map(d -> new DiscoResponseDTO(
                        d.getId(),
                        d.getCodigoDisco(),
                        d.getNomeDisco(),
                        d.getArtista(),
                        d.getGenero(), // ✅ enum
                        d.getAnoLancamento()
                ))
                .toList();

        return ResponseEntity.ok(lista);
    }

    // ✅ PUT - atualizar disco
    @Operation(summary = "Atualizar disco", description = "Atualiza um disco pelo ID")
    @PutMapping("/{id}")
    public ResponseEntity<DiscoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody DiscoRequestDTO dto) {

        Disco disco = new Disco();
        disco.setCodigoDisco(dto.getCodigoDisco());
        disco.setNomeDisco(dto.getNomeDisco());
        disco.setArtista(dto.getArtista());
        disco.setGenero(dto.getGenero()); // ✅ enum
        disco.setAnoLancamento(dto.getAnoLancamento());

        Disco atualizado = discoService.atualizar(id, disco);

        return ResponseEntity.ok(new DiscoResponseDTO(
                atualizado.getId(),
                atualizado.getCodigoDisco(),
                atualizado.getNomeDisco(),
                atualizado.getArtista(),
                atualizado.getGenero(), // ✅ enum
                atualizado.getAnoLancamento()
        ));
    }

    // ✅ PATCH - atualização parcial
    @Operation(summary = "Atualizar parcialmente disco", description = "Atualiza parcialmente um disco pelo ID")
    @PatchMapping("/{id}")
    public ResponseEntity<DiscoResponseDTO> atualizarParcial(
            @PathVariable Long id,
            @RequestBody DiscoPatchDTO dto) {

        Disco atualizado = discoService.atualizarParcial(id, dto);

        return ResponseEntity.ok(new DiscoResponseDTO(
                atualizado.getId(),
                atualizado.getCodigoDisco(),
                atualizado.getNomeDisco(),
                atualizado.getArtista(),
                atualizado.getGenero(), // ✅ enum
                atualizado.getAnoLancamento()
        ));
    }

    // ✅ DELETE - remover disco
    @Operation(summary = "Remover disco", description = "Remove um disco pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        discoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
