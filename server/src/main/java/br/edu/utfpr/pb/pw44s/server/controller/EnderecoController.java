package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.EnderecoDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.EnderecoMapper;
import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.service.AuthService;
import br.edu.utfpr.pb.pw44s.server.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;
    private final EnderecoMapper enderecoMapper;
    private final AuthService authService;

    public EnderecoController(EnderecoService enderecoService, EnderecoMapper enderecoMapper, AuthService authService) {
        this.enderecoService = enderecoService;
        this.enderecoMapper = enderecoMapper;
        this.authService = authService;
    }

    private User usuarioAutenticado() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return (User) authService.loadUserByUsername(username);
    }

    @GetMapping
    public List<EnderecoDTO> findAll() {
        User usuario = usuarioAutenticado();
        return enderecoService.findAllByUsuario(usuario.getId()).stream().map(enderecoMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        User usuario = usuarioAutenticado();
        return enderecoService.findByIdAndUsuario(id, usuario.getId())
                .map(endereco -> ResponseEntity.ok(enderecoMapper.toDto(endereco)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody @Valid EnderecoDTO dto) {
        Endereco endereco = enderecoMapper.toEntity(dto);
        Endereco salvo = enderecoService.save(endereco, usuarioAutenticado());
        return ResponseEntity.status(HttpStatus.CREATED).body(enderecoMapper.toDto(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody @Valid EnderecoDTO dto) {
        User usuario = usuarioAutenticado();
        return enderecoService.findByIdAndUsuario(id, usuario.getId()).map(atual -> {
            Endereco alteracoes = enderecoMapper.toEntity(dto);
            atual.setLogradouro(alteracoes.getLogradouro());
            atual.setNumero(alteracoes.getNumero());
            atual.setBairro(alteracoes.getBairro());
            atual.setCidade(alteracoes.getCidade());
            atual.setComplemento(alteracoes.getComplemento());
            atual.setCep(alteracoes.getCep());
            return ResponseEntity.ok(enderecoMapper.toDto(enderecoService.save(atual, usuario)));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User usuario = usuarioAutenticado();
        return enderecoService.findByIdAndUsuario(id, usuario.getId()).map(endereco -> {
            enderecoService.delete(endereco);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
