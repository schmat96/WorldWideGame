package model.unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Ability;
import model.HibernateFactory;
import model.Spieler;

public class CharakterDAO {

	public boolean getCharaktersOfCertainPlayer(Spieler spieler, ArrayList<Charakter> loadedCharakters) {
		Session sess = null;
		Transaction trx = null;
		Set<Charakter> result = null;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			Spieler sp = (Spieler) sess.get(spieler.getClass(), spieler.getId());
			result = sp.getCharakters();
			Iterator<Charakter> iter = result.iterator();
			while (iter.hasNext()) {
				Charakter character = iter.next();
				loadedCharakters.add(character);
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
	
	public ArrayList<Ability> getAbilitiesOfCharakter(Charakter charakter) {
		Session sess = null;
		Transaction trx = null;
		Set<Ability> result = null;
		ArrayList<Ability> loadedAbilities = new ArrayList<Ability>();
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();
			int id = charakter.getId();
			Charakter ab = (Charakter) sess.get(Charakter.class, id);
			result = ab.getAbilities();
			Iterator<Ability> iter = result.iterator();
			while (iter.hasNext()) {
				Ability ability = iter.next();
				loadedAbilities.add(ability);
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

		return loadedAbilities;

	}

}
