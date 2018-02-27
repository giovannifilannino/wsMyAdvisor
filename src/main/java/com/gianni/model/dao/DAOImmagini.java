package com.gianni.model.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.gianni.model.entity.Immagine;
import com.gianni.model.entity.Recensione;
import com.gianni.model.entity.Utente;
import com.gianni.model.utility.DBManager;

public class DAOImmagini {

	private static final Logger log = Logger.getLogger(DAOImmagini.class.getName());
	
	private static final String GET_IMMAGINI = "SELECT I FROM Immagine I WHERE I.recensione.idRecensione = :recensione";

	public static boolean inserisciImmagine(int idUtente, int idRecensione, Immagine i) {
		EntityManager em = DBManager.getInstance().getEntityManager();

		Utente utente = em.find(Utente.class, idUtente);
		Recensione recensione = em.find(Recensione.class, idRecensione);
		
		if(null == utente || null == recensione || null == i) {
			log.severe("INSERIMENTO IMMAGINE FALLITO!");
			log.severe("L'utente e' null" + String.valueOf(null == utente));
			log.severe("La recensione e' null" + String.valueOf(null == recensione));
			log.severe("L'immagine e' null" + String.valueOf(null == i));
			return false;
		}
		
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			i.setUtente(utente);
			i.setRecensione(recensione);
			
			em.persist(i);
			em.getTransaction().commit();

			return true;
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			log.severe(e.getMessage());
			return false;
		} finally {
			em.close();
		}
	}

	public static List<Immagine> listaImmagini(int idRecensione) {
		EntityManager em = DBManager.getInstance().getEntityManager();
		
		List<Immagine> immaginiResult = null;
		
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			
			TypedQuery<Immagine> immagini = em.createQuery(GET_IMMAGINI, Immagine.class);
			immagini.setParameter("recensione", idRecensione);
			immaginiResult = immagini.getResultList();
			
		} catch(Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			log.severe(e.getMessage());
		} finally {
			em.close();
		}
		
		return immaginiResult;
	}
}
