package com.br.notificacao.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(of = "usuNrId")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "usu_usuario", schema = "autenticacao")
public class UsuarioEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_nr_id")
    private Long usuNrId;

    @Column(name = "usu_tx_nome")
    private String usuTxNome;

    @Column(name = "usu_tx_email",unique = true)
    private String usuTxEmail;

    @Column(name = "usu_bl_ativo")
    private boolean usublAtivo;

    @Column(name = "usu_tx_senha")
    private String usuTxSenha;

    @Column(name = "usu_tx_expo_token")
    private String usuTxExpoToken;

    @Version
    @Column(name = "usu_nr_version")
    int usuNrVersion;
}
