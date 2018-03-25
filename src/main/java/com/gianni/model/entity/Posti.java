package com.gianni.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "posti")
public class Posti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_posto")
	private int idPosto;

	@Column(name = "citta_posto")
	private String cittaPosto;

	@Column(name = "nome_posto")
	private String nomePosto;

	private String indirizzo;

	@Column(name = "immagine_copertina")
	private String immagineCopertina;

	public int getIdPosto() {
		return idPosto;
	}

	public void setIdPosto(int idPosto) {
		this.idPosto = idPosto;
	}

	public String getCittaPosto() {
		return cittaPosto;
	}

	public void setCittaPosto(String cittaPosto) {
		this.cittaPosto = cittaPosto;
	}

	public String getNomePosto() {
		return nomePosto;
	}

	public void setNomePosto(String nomePosto) {
		this.nomePosto = nomePosto;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getImmagineCopertina() {
		return immagineCopertina;
	}

	public void setImmagineCopertina(String immagineCopertina) {
		this.immagineCopertina = immagineCopertina;
	}

}
