package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.ItemPedidoDTO;
import br.edu.utfpr.pb.pw44s.server.dto.PedidoDTO;
import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import br.edu.utfpr.pb.pw44s.server.model.ItensDoPedido;
import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.repository.ProductRepository;
import br.edu.utfpr.pb.pw44s.server.service.AuthService;
import br.edu.utfpr.pb.pw44s.server.service.EnderecoService;
import br.edu.utfpr.pb.pw44s.server.service.IPedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final IPedidoService pedidoService;
    private final EnderecoService enderecoService;
    private final ProductRepository productRepository;
    private final AuthService authService;

    public PedidoController(IPedidoService pedidoService, EnderecoService enderecoService,
                            ProductRepository productRepository, AuthService authService) {
        this.pedidoService = pedidoService;
        this.enderecoService = enderecoService;
        this.productRepository = productRepository;
        this.authService = authService;
    }

    private User usuarioAutenticado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (User) authService.loadUserByUsername(username);
    }

    private PedidoDTO toDto(Pedido pedido) {
        List<ItemPedidoDTO> itens = pedido.getItens().stream()
                .map(item -> ItemPedidoDTO.builder()
                        .produtoId(item.getProduct().getId())
                        .quantidade(item.getQuantidade())
                        .precoUnitario(item.getPreco())
                        .build())
                .toList();
        return PedidoDTO.builder()
                .id(pedido.getId())
                .data(pedido.getData())
                .enderecoId(pedido.getEndereco().getId())
                .itens(itens)
                .build();
    }

    @PostMapping
    public ResponseEntity<?> finalizarCompra(@RequestBody @Valid PedidoDTO dto) {
        User usuario = usuarioAutenticado();
        Endereco endereco = enderecoService.findByIdAndUsuario(dto.getEnderecoId(), usuario.getId()).orElse(null);
        if (endereco == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O endereço não existe ou não pertence ao usuário.");
        }

        Set<Long> idsProdutos = new HashSet<>();
        List<ItensDoPedido> itens = new ArrayList<>();
        for (ItemPedidoDTO itemDTO : dto.getItens()) {
            if (!idsProdutos.add(itemDTO.getProdutoId())) {
                return ResponseEntity.badRequest().body("Cada produto deve aparecer uma única vez no pedido.");
            }
            Product product = productRepository.findById(itemDTO.getProdutoId()).orElse(null);
            if (product == null) {
                return ResponseEntity.badRequest().body("Produto não encontrado: " + itemDTO.getProdutoId());
            }
            ItensDoPedido item = new ItensDoPedido();
            item.setProduct(product);
            item.setQuantidade(itemDTO.getQuantidade());
            item.setPreco(product.getPrice());
            itens.add(item);
        }

        Pedido pedido = Pedido.builder()
                .data(LocalDateTime.now())
                .usuarioId(usuario.getId())
                .endereco(endereco)
                .itens(itens)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(pedidoService.save(pedido)));
    }

    @GetMapping
    public List<PedidoDTO> findAll() {
        User usuario = usuarioAutenticado();
        return pedidoService.findAllByUsuarioId(usuario.getId()).stream().map(this::toDto).toList();
    }

    @GetMapping("/page")
    public Page<PedidoDTO> findAll(@RequestParam int page, @RequestParam int size) {
        User usuario = usuarioAutenticado();
        return pedidoService.findAllByUsuarioId(usuario.getId(), PageRequest.of(page, size)).map(this::toDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        User usuario = usuarioAutenticado();
        return pedidoService.findByIdAndUsuarioId(id, usuario.getId())
                .map(pedido -> ResponseEntity.ok(toDto(pedido)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User usuario = usuarioAutenticado();
        return pedidoService.findByIdAndUsuarioId(id, usuario.getId()).map(pedido -> {
            pedidoService.deleteById(pedido.getId());
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
