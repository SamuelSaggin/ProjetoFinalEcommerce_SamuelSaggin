package br.edu.utfpr.pb.pw44s.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_itensDoPedido")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ItensDoPedido {

    @EmbeddedId
    private ItemPedidoId id = new ItemPedidoId();

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Product product;

    @NotNull
    private BigDecimal preco;
    @NotNull
    private int quantidade;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensDoPedido itensDoPedido = (ItensDoPedido) o;
        return Objects.equals(id, itensDoPedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
