package br.edu.utfpr.pb.pw44s.server.dto;

import lombok.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoDTO {
    private Long id;
    private LocalDateTime data;
    @NotNull
    @Positive
    private Long enderecoId;
    @NotEmpty
    @Valid
    private List<ItemPedidoDTO> itens;
}
