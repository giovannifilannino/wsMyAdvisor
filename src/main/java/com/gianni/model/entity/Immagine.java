package com.gianni.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "immagini")
public class Immagine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_immagine")
	private int idImmagine;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_recensione")
	private Recensione recensione;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	private String foto;

	@Column(name = "formato_immagine")
	private String formatoImmagine;

	public int getIdImmagine() {
		return idImmagine;
	}

	public void setIdImmagine(int idImmagine) {
		this.idImmagine = idImmagine;
	}

	public Recensione getRecensione() {
		return recensione;
	}

	public void setRecensione(Recensione recensione) {
		this.recensione = recensione;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFormatoImmagine() {
		return formatoImmagine;
	}

	public void setFormatoImmagine(String formatoImmagine) {
		this.formatoImmagine = formatoImmagine;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

}
