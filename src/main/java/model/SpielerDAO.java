package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import exceptions.NotEnoughMoneyException;
import model.unit.Charakter;

public class SpielerDAO {

	public void addMoney(Spieler sp, int money) throws NotEnoughMoneyException {
		Session sess = null;
		Transaction trx = null;
		try {

			if (money < 0 && money * -1 > sp.getGeld()) {

				throw new NotEnoughMoneyException("Nicht genug Geld!");

			}

			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			sp.setGeld(sp.getGeld() + money);
			sess.merge(sp);
			trx.commit();

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
	}

	public Spieler checkUserLogin(String Name, String pw) {

		Session sess = null;
		Transaction trx = null;
		ArrayList<Spieler> result;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			result = (ArrayList<Spieler>) sess.createQuery("from Spieler E WHERE E.name like '" + Name + "'").list();
			if (result.size() == 1) {
				
					if (Hash.getHash(pw).equals(result.get(0).getPasswort())) {
						Spieler sp = result.get(0);
						sess.merge(sp);
						trx.commit();
						return result.get(0);
					}
					
				
				return null;
			} else {
				return null;
			}
		} catch (HashingException e) {
				
			e.printStackTrace();
			return null;
		
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

	}

	private ArrayList<Spieler> getPlayers() {
		Session sess = null;
		Transaction trx = null;
		ArrayList<Spieler> result;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			result = (ArrayList<Spieler>) sess.createQuery("from Spieler").list();

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

	private Spieler generatePlayer(String name, String passwort, int exp, int geld) {
		Session sess = null;
		Transaction trx = null;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();
			Spieler sp = new Spieler();
			sp.setName(name);
			sp.setPasswort(Hash.getHash(passwort));
			sp.setExp(exp);
			sp.setGeld(geld);
			ArrayList<Charakter> charaktersDefault = (ArrayList<Charakter>) sess.createQuery("from Charakter where id<7").list();
			Iterator<Charakter> iter = charaktersDefault.iterator();
			while (iter.hasNext()) {
				sp.charakters.add(iter.next());
			}
			sess.save(sp);
			trx.commit();
			return sp;
		}catch (HashingException e) {
			// TODO Auto-generated catch block
			sess.close();
			e.printStackTrace();
			return null;
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
		
	}

	public void closeSessionFactory() {
		HibernateFactory.getSessionFactory().close();
	}

	public Spieler registerUser(String name, String pw) {
		ArrayList<Spieler> players = this.getPlayers();
		Iterator<Spieler> iter = players.iterator();
		while (iter.hasNext()) {
			if (name.equals(iter.next().getName())) {
				return null;
			}
		}
		return this.generatePlayer(name, pw, 0, 190);
	}
	
	public boolean playerOwnsCharakter(Spieler spieler, Charakter charakter) {
		Session sess = null;
		Transaction trx = null;
		boolean owns = false;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			Spieler sp = (Spieler) sess.get(spieler.getClass(), spieler.getId());

			Set<Charakter> charakters = sp.getCharakters();
			Iterator<Charakter> iter = charakters.iterator();
			while (iter.hasNext()) {
				if (charakter.getName().equals(iter.next().getName())) {
					return true;
				}
			}
			
			trx.commit();

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
		return owns;
	}

	public void addCharakter(Spieler loggedInSpieler, Charakter summonedCharakter) {
		Session sess = null;
		Transaction trx = null;

		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			Spieler sp = (Spieler) sess.get(loggedInSpieler.getClass(), loggedInSpieler.getId());
			sp.getCharakters().add(summonedCharakter);
			sess.merge(sp);
			trx.commit();
		
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
		
	}
}
