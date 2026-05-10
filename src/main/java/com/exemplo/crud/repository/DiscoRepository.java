package com.exemplo.crud.repository;

import com.exemplo.crud.model.Disco;
import com.exemplo.crud.model.enums.GeneroDisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {

    Optional<Disco> findByCodigoDisco(String codigoDisco);

    // ✅ ALINHADO COM ENUM
    // Mesmo que você não use agora, garante consistência de tipos
    List<Disco> findByGenero(GeneroDisco genero);
}


