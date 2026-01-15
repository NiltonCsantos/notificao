package com.br.notificacao.service.firebase.impl;

import com.br.notificacao.domain.Notificacao;
import com.br.notificacao.enums.StatusPedidoEnum;
import com.br.notificacao.model.PedidoEntidade;
import com.br.notificacao.repository.PedidoRepository;
import com.br.notificacao.repository.UsuarioRepository;
import com.br.notificacao.service.firebase.NotificacaFirebaseService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class NotificacaoFirebaseServiceImpl implements NotificacaFirebaseService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public void notificarAtualizaoDePedido(Notificacao form) {
           try {
               enviarMensagem(form.usuNrId(), form.pedTxStatus(), form.titulo());
           }catch (Exception e){
               System.out.println(e.getMessage());
           }
    }

    @Override
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    public void tratarNotificacoesFalhas() {
        var listaPedidosComNotificacoesFalhas = this.pedidoRepository.findAllByPedBlUltimaNotificacaoEnviadaComSucessoIsTrue();
        for (var pedido : listaPedidosComNotificacoesFalhas){
            notificarAtualizaoDePedido(pedido);
        }
        pedidoRepository.saveAll(listaPedidosComNotificacoesFalhas);
    }

    private void notificarAtualizaoDePedido(PedidoEntidade entidade) {
        try {

            enviarMensagem(entidade.getUsuNrId(), entidade.getPedTxStatus().getMensagem(),
                    entidade.getPedTxStatus().getTitulo());
//            if (request.getData() != null) {
//                builder.putAllData(request.getData());
//            }

            entidade.setPedBlUltimaNotificacaoEnviadaComSucesso(true);

        }catch (Exception e){
            entidade.setPedBlUltimaNotificacaoEnviadaComSucesso(false);
            //TODO: implementar log
        }
    }

    private void enviarMensagem(Long usuNrId, String pedTxStatus, String titulo) throws FirebaseMessagingException{
        var usuario = this.usuarioRepository.findById(usuNrId)
                .orElseThrow();
        var message = Message.builder()
                .setToken(usuario.getUsuTxExpoToken())
                .setNotification(
                        Notification.builder()
                                .setTitle(titulo)
                                .setBody(StatusPedidoEnum.getMensagemPorValor(pedTxStatus))
                                .build()
                ).build();
        FirebaseMessaging.getInstance().send(message);
    }
}
