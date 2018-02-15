package com.gianni.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "recensioni")
public class Recensione implements Serializable {

	private static final long serialVersionUID = -1892336463513031585L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRecensione;

	private Utente utente;

	private Posti posto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRecensione;

	private int location;

	private int menu;

	private int servizio;

	private int conto;

	private String note;

	public int getIdRecensione() {
		return idRecensione;
	}

	public void setIdRecensione(int idRecensione) {
		this.idRecensione = idRecensione;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Posti getPosto() {
		return posto;
	}

	public void setPosto(Posti posto) {
		this.posto = posto;
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

}
