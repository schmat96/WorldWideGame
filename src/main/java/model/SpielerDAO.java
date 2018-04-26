package model;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.collection.internal.PersistentSet;

import exceptions.NotEnoughMoney;

public class SpielerDAO {
	
	public void addMoney(Spieler sp, int money) throws NotEnoughMoney {
		Session sess = null;
	    Transaction trx  = null;
	    try {
	    	
	    	 if (money < 0 && money*-1>sp.getGeld()) {
	    		 
		    	  throw new NotEnoughMoney("Nicht genug Geld!");
		    	  
		      }	
	    	
	    	 sess = HibernateFactory.getSessionFactory().openSession();
	      trx  = sess.beginTransaction();
	      
	     
   		  sp.setGeld(sp.getGeld()+money);
   		  sess.merge(sp);
   		  trx.commit();

	      
	    } catch( HibernateException ex ) {
	      if( trx != null )
	        try { trx.rollback(); } catch( HibernateException exRb ) {}
	      throw new RuntimeException( ex.getMessage() );
	    } finally {
	      try { if( sess != null ) sess.close(); } catch( Exception exCl ) {}
	    }
	}
	
	public Spieler checkUserLogin(String Name, String pw) {
		
		Session sess = null;
	    Transaction trx  = null;
	    ArrayList<Spieler> result;
	    try {
	    	sess = HibernateFactory.getSessionFactory().openSession();
	    	trx  = sess.beginTransaction();

	      result = (ArrayList<Spieler>) sess.createQuery("from Spieler E WHERE E.name like '"+Name+"'").list();
	      if (result.size()==1) {
	    	  if (pw.equals(result.get(0).getPasswort())) {
	    		  Spieler sp = result.get(0);
	    		  sess.merge(sp);
	    		  trx.commit();
		    	  return result.get(0);
	    	  }
	    	  return null;
	      } else {
	    	  return null;
	      }
	      
	    } catch( HibernateException ex ) {
	      if( trx != null )
	        try { trx.rollback(); } catch( HibernateException exRb ) {}
	      throw new RuntimeException( ex.getMessage() );
	    } finally {
	      try { if( sess != null ) sess.close(); } catch( Exception exCl ) {}
	    }
	 
	}

	
	private ArrayList<Spieler> getPlayers() {
		Session sess = null;
	    Transaction trx  = null;
	    ArrayList<Spieler> result;
	    try {
	    	sess = HibernateFactory.getSessionFactory().openSession();
	      trx  = sess.beginTransaction();

	      result = (ArrayList<Spieler>) sess.createQuery("from Spieler").list();
	      
	    } catch( HibernateException ex ) {
	      if( trx != null )
	        try { trx.rollback(); } catch( HibernateException exRb ) {}
	      throw new RuntimeException( ex.getMessage() );
	    } finally {
	      try { if( sess != null ) sess.close(); } catch( Exception exCl ) {}
	    }
	    
	    return result;
	}
	
	
	private Spieler generatePlayer( String name, String passwort, int exp, int geld )
	  {
	    Session sess = null;
	    Transaction trx  = null;
	    try {
	    	sess = HibernateFactory.getSessionFactory().openSession();
	      trx  = sess.beginTransaction();
	      Spieler sp = new Spieler();
	      sp.setName(name);
	      sp.setPasswort(passwort);
	      sp.setExp(exp);
	      sp.setGeld(geld);
	      sess.save( sp );
	      trx.commit();
	      return sp;
	    } catch( HibernateException ex ) {
	      if( trx != null )
	        try { trx.rollback(); } catch( HibernateException exRb ) {}
	      throw new RuntimeException( ex.getMessage() );
	    } finally {
	      try { if( sess != null ) sess.close(); } catch( Exception exCl ) {}
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
		return this.generatePlayer(name, pw, 0, 100);
	}
}