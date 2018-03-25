package com.gianni.service.model;

import java.io.Serializable;

import com.gianni.model.entity.Utente;

public class ImmagineModel implements Serializable {

	private static final long serialVersionUID = -7583542685477680833L;

	private int idImmagine;

	private Utente utente;

	private String foto;

	public int getIdImmagine() {
		return idImmagine;
	}

	public void setIdImmagine(int idImmagine) {
		this.idImmagine = idImmagine;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
