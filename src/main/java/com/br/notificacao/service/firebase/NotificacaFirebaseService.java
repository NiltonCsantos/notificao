package com.br.notificacao.service.firebase;

import com.br.notificacao.domain.Notificacao;

public interface NotificacaFirebaseService {
    void notificarAtualizaoDePedido(Notificacao form);
    void tratarNotificacoesFalhas();
}
