package com.gianni.service.mapper;

import java.util.Base64;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.gianni.model.entity.Posti;
import com.gianni.service.model.CreaPostiRequest;

@Mapper(imports= {Base64.class})
public interface PostiMapper {

	PostiMapper INSTANCE = Mappers.getMapper(PostiMapper.class);

	@Mappings({ @Mapping(target = "nomePosto", source = "nomePosto")})
	Posti postiMapper(CreaPostiRequest postiResource);

	@InheritInverseConfiguration
	CreaPostiRequest postiMapperInverse(Posti posti);
}
