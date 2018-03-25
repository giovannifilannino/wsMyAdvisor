package com.gianni.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.gianni.model.dao.DAOImmagini;
import com.gianni.model.dao.DAORecensione;
import com.gianni.model.entity.Immagine;
import com.gianni.model.entity.Recensione;
import com.gianni.service.mapper.RecensioneMapper;
import com.gianni.service.model.CreaRecensione;
import com.gianni.service.model.GenericBooleanResponse;
import com.gianni.service.model.RecensioniResponse;
import com.gianni.service.utility.Utility;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/recensione")
@Api
public class RecensioneResource {

	@POST
	@Path("/nuovo/{idPosto}/{idUtente}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Inserisce una nuova recensione", response = GenericBooleanResponse.class)
	public Response inserisciRecensione(
			@ApiParam(value = "id del posto a cui fare la recensione") @PathParam("idPosto") int idPosto,
			@ApiParam(value = "id dell'utente creatore della recensione") @PathParam("idUtente") int idUtente,
			@ApiParam(value = "Recensione scritta in vari punti") CreaRecensione r) {
		Recensione recensione = RecensioneMapper.INSTANCE.mapRecensioneFromCreaRecensione(r);
		boolean esito = DAORecensione.inserisciRecensione(idUtente, idPosto, recensione);
		GenericBooleanResponse response = new GenericBooleanResponse(esito);
		if (esito) {
			return Response.ok(response).build();
		}
		return Response.status(Status.BAD_REQUEST).entity(response).build();
	}

	@GET
	@Path("/recensioni/{idPosto}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Recupera tutte le recensioni", response = RecensioniResponse.class, responseContainer = "List")
	public Response getAllRecensioni(
			@ApiParam(value = "id del posto da cui recuperare le recensioni") @PathParam("idPosto") int idPosto) {

		List<Recensione> recensioni = DAORecensione.getAllRecensioni(idPosto);
		List<RecensioniResponse> recensioniMapped = RecensioneMapper.INSTANCE.mapRecensioni(recensioni);
		if (null == recensioni) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		return Response.ok(recensioniMapped).build();
	}

	@GET
	@Path("/immagini/{idRecensione}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Recupera tutte le immagini della recensione", response = Immagine.class, responseContainer = "List")
	public Response getListaImmagini(
			@ApiParam(value = "id della recensione") @PathParam("idRecensione") int idRecensione) {
		List<Immagine> immagini = DAOImmagini.listaImmagini(idRecensione);
		if (null == immagini) {
			return Response.status(Status.OK).entity(new ArrayList<Immagine>()).build();
		}
		return Response.status(Status.OK).entity(immagini).build();
	}

	@POST
	@Path("/immagini/nuova/{idRecensione}/{idUtente}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Upload di una nuova immagine della recensione", response = GenericBooleanResponse.class)
	public Response uploadImmagine(@PathParam("idRecensione") int idRecensione, @PathParam("idUtente") int idUtente,
			@ApiParam("immagine da caricare") @FormDataParam("file") InputStream uploadedInputStream,
			@ApiParam("dettagli immagine da caricare") @FormDataParam("file") FormDataContentDisposition fileDetails) {

		boolean esito = false;
		GenericBooleanResponse response = new GenericBooleanResponse();
		
		
		String nomeFile = fileDetails.getFileName();
		
		String location = Utility.uploadImmagine(nomeFile, "images", uploadedInputStream);

		esito = location  != null;
		
		if(esito) {
			Immagine i = new Immagine();
			i.setFoto(location);
			DAOImmagini.inserisciImmagine(idUtente, idRecensione, i);
		}

		response.setEsito(esito);

		return Response.ok(response).build();
	}

}
