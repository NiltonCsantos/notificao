package com.br.notificacao.repository;

import com.br.notificacao.model.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntidade, Long> {
    List<PedidoEntidade> findAllByPedBlUltimaNotificacaoEnviadaComSucessoIsTrue();
}
