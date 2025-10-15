package com.cooperativa.votacao.repository;

import com.cooperativa.votacao.model.TipoVoto;
import com.cooperativa.votacao.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsByAssociadoIdAndPautaId(Long associadoId, Long pautaId);

    long countByPautaIdAndVoto(Long pautaId, TipoVoto voto);
}