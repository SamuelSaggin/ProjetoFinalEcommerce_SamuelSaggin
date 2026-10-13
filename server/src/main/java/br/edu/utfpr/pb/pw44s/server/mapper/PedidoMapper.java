package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.PedidoDTO;
import br.edu.utfpr.pb.pw44s.server.model.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CategoryMapper.class})
public interface PedidoMapper {

    @Mapping(target = "enderecoId", source = "endereco.id")
    @Mapping(target = "itens", ignore = true)
    PedidoDTO toDto(Pedido pedido);

    @Mapping(target = "usuarioId", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    @Mapping(target = "itens", ignore = true)
    Pedido toEntity(PedidoDTO dto);
}
