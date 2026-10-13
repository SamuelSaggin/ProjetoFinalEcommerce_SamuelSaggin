package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import br.edu.utfpr.pb.pw44s.server.repository.PedidoRepository;
import br.edu.utfpr.pb.pw44s.server.model.ItensDoPedido;
import br.edu.utfpr.pb.pw44s.server.service.IPedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoServiceImpl implements IPedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findAll() {
        return this.pedidoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pedido> findAll(Pageable pageable) {
        return this.pedidoRepository.findAll(pageable);
    }

    @Override
    public List<Pedido> findAllByUsuarioId(Long usuarioId) {
        return this.pedidoRepository.findAllByUsuarioId(usuarioId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Pedido> findAllByUsuarioId(Long usuarioId, Pageable pageable) {
        return this.pedidoRepository.findAllByUsuarioId(usuarioId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Pedido findById(Long id) {
        return this.pedidoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public java.util.Optional<Pedido> findByIdAndUsuarioId(Long id, Long usuarioId) {
        return this.pedidoRepository.findByIdAndUsuarioId(id, usuarioId);
    }

    @Override
    @Transactional
    public Pedido save(Pedido pedido) {
        for (ItensDoPedido item : pedido.getItens()) {
            item.setPedido(pedido);
            item.setPreco(item.getProduct().getPrice());
        }
        return this.pedidoRepository.save(pedido);
    }

    @Override
    public void deleteById(Long id) {
        this.pedidoRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return this.pedidoRepository.existsById(id);
    }

    @Override
    public long count() {
        return this.pedidoRepository.count();
    }
}
