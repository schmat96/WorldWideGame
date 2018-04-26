package model;

import com.sun.javafx.application.LauncherImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.collection.internal.PersistentSet;

import org.hibernate.service.ServiceRegistry;
import org.jboss.logging.Logger.Level;

import exceptions.NotEnoughMoney;
import javafx.application.Preloader;
import main.Main;
import main.MyPreloader;



public class HibernateFactory {

	
	private static SessionFactory sessionFactory;
	
	private static SessionFactory buildSessionJavaConfigFactory() {
    	try {
    	Configuration configuration = new Configuration();
		
		//Create Properties, can be read from property files too
		Properties props = new Properties();
		props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		props.put("hibernate.connection.url", "jdbc:mysql://localhost/wwg");
		props.put("hibernate.connection.username", "root");
		props.put("hibernate.current_session_context_class", "thread");
		
		configuration.setProperties(props);
		
		//we can set mapping file or class with annotation
		//addClass(Employee1.class) will look for resource
		// com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
		configuration.addAnnotatedClass(Spieler.class);
		configuration.addAnnotatedClass(Charakter.class);
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    	System.out.println("Hibernate Java Config serviceRegistry created");
    	
    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	
        return sessionFactory;
    	}
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
	}

	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null) sessionFactory = buildSessionJavaConfigFactory();
        return sessionFactory;
    }
}
