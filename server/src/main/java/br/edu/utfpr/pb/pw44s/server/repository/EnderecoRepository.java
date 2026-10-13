package br.edu.utfpr.pb.pw44s.server.repository;

import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findAllByUsuario_Id(Long usuarioId);
    Optional<Endereco> findByIdAndUsuario_Id(Long id, Long usuarioId);
}
