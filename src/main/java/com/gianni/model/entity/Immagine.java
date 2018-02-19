package com.gianni.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Immagine")
public class Immagine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idImmagine;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_recensione")
	private Recensione recensione;

	@Lob
	private byte[] foto;

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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getFormatoImmagine() {
		return formatoImmagine;
	}

	public void setFormatoImmagine(String formatoImmagine) {
		this.formatoImmagine = formatoImmagine;
	}

}
