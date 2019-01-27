package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.User;

/**
 * the class DAO to access the data of the table user.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class UserDAO {
	/**
	 * get all users
	 * 
	 * @return a list of user.
	 */
	public List<User> getAll() {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from User user";
			Query query = session.createQuery(sql);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * create a user
	 * 
	 * @param user
	 *            : a user
	 * @return user
	 */
	public User create(User user) {
		try {
			if (getById(user.getuId()) == null) {
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(user);
				transaction.commit();
			}
			return user;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * delete a user
	 * @param user : user
	 * @return whether delete or not
	 */
	public boolean delete(User user) {
		Session session = HibernateUtil.currentSession();
		boolean result = false;
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(user);
			transaction.commit();
			HibernateUtil.closeSession();
			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
		return result;

	}

	/**
	 * get user by user Id
	 * @param uId : user id
	 * @return user
	 */
	public User getById(int uId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(User.class, uId);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * verify a user with name and password
	 * @param userName : name
	 * @param password : password
	 * @return whether verify or not
	 */
	public boolean isValide(String userName, String password) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from User user where user.userName=:userName and user.password=:password";
			Query query = session.createQuery(sql);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			List<User> users = query.getResultList();
			System.out.println(users.size());
			if (users.size() == 1) {
				return true;
			} else {
				return false;
			}
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * update a user
	 * @param user : user
	 * @param userName : new name
	 * @param password : new password
	 * @param privacy : new privacy
	 * @return user
	 */
	public User update(User user, String userName, String password, boolean privacy) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			if (userName != null && !userName.equals("")) {
				user.setUserName(userName);
			}
			if (password != null && !password.equals("")) {
				user.setPassword(password);
			}
			user.setPrivacy(privacy);
			session.update(user);
			transaction.commit();
			return user;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get user by name and password
	 * 
	 * @param userName
	 *            : name of a user
	 * @param password
	 *            : password of a user
	 * @return user
	 */
	public User get(String userName, String password) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from User user where user.userName=:userName and user.password=:password";
			Query query = session.createQuery(sql);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			List<User> users = query.getResultList();
			System.out.println(users.size());
			if (users.size() > 0) {
				User user = users.get(0);
				return user;
			} else {
				return null;
			}
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * convert a user to a list of string
	 * 
	 * @param user
	 *            : a user
	 * @return user
	 */
	public List<String> convertTostring(User user) {
		List<String> userInfo = new ArrayList<String>();
		userInfo.add("userName is: " + user.getUserName());
		userInfo.add("password is: " + user.getPassword());
		userInfo.add("privacy or not: " + user.isPrivacy());
		return userInfo;
	}
}
