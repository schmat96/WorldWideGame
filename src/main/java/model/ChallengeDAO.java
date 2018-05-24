package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.unit.Enemy;

public class ChallengeDAO {
	

	ArrayList<ChallengeTurn> challenges;
	
	public boolean getChallenges(ArrayList<Challenge> loadedUniversums) {
		Session sess = null;
		Transaction trx = null;
		ArrayList<Challenge> result;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();

			result = (ArrayList<Challenge>) sess.createQuery("from Challenge").list();
			
			Iterator<Challenge> iter = result.iterator();
			while (iter.hasNext()) {
				Challenge universum = iter.next();
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

		return true;
	}
	
	public int getChallengeTurnCount(Challenge challenge) {
		Session sess = null;
		Transaction trx = null;
		int result;
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();
			Challenge ab = (Challenge) sess.get(Challenge.class, challenge.getId());
			result = ab.getTurns().size();

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
	
	public ArrayList<Enemy> getEnemies(ChallengeTurn selectedChallenge) {
		Session sess = null;
		Transaction trx = null;
		ArrayList<Enemy> result = new ArrayList<Enemy>();
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();
			
			ChallengeTurn ab = (ChallengeTurn) sess.get(ChallengeTurn.class, selectedChallenge.getIdTurn());
			Iterator<Enemy> iter = ab.getEnemies().iterator();
			while (iter.hasNext()) {
				result.add(iter.next());
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

		return result;
	}

	public ArrayList<Enemy> getEnemiesAtTurnCount(Challenge selectedChallenge, int turn) {
		if (challenges == null) {
			challenges = getChallengTurns(selectedChallenge);
		}
		Iterator<ChallengeTurn> dammed = challenges.iterator();
		if (dammed.hasNext()) {
			ChallengeTurn god = dammed.next();
			dammed.remove();
			return getEnemies (god);
		}
		return null;
	}

	private ArrayList<ChallengeTurn> getChallengTurns(Challenge selectedChallenge) {
		Session sess = null;
		Transaction trx = null;
		ArrayList<ChallengeTurn> result = new ArrayList<ChallengeTurn>();
		try {
			sess = HibernateFactory.getSessionFactory().openSession();
			trx = sess.beginTransaction();
			Challenge ab = (Challenge) sess.get(Challenge.class, selectedChallenge.getId());
			
			Set<ChallengeTurn> puuh = ab.getTurns();
			
			Iterator<ChallengeTurn> iter = puuh.iterator();
			while (iter.hasNext()) {
				ChallengeTurn curren = iter.next();
				result.add(curren);
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

		return result;

	}
	
}
