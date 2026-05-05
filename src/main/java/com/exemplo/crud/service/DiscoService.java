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

   public Disco cadastrar(Disco disco){

        //regra não pode ter disco já cadastrado, senão retorna erro
       discoRepository.findByCodigoDisco(disco.getCodigoDisco())
               .ifPresent(d -> {
                   throw new RegraNegocioException("Código do disco já cadastrado");
               });

       return discoRepository.save(disco);
   }

    public List<Disco> listar() {
        return discoRepository.findAll();
    }

    //Atualização
    public Disco atualizar(Long id, Disco discoAtualizado) {

        //regra 1: testa se o código do disco existe para ser atualizado, caso não exista retorna mensagem erro
        Disco existente = discoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Disco não encontrado"));

        //regra 2: testa se o código do disco já está cadastrado, se sim retorna mensagem de erro
        discoRepository.findByCodigoDisco(discoAtualizado.getCodigoDisco())
                .ifPresent(outro -> {
                    if (!outro.getId().equals(id)) {
                        throw new RegraNegocioException("Código do disco já cadastrado");
                    }
                });

        //
        existente.setCodigoDisco(discoAtualizado.getCodigoDisco());
        existente.setNomeDisco(discoAtualizado.getNomeDisco());
        existente.setArtista(discoAtualizado.getArtista());
        existente.setGenero(discoAtualizado.getGenero());
        existente.setAnoLancamento(discoAtualizado.getAnoLancamento());

        return discoRepository.save(existente);
    }

   //Deleção
    //regra: verifica se o código do disco existe, caso contrário retorna mensaagem de erro
    public void remover(Long id) {
        if (!discoRepository.existsById(id)) {
            throw new RegraNegocioException("Disco não encontrado");
        }
        discoRepository.deleteById(id);
    }
}