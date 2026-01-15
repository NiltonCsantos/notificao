package com.br.notificacao.domain;

public record Notificacao(
        String titulo,
        String pedTxStatus,
        Long usuNrId
) {

    public static Notificacao of(String titulo, String pedTxStatus,  Long usuNrId) {
        return new Notificacao(titulo, pedTxStatus, usuNrId);
    }

}