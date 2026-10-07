package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.PedidoDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.PedidoMapper;
import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import br.edu.utfpr.pb.pw44s.server.service.IPedidoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("pedido")
public class PedidoController {

    private final IPedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoController(IPedidoService Service,  PeiddoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.PedidoMapper = PedidoMapper;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> save(@RequestBody PedidoDTO PedidoDTO) {
        Pedido Pedido = PedidoService.save(PedidoMapper.toEntity(PedidoDTO));

        return ResponseEntity.status(HttpStatus.CREATED).body(
                PedidoMapper.toDto(Pedido)
        );
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() {
        return ResponseEntity.ok(
                PedidoService.findAll()
                        .stream()
                        .map(PedidoMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        Pedido Pedido = PedidoService.findById(id);
        if (Pedido != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    PedidoMapper.toDto(Pedido));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        PedidoService.deleteById(id);
    }

    @GetMapping("page")
    public ResponseEntity<Page<PedidoDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) Boolean asc
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if(order != null && asc != null) {
            pageRequest = PageRequest.of(page -1, size,
                    asc? Sort.Direction.ASC : Sort.Direction.DESC, order);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                PedidoService.findAll(pageRequest).map(PedidoMapper::toDto)
        );

    }

}
