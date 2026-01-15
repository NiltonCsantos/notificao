package com.br.notificacao.model;

import com.br.notificacao.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ped_pedido", schema = "financeiro")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "pedNrId")
public class PedidoEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ped_nr_id")
    private Long pedNrId;

    @Column(name = "ped_dt_criado", nullable = false)
    private LocalDateTime pedDtCriado;

    @Column(name = "ped_tx_status")
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum pedTxStatus;

    @Column(name = "usu_nr_id", nullable = false)
    private Long usuNrId;

    @Column(name = "ped_nr_valor_total", precision = 5, scale = 2)
    private BigDecimal pedNrValorTotal;

    @Builder.Default
    @Column(name = "ped_bl_ultima_notificao_enviada_com_sucesso")
    private boolean pedBlUltimaNotificacaoEnviadaComSucesso = true;

    @Version
    @Column(name = "ped_nr_version")
    int pedNrVersion;
}