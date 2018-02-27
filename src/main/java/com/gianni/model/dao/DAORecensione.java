package com.gianni.model.dao;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.gianni.model.entity.Posti;
import com.gianni.model.entity.Recensione;
import com.gianni.model.entity.Utente;
import com.gianni.model.utility.DBManager;

public class DAORecensione {

	private static final Logger log = Logger.getLogger(DAORecensione.class.getName());
	private static final String SELECT_RECENSIONI = "SELECT R FROM Recensione R WHERE R.posto.idPosto = :posto";

	public static List<Recensione> getAllRecensioni(int idPosto) {
		log.info("INIZIO RECUPERO RECENSIONI PER POSTO: " + idPosto);
		EntityManager em = DBManager.getInstance().getEntityManager();
		TypedQuery<Recensione> recensioni = em.createQuery(SELECT_RECENSIONI, Recensione.class);
		recensioni.setParameter("posto", idPosto);
		List<Recensione> recensioniList = recensioni.getResultList();
		log.info("RECUPERATE : " + recensioniList.size() + " RECENSIONI.");
		em.close();
		return recensioniList;
	}
	 

	public static boolean inserisciRecensione(int idUtente, int idPosto, Recensione r) {
		log.info("INIZIO INSERIMENTO NUOVA RECENSIONE");
		EntityManager em = DBManager.getInstance().getEntityManager();
		Utente user = em.find(Utente.class, idUtente);
		Posti posto = em.find(Posti.class, idPosto);
		if (null == posto || null == user || null == r) {
			log.severe("Utente o posto non trovato!");
			log.severe("Utente null: " + String.valueOf(user == null));
			log.severe("Posto null: " + String.valueOf(posto == null));
			log.severe("Recensione null: " + String.valueOf(r == null));
			return false;
		}

		r.setUtente(user);
		r.setPosto(posto);

		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			if(null == r.getDataRecensione()) {
				r.setDataRecensione(new Date());
			}
			em.persist(r);
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			log.severe(e.getMessage());
			return false;
		} finally {
			em.close();
		}
	}
}
