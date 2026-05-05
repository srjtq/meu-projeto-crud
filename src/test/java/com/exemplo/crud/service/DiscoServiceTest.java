package com.exemplo.crud.service;

import com.exemplo.crud.exception.RegraNegocioException;
import com.exemplo.crud.model.Disco;
import com.exemplo.crud.repository.DiscoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscoServiceTest {

    @Mock
    private DiscoRepository discoRepository;

    @InjectMocks
    private DiscoService discoService;

    // ✅ TESTE 1: CADASTRO COM SUCESSO
    @Test
    void deveCadastrarDiscoComSucesso() {
        Disco disco = criarDisco();

        when(discoRepository.findByCodigoDisco(disco.getCodigoDisco()))
                .thenReturn(Optional.empty());
        when(discoRepository.save(any(Disco.class)))
                .thenReturn(disco);

        Disco salvo = discoService.cadastrar(disco);

        assertNotNull(salvo);
        assertEquals("D001", salvo.getCodigoDisco());
        verify(discoRepository).save(disco);
    }

    // ✅ TESTE 2: NÃO DEVE CADASTRAR COM CÓDIGO DUPLICADO
    @Test
    void naoDeveCadastrarDiscoComCodigoDuplicado() {
        Disco disco = criarDisco();

        when(discoRepository.findByCodigoDisco(disco.getCodigoDisco()))
                .thenReturn(Optional.of(disco));

        assertThrows(RegraNegocioException.class,
                () -> discoService.cadastrar(disco));

        verify(discoRepository, never()).save(any());
    }

    // ✅ TESTE 3: ATUALIZAR DISCO COM SUCESSO
    @Test
    void deveAtualizarDiscoComSucesso() {
        Disco existente = criarDisco();
        existente.setId(1L);

        Disco atualizado = criarDisco();
        atualizado.setNomeDisco("Disco Atualizado");

        when(discoRepository.findById(1L))
                .thenReturn(Optional.of(existente));
        when(discoRepository.findByCodigoDisco(atualizado.getCodigoDisco()))
                .thenReturn(Optional.of(existente));
        when(discoRepository.save(any(Disco.class)))
                .thenReturn(existente);

        Disco resultado = discoService.atualizar(1L, atualizado);

        assertEquals("Disco Atualizado", resultado.getNomeDisco());
        verify(discoRepository).save(existente);
    }

    // ✅ TESTE 4: NÃO DEVE ATUALIZAR COM CÓDIGO DE OUTRO DISCO
    @Test
    void naoDeveAtualizarComCodigoDeOutroDisco() {
        Disco existente = criarDisco();
        existente.setId(1L);

        Disco outro = criarDisco();
        outro.setId(2L);
        outro.setCodigoDisco("D002");

        when(discoRepository.findById(1L))
                .thenReturn(Optional.of(existente));
        when(discoRepository.findByCodigoDisco("D002"))
                .thenReturn(Optional.of(outro));

        assertThrows(RegraNegocioException.class,
                () -> discoService.atualizar(1L, outro));
    }

    // ✅ TESTE 5: REMOVER DISCO EXISTENTE
    @Test
    void deveRemoverDiscoExistente() {
        when(discoRepository.existsById(1L))
                .thenReturn(true);

        discoService.remover(1L);

        verify(discoRepository).deleteById(1L);
    }

    // ✅ TESTE 6: NÃO DEVE REMOVER DISCO INEXISTENTE
    @Test
    void naoDeveRemoverDiscoInexistente() {
        when(discoRepository.existsById(1L))
                .thenReturn(false);

        assertThrows(RegraNegocioException.class,
                () -> discoService.remover(1L));
    }

    // 🔧 MÉTODO AUXILIAR PARA CRIAR DISCO
    private Disco criarDisco() {
        Disco d = new Disco();
        d.setCodigoDisco("D001");
        d.setNomeDisco("Disco Teste");
        d.setArtista("Artista Teste");
        d.setGenero("Rock");
        d.setAnoLancamento(1999);
        return d;
    }
}
