package webService;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.User;
import service.AuthenticationService;
import service.UserService;

/**
 * web service user service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Path("/users")
public class UserWS {
	private UserService userService = new UserService();
	private AuthenticationService authenticationService = new AuthenticationService();

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
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> create(@QueryParam("userName") String userName, @QueryParam("password") String password,
			@QueryParam("privacy") boolean privacy) {
		User user = userService.create(userName, password, privacy);
		return userService.convertTostring(user);
	}

	/**
	 * update userName
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param newUserName
	 *            : new name
	 * @return new information of user
	 */
	@PUT
	@Path("userName")
	@Produces(MediaType.TEXT_PLAIN)
	public String updateName(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("newUserName") String newUserName) {
		User user = authenticationService.get(authCredentials);
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
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param newPassword
	 *            : new password
	 * @return new information of user
	 */
	@PUT
	@Path("password")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePassword(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("newPassword") String newPassword) {
		User user = authenticationService.get(authCredentials);
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
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param newPrivacy
	 *            : new privacy
	 * @return new information of user
	 */
	@PUT
	@Path("privacy")
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePrivacy(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("newPrivacy") boolean newPrivacy) {
		User user = authenticationService.get(authCredentials);
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
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @return whether delete successfully or not.
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(@HeaderParam("Authorization") String authCredentials) {
		User user = authenticationService.get(authCredentials);
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
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @return information of user
	 */
	@GET
	@Path("information")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getByNameAndPassword(@HeaderParam("Authorization") String authCredentials) {
		User user = authenticationService.get(authCredentials);
		return userService.convertTostring(user);
	}

}
