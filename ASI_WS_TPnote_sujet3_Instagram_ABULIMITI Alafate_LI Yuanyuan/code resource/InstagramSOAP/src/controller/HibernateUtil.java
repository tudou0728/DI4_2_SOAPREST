package controller;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * the class for session to control JDBC
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class HibernateUtil {
	public static final SessionFactory sessionFactory;

	/**
	 * create sessionFactory
	 */
	static {
		try {
			Configuration cfg = new Configuration().configure();
			sessionFactory = cfg.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	/**
	 * Create session
	 * 
	 * @return session: the current session
	 * @throws HibernateException
	 */
	public static Session currentSession() throws HibernateException {
		Session s = session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	/**
	 * Close session
	 * 
	 * @throws HibernateException
	 */
	public static void closeSession() throws HibernateException {
		Session s = session.get();
		if (s != null)
			s.close();
		session.set(null);
	}
}
