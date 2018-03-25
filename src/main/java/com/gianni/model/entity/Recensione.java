package com.gianni.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recensioni")
public class Recensione implements Serializable {

	private static final long serialVersionUID = -1892336463513031585L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_recensione")
	private int idRecensione;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_posto")
	private Posti posto;

	@Column(name = "data_recensione")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataRecensione;

	private int location;

	private int menu;

	private int servizio;

	private int conto;

	private String note;

	@OneToMany(mappedBy = "recensione")
	private List<Immagine> immagini;

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

	public List<Immagine> getImmagini() {
		return immagini;
	}

	public void setImmagini(List<Immagine> immagini) {
		this.immagini = immagini;
	}

}
