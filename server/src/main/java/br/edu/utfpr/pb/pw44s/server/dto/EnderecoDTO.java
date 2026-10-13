package br.edu.utfpr.pb.pw44s.server.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 120)
    private String logradouro;
    @NotNull
    @Positive
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
    private String cep;
}
