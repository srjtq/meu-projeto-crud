package com.exemplo.crud.service;

import com.exemplo.crud.exception.RegraNegocioException;
import com.exemplo.crud.model.Disco;
import com.exemplo.crud.repository.DiscoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscoService {

    private final DiscoRepository discoRepository;

    public DiscoService(DiscoRepository discoRepository) {
        this.discoRepository = discoRepository;
    }

    public Disco cadastrar(Disco disco) {

        discoRepository.findByCodigoDisco(disco.getCodigoDisco())
                .ifPresent(d -> {
                    throw new RegraNegocioException("Código do disco já cadastrado");
                });

        return discoRepository.save(disco);
    }

    public List<Disco> listar() {
        return discoRepository.findAll();
    }

    public Disco atualizar(Long id, Disco discoAtualizado) {

        Disco existente = discoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Disco não encontrado"));

        discoRepository.findByCodigoDisco(discoAtualizado.getCodigoDisco())
                .ifPresent(outro -> {
                    // ✅ COMPARAÇÃO SEGURA (SEM NPE)
                    if (!id.equals(outro.getId())) {
                        throw new RegraNegocioException("Código do disco já cadastrado");
                    }
                });

        existente.setCodigoDisco(discoAtualizado.getCodigoDisco());
        existente.setNomeDisco(discoAtualizado.getNomeDisco());
        existente.setArtista(discoAtualizado.getArtista());
        existente.setGenero(discoAtualizado.getGenero());
        existente.setAnoLancamento(discoAtualizado.getAnoLancamento());

        return discoRepository.save(existente);
    }

    public Disco atualizarParcial(Long id, com.exemplo.crud.dto.DiscoPatchDTO dto) {

        Disco existente = discoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Disco não encontrado"));

        if (dto.getCodigoDisco() != null) {
            existente.setCodigoDisco(dto.getCodigoDisco());
        }

        if (dto.getNomeDisco() != null) {
            existente.setNomeDisco(dto.getNomeDisco());
        }

        if (dto.getArtista() != null) {
            existente.setArtista(dto.getArtista());
        }

        if (dto.getGenero() != null) {
            existente.setGenero(dto.getGenero());
        }

        if (dto.getAnoLancamento() != null) {
            existente.setAnoLancamento(dto.getAnoLancamento());
        }

        return discoRepository.save(existente);
    }

    public void remover(Long id) {
        if (!discoRepository.existsById(id)) {
            throw new RegraNegocioException("Disco não encontrado");
        }
        discoRepository.deleteById(id);
    }
}
