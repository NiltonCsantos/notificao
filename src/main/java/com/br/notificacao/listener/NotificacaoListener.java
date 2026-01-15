package com.br.notificacao.listener;

import com.br.notificacao.domain.Notificacao;
import com.br.notificacao.enums.StatusPedidoEnum;
import com.br.notificacao.service.firebase.NotificacaFirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificacaoListener {

    private final NotificacaFirebaseService notificacaFirebaseService;

    @RabbitListener(queues = "${rabbitmq.queue.notificacao}")
    public void listener(Notificacao notificacao){
        String mensagem = StatusPedidoEnum.getMensagemPorValor(notificacao.pedTxStatus());
        notificacaFirebaseService.notificarAtualizaoDePedido(notificacao);
    }
}
