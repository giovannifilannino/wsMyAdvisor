package com.gianni.service.mapper;

import java.util.Base64;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.gianni.model.dao.DAOPosto;
import com.gianni.model.dao.DAOUtente;
import com.gianni.model.entity.Immagine;
import com.gianni.model.entity.Recensione;
import com.gianni.service.model.CreaRecensione;
import com.gianni.service.model.ImmagineModel;
import com.gianni.service.model.RecensioniResponse;

@Mapper(imports = { DAOUtente.class, DAOPosto.class, Base64.class })
public interface RecensioneMapper {

	static RecensioneMapper INSTANCE = Mappers.getMapper(RecensioneMapper.class);

	@Mappings({ @Mapping(target = "immagini", ignore = true),
			@Mapping(target = "utente", expression = "java(DAOUtente.recuperaUtente(r.getIdUtente()))"),
			@Mapping(target = "posto", expression = "java(DAOPosto.getPosto(r.getIdPosto()))") })
	Recensione mapRecensioneFromCreaRecensione(CreaRecensione r);

	@Mapping(target = "foto", source = "foto")
	ImmagineModel mapFromImmagine(Immagine i);

	@Mappings({ @Mapping(target = "idUtente", expression = "java(r.getUtente().getId())"),
			@Mapping(target = "idPosto", expression = "java(r.getPosto().getIdPosto())"),
			@Mapping(target = "immagini", source = "immagini") })
	RecensioniResponse mapFromRecensione(Recensione r);

	List<RecensioniResponse> mapRecensioni(List<Recensione> r);

}
