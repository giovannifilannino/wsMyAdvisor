package com.gianni.model.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.gianni.model.entity.Posti;
import com.gianni.model.utility.DBManager;

public class DAOPosto {

	private static final Logger log = Logger.getLogger(DAOPosto.class.getName());
	private static final String QUERY_GET_ALL_POSTI = "SELECT P FROM Posti P";
	private static final String QUERY_GET_ALL_POSTI_BY_CITTA = "SELECT P FROM Posti P where P.cittaPosto LIKE :citta";
	private static final String QUERY_GET_ALL_POSTI_BY_POSTO = "SELECT P FROM Posti P where P.nomePosto LIKE :posto";

	private static final String POSTO = "POSTO";
	private static final String CITTA = "CITTA";

	public static List<Posti> getAllPosti() {
		EntityManager em = DBManager.getInstance().getEntityManager();
		TypedQuery<Posti> queryRecuperoPosti = em.createQuery(QUERY_GET_ALL_POSTI, Posti.class);
		List<Posti> posti = queryRecuperoPosti.getResultList();
		em.close();
		return posti;
	}

	private static List<Posti> getAllPostiByCitta(String citta) {
		EntityManager em = DBManager.getInstance().getEntityManager();
		TypedQuery<Posti> queryRecuperoPosti = em.createQuery(QUERY_GET_ALL_POSTI_BY_CITTA, Posti.class);
		queryRecuperoPosti.setParameter(1, citta);
		List<Posti> posti = queryRecuperoPosti.getResultList();
		em.close();
		return posti;
	}

	private static List<Posti> getAllPostiByPosto(String posto) {
		EntityManager em = DBManager.getInstance().getEntityManager();
		TypedQuery<Posti> queryRecuperoPosti = em.createQuery(QUERY_GET_ALL_POSTI_BY_POSTO, Posti.class);
		queryRecuperoPosti.setParameter(1, posto);
		List<Posti> posti = queryRecuperoPosti.getResultList();
		em.close();
		return posti;
	}

	public static List<Posti> getAllPostiFiltered(String tipologia, String query) {
		List<Posti> posti = null;
		if (null != tipologia && !tipologia.trim().isEmpty()) {
			switch (tipologia.toUpperCase()) {
			case POSTO:
				posti = DAOPosto.getAllPostiByPosto(query);
				break;
			default:
				posti = DAOPosto.getAllPostiByCitta(query);
				break;
			}

		}

		return posti;
	}

	public static boolean inserisciNuovoPosto(Posti p) {
		boolean esito = false;
		EntityManager em = DBManager.getInstance().getEntityManager();

		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}

			em.persist(p);
			em.getTransaction().commit();
			esito = true;
		} catch (Exception e) {
			log.severe(e.getMessage());
			em.getTransaction().rollback();
		} finally {
			em.close();
		}

		return esito;
	}

	public static boolean aggiornaFoto(int idPosto, byte[] foto) {
		boolean esito = false;
		EntityManager em = DBManager.getInstance().getEntityManager();
		Posti posto = em.find(Posti.class, idPosto);
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			if (null != posto) {
				posto.setImmagineCopertina(foto);

				em.merge(posto);
				em.getTransaction().commit();
				esito = true;
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
			if (null != em.getTransaction() && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
		} finally {
			em.close();
		}

		return esito;
	}
}
