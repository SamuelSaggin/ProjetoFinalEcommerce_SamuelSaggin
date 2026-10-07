package br.edu.utfpr.pb.pw44s.server.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;

    @Embeddable
    public class ItemPedidoId implements Serializable {
        private Long pedidoId;
        private Long produtoId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ItemPedidoId that = (ItemPedidoId) o;
            return Objects.equals(pedidoId, that.pedidoId) &&
                    Objects.equals(produtoId, that.produtoId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pedidoId, produtoId);
        }
    }