package webService;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.User;
import service.ConvertService;
import service.UserService;

/**
 * web service user service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@WebService
public class UserWS {
	private UserService userService = new UserService();
	private ConvertService convertService = new ConvertService();

	/**
	 * create a user
	 * 
	 * @param userName
	 *            : name
	 * @param password
	 *            : password
	 * @param privacy
	 *            : whether private or not
	 * @return information of user.
	 */
	@WebMethod
	public String[] createUser(String userName, String password, boolean privacy) {
		User user = userService.create(userName, password, privacy);
		return convertService.convertTo(userService.convertTostring(user));
	}

	/**
	 * update userName
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param newUserName
	 *            : new name
	 * @return new information of user
	 */
	@WebMethod
	public String updateName(String userName, String password, String newUserName) {
		User user = userService.get(userName, password);
		if (user != null) {
			User userUpdate = userService.update(user, newUserName, user.getPassword(), user.isPrivacy());
			return "New name: " + userUpdate.getUserName();

		} else {
			return null;
		}
	}

	/**
	 * update password
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param newPassword
	 *            : new password
	 * @return new information of user
	 */
	@WebMethod
	public String updatePassword(String userName, String password, String newPassword) {
		User user = userService.get(userName, password);
		if (user != null) {
			User userUpdate = userService.update(user, user.getUserName(), newPassword, user.isPrivacy());
			return "New password: " + userUpdate.getPassword();
		} else {
			return null;
		}
	}

	/**
	 * update privacy
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param newPrivacy
	 *            : new privacy
	 * @return new information of user
	 */
	@WebMethod
	public String updatePrivacy(String userName, String password, boolean newPrivacy) {
		User user = userService.get(userName, password);
		if (user != null) {
			User userUpdate = userService.update(user, user.getUserName(), user.getPassword(), newPrivacy);
			return "New privacy: " + userUpdate.isPrivacy();
		} else {
			return null;
		}
	}

	/**
	 * delete a user
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @return whether delete successfully or not.
	 */
	@WebMethod
	public String deleteUser(String userName, String password) {
		User user = userService.get(userName, password);
		if (user != null) {
			if (userService.delete(user)) {
				return "delete user successfully.";
			} else {
				return "You do not have the permission to delete this user or you can not delete this user.";
			}
		}
		return "can not find this user.";
	}

	/**
	 * get information of user
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @return information of user
	 */
	@WebMethod
	public String[] getUserByNameAndPassword(String userName, String password) {
		User user = userService.get(userName, password);
		if (user != null) {
			return convertService.convertTo(userService.convertTostring(user));
		} else {
			return null;
		}
	}

}
