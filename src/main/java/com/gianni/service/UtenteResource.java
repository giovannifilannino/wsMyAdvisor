package com.gianni.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.gianni.model.dao.DAOUtente;
import com.gianni.service.model.UtenteLoginRequest;
import com.gianni.service.model.UtenteLoginResponse;

@Path("/utente")
public class UtenteResource {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(UtenteLoginRequest request) {
		int id = DAOUtente.login(request.getUsername(), request.getPassword());
		UtenteLoginResponse response = new UtenteLoginResponse();
		response.setId(id);
		return Response.ok(response).build();
	}
}
