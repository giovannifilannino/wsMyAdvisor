package com.gianni.service.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Classe generica per la risposta di un operazione con id come risposta")
public class GenericIdResponse implements Serializable {

	private static final long serialVersionUID = -348155916118385767L;
	@ApiModelProperty(value = "Id dell'operazione", notes = "Restituisce un id")
	private int id;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
