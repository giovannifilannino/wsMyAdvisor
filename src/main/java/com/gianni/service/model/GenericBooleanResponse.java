package com.gianni.service.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Classe generica per la risposta di un operazione da true o false")
public class GenericBooleanResponse implements Serializable {

	private static final long serialVersionUID = 4884346428125134466L;

	@ApiModelProperty(value = "Esito dell'operazione", notes = "Restituisce o true o false")
	private boolean esito;

	public GenericBooleanResponse() {
	}

	public GenericBooleanResponse(boolean esito) {
		this.esito = esito;
	}

	public boolean isEsito() {
		return esito;
	}

	public void setEsito(boolean esito) {
		this.esito = esito;
	}

}
