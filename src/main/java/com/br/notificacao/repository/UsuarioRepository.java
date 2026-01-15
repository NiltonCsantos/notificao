package com.br.notificacao.repository;

import com.br.notificacao.model.UsuarioEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntidade, Long> {
}



