package com.cooperativa.votacao.repository;

import com.cooperativa.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    // O Spring Data JPA fornece métodos CRUD padrão (save, findById, findAll, etc.)
}