package com.gianni.service.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RecensioniResponse implements Serializable {

	private static final long serialVersionUID = 2015071414772061162L;

	private int idRecensione;

	private int idUtente;

	private int idPosto;

	private Date dataRecensione;

	private int location;

	private int menu;

	private int servizio;

	private int conto;

	private String note;

	private List<ImmagineModel> immagini;

	public int getIdRecensione() {
		return idRecensione;
	}

	public void setIdRecensione(int idRecensione) {
		this.idRecensione = idRecensione;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}

	public Date getDataRecensione() {
		return dataRecensione;
	}

	public void setDataRecensione(Date dataRecensione) {
		this.dataRecensione = dataRecensione;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getMenu() {
		return menu;
	}

	public void setMenu(int menu) {
		this.menu = menu;
	}

	public int getServizio() {
		return servizio;
	}

	public void setServizio(int servizio) {
		this.servizio = servizio;
	}

	public int getConto() {
		return conto;
	}

	public void setConto(int conto) {
		this.conto = conto;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<ImmagineModel> getImmagini() {
		return immagini;
	}

	public void setImmagini(List<ImmagineModel> immagini) {
		this.immagini = immagini;
	}

}
