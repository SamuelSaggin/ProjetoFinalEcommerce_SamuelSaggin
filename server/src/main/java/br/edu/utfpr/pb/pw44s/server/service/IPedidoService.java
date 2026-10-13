package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPedidoService {
    List<Pedido> findAll();
    Page<Pedido> findAll(Pageable pageable);
    List<Pedido> findAllByUsuarioId(Long usuarioId);
    Pedido findById(Long id);
    Optional<Pedido> findByIdAndUsuarioId(Long id, Long usuarioId);
    Pedido save(Pedido pedido);
    void deleteById(Long id);
    boolean exists(Long id);
    long count();
    Page<Pedido> findAllByUsuarioId(Long usuarioId, Pageable pageable);
}
