package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public List<Endereco> findAllByUsuario(Long usuarioId) {
        return enderecoRepository.findAllByUsuario_Id(usuarioId);
    }

    @Transactional(readOnly = true)
    public Optional<Endereco> findByIdAndUsuario(Long id, Long usuarioId) {
        return enderecoRepository.findByIdAndUsuario_Id(id, usuarioId);
    }

    public Endereco save(Endereco endereco, User usuario) {
        endereco.setUsuario(usuario);
        return enderecoRepository.save(endereco);
    }

    public void delete(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }
}
