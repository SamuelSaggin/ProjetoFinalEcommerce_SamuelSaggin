package br.edu.utfpr.pb.pw44s.server.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "tb_endereco")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private User usuario;
    @NotNull
    @Size(min = 1, max = 120)
    private String logradouro;
    @NotNull
    @jakarta.validation.constraints.Positive
    private int numero;
    @NotNull
    @Size(min = 1, max = 80)
    private String bairro;
    @NotNull
    @Size(min = 1, max = 80)
    private String cidade;
    @Size(max = 120)
    private String complemento;

    @NotNull
    @Size(min = 8, max = 9)
    @Column(length = 9, nullable = false)
    private String cep;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
