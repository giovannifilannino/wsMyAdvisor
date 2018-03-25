package com.gianni.service.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel("Oggetto per la creazione di un nuovo posto")
public class CreaPostiRequest implements Serializable {

	private static final long serialVersionUID = 3150936541132036946L;

	private int idPosto;

	private String cittaPosto;

	private String nomePosto;

	private String indirizzo;

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
