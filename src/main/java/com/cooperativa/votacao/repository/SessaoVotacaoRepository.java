package com.cooperativa.votacao.repository;

import com.cooperativa.votacao.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

    SessaoVotacao findByPautaId(Long pautaId);

    @Query("SELECT s FROM SessaoVotacao s WHERE CURRENT_TIMESTAMP BETWEEN s.dataAbertura AND s.dataFechamento")
    List<SessaoVotacao> findAllSessoesAbertas();
}