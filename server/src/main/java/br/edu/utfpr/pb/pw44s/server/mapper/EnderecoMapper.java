package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.EnderecoDTO;
import br.edu.utfpr.pb.pw44s.server.model.Endereco;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EnderecoMapper {

    EnderecoDTO toDto(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Endereco toEntity(EnderecoDTO dto);
}
