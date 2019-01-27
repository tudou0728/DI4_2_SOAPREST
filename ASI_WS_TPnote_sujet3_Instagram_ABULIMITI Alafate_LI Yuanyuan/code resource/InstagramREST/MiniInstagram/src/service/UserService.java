package service;

import java.util.List;

import dao.UserDAO;
import model.User;

/**
 * user service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class UserService {
	private UserDAO userDAO = new UserDAO();

	/**
	 * user log in
	 * 
	 * @param userName
	 *            : name of user
	 * @param password
	 *            : password of user
	 * @return whether user log in or not.
	 */
	public boolean login(String userName, String password) {
		return userDAO.isValide(userName, password);
	}

	/**
	 * get information by user Id
	 * 
	 * @param uId
	 *            : id of user
	 * @return user
	 */
	public User getById(int uId) {
		return userDAO.getById(uId);
	}

	/**
	 * create a user account.
	 * 
	 * @param userName
	 *            : name
	 * @param password
	 *            : password
	 * @param privacy
	 *            : privacy
	 * @return user
	 */
	public User create(String userName, String password, boolean privacy) {
		User user = new User(userName, password, privacy);
		return userDAO.create(user);
	}

	/**
	 * update the information of a user
	 * 
	 * @param user
	 *            : user
	 * @param userName
	 *            : new name
	 * @param password
	 *            : new password
	 * @param privacy
	 *            : new privacy
	 * @return user
	 */
	public User update(User user, String userName, String password, boolean privacy) {
		return userDAO.update(user, userName, password, privacy);
	}

	/**
	 * get user
	 * 
	 * @param userName
	 *            : name of user
	 * @param password
	 *            : password of user
	 * @return user
	 */
	public User get(String userName, String password) {
		return userDAO.get(userName, password);
	}

	/**
	 * delete a user : user just can delete his own account.
	 * 
	 * @param user
	 *            : user
	 * @return whether delete a user
	 */
	public boolean delete(User user) {
		return userDAO.delete(user);
	}

	/**
	 * convert a user to a list of string
	 * 
	 * @param user
	 *            : user
	 * @return a list of string
	 */
	public List<String> convertTostring(User user) {
		return userDAO.convertTostring(user);
	}
}
