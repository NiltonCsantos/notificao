package com.br.notificacao.enums;

import java.util.List;

public enum StatusPedidoEnum {

    CRIADO("Pedido criado com sucesso", "Aguarde o vendedor iniciar o pedido"),
    EM_PREPARACAO("Pedido em preparação", "O vendedor já está preparando seu pedido"),
    CANCELADO("Pedido cancelado", "Infelizmente o pedido não foi aceito"),
    FINALIZADO("Pedido finalizado", "Nos vemos em breve!");

    private final String titulo;
    private final String mensagem;

    StatusPedidoEnum(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public static String getMensagemPorValor(String valor) {
        return StatusPedidoEnum.valueOf(valor).getMensagem();
    }

    public static String getTituloPorValor(String valor) {
        return StatusPedidoEnum.valueOf(valor).getTitulo();
    }
}
