package com.gianni.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.gianni.model.dao.DAOPosto;
import com.gianni.model.entity.Posti;
import com.gianni.service.mapper.PostiMapper;
import com.gianni.service.model.CreaPostiRequest;
import com.gianni.service.model.GenericBooleanResponse;
import com.gianni.service.model.GenericIdResponse;
import com.gianni.service.utility.Utility;
import com.sun.corba.se.impl.ior.GenericIdentifiable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api
@Path("/posti")
public class PostiResource {

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Restituisce tutti i posti disponibili", response = CreaPostiRequest.class, responseContainer = "List")
	public Response getListaPosti() {
		List<Posti> posti = DAOPosto.getAllPosti();
		List<CreaPostiRequest> postiResponse = new ArrayList<CreaPostiRequest>();

		if (null != posti) {

			for (Posti p : posti) {
				postiResponse.add(PostiMapper.INSTANCE.postiMapperInverse(p));
			}

			return Response.ok(postiResponse).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("/{tipologia}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Restituisce una lista di posti filtrati per tipologia", response = CreaPostiRequest.class, responseContainer = "List")
	public Response getListaPostiFiltered(
			@ApiParam(value = "tipologia del filtro, può essere: POSTO o CITTA") @PathParam("tipologia") String tipologia,
			@ApiParam(value = "valore per cui applicare il filtro") @QueryParam("filtro") String filtro) {
		List<Posti> posti = DAOPosto.getAllPostiFiltered(tipologia, filtro);
		List<CreaPostiRequest> postiResponse = new ArrayList<CreaPostiRequest>();

		if (null != posti) {

			for (Posti p : posti) {
				postiResponse.add(PostiMapper.INSTANCE.postiMapperInverse(p));
			}

			return Response.ok(postiResponse).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Path("/crea")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Crea un nuovo posto", response = GenericBooleanResponse.class)
	public Response creaPosto(@ApiParam(value = "Posto da creare") CreaPostiRequest request) {
		Posti requestMapped = PostiMapper.INSTANCE.postiMapper(request);
		int esito = DAOPosto.inserisciNuovoPosto(requestMapped);
		GenericIdResponse response = new GenericIdResponse();
		response.setId(esito);
		return Response.ok(response).build();
	}

	@PUT
	@Path("/uploadFoto/{id}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Upload di una foto per il posto", response = GenericBooleanResponse.class)
	public Response uploadFoto(@ApiParam(value = "id del posto") @PathParam("id") int id,
			@ApiParam(value = "file da uploadare") @FormDataParam("file") InputStream uploadedInputStream,
			@ApiParam("dettagli immagine da caricare") @FormDataParam("file") FormDataContentDisposition fileDetails) {
		boolean esito = false;
		GenericBooleanResponse response = new GenericBooleanResponse();
		String nomeFile = fileDetails.getFileName();

		String location = Utility.uploadImmagine(nomeFile, "places", uploadedInputStream);

		esito = location != null;
		
		if(esito) {
			DAOPosto.aggiornaFoto(id, location);
		}

		response.setEsito(esito);

		return Response.ok(response).build();
	}
}
