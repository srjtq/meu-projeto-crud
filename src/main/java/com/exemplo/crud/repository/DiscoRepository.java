package com.exemplo.crud.repository;

import com.exemplo.crud.model.Disco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscoRepository extends JpaRepository<Disco, Long> {

    Optional<Disco>findByCodigoDisco(String codigoDisco);
}

