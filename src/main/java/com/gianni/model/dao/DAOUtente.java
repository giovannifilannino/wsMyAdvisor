package com.gianni.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.gianni.model.entity.Utente;
import com.gianni.model.utility.DBManager;

public class DAOUtente {

	private static final String QUERY_LOGIN = "SELECT * FROM UTENTI WHERE USERNAME = ? AND PASSWORD = MD5(?)";

	public static int login(String username, String password) {
		EntityManager em = DBManager.getInstance().getEntityManager();
		int id = 0;

		Query nativeQuery = em.createNativeQuery(QUERY_LOGIN, Utente.class);
		int indice = 0;
		nativeQuery.setParameter(++indice, username);
		nativeQuery.setParameter(++indice, password);

		@SuppressWarnings("unchecked")
		List<Utente> utenti = nativeQuery.getResultList();

		if (null != utenti && !utenti.isEmpty()) {
			id = utenti.get(0).getId();
		} else {
			id = -1;
		}
		em.close();
		return id;

	}
}
