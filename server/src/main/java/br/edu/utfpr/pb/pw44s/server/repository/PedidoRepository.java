package br.edu.utfpr.pb.pw44s.server.repository;

import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.Optional;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    @EntityGraph(attributePaths = {"endereco", "itens", "itens.product"})
    List<Pedido> findAllByUsuarioId(Long usuarioId);
    Page<Pedido> findAllByUsuarioId(Long usuarioId, Pageable pageable);

    @EntityGraph(attributePaths = {"endereco", "itens", "itens.product"})
    Optional<Pedido> findByIdAndUsuarioId(Long id, Long usuarioId);
}
