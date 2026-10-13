package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoDTO {
    @NotNull
    @Positive
    private Long produtoId;

    @NotNull
    @Positive
    private Integer quantidade;

    private BigDecimal precoUnitario;
}
