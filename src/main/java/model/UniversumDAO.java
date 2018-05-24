package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.unit.Charakter;

public class UniversumDAO {

	public boolean getUniversums(ArrayList<Universum> loadedUniversums) {
		Session sess = null;
		Transaction trx = null;
		ArrayList<Universum> result = null;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			result = (ArrayList<Universum>) sess.createQuery("from Universum").list();
			
			Iterator<Universum> iter = result.iterator();
			while (iter.hasNext()) {
				Universum universum = iter.next();
				loadedUniversums.add(universum);
			}


		} catch (HibernateException ex) {
			if (trx != null)
				try {
					trx.rollback();
				} catch (HibernateException exRb) {
				}
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (sess != null)
					sess.close();
			} catch (Exception exCl) {
			}
		}

		return false;

	}
	
	public Charakter getRandomUnitFromUniversum(Universum universum) {
		Session sess = null;
		Transaction trx = null;
		Charakter result = null;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			Universum uni = (Universum) sess.get(universum.getClass(), universum.getId());
			
			Set<Charakter> allCharakters = uni.getCharakters();
			ArrayList<Charakter> charakters = new ArrayList<Charakter>(allCharakters);
			if (charakters.size()==0) {
				return null;
			}
			Random rng = new Random();
			result = charakters.get(rng.nextInt(charakters.size()));


		} catch (HibernateException ex) {
			if (trx != null)
				try {
					trx.rollback();
				} catch (HibernateException exRb) {
				}
			throw new RuntimeException(ex.getMessage());
		} finally {
			try {
				if (sess != null)
					sess.close();
			} catch (Exception exCl) {
			}
		}

		return result;
		
	}
	

}
