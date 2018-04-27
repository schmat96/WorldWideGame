package model;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import main.Main;



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
		configuration.addAnnotatedClass(Universum.class);
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
	
	private static SessionFactory buildSessionJavaConfigFactory(Main main) {
    	try {
    	Configuration configuration = new Configuration();
		
		//Create Properties, can be read from property files too
		Properties props = new Properties();
		props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		props.put("hibernate.connection.url", "jdbc:mysql://localhost/wwg");
		props.put("hibernate.connection.username", "root");
		props.put("hibernate.current_session_context_class", "thread");
		main.notifyProgress("Loading Properties");
		configuration.setProperties(props);
		main.notifyProgress("Setting Properties");
		//we can set mapping file or class with annotation
		//addClass(Employee1.class) will look for resource
		// com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
		configuration.addAnnotatedClass(Spieler.class);
		main.notifyProgress("Loading Spieler Klass");
		configuration.addAnnotatedClass(Universum.class);
		main.notifyProgress("Loading Universum Klass");
		configuration.addAnnotatedClass(Charakter.class);
		main.notifyProgress("Loading Charakter Klass");
		
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		main.notifyProgress("Loading Hibernate Java Config");
    	
    	SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    	main.notifyProgress("Loading Charakter Klass");
    	
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
	
	public static SessionFactory getSessionFactory(Main main) {
		if(sessionFactory == null) sessionFactory = buildSessionJavaConfigFactory(main);
        return sessionFactory;
    }
	
	
}
