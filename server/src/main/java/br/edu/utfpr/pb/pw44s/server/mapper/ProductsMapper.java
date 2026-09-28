package br.edu.utfpr.pb.pw44s.server.mapper;

import br.edu.utfpr.pb.pw44s.server.dto.ProductsDTO;
import br.edu.utfpr.pb.pw44s.server.model.Products;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CategoryMapper.class})
public interface ProductsMapper {

    ProductsDTO toDto(Products products);

    Products toEntity(ProductsDTO dto);
}
