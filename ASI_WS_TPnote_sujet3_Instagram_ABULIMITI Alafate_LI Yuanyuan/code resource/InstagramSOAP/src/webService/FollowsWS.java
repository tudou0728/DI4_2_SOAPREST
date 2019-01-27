package webService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.Follows;
import model.User;
import service.ConvertService;
import service.FollowsService;
import service.UserService;

/**
 * web service follow service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@WebService
public class FollowsWS {
	private FollowsService followsService = new FollowsService();
	private UserService userService = new UserService();
	private ConvertService convertService = new ConvertService();

	/**
	 * user follows other user.
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param followId
	 *            : other user's id
	 * @return the information of follow to tell user whether follows successfully.
	 */
	@WebMethod
	public String[] createFollow(String userName, String password, int followId) {
		User user = userService.get(userName, password);
		User follow = userService.getById(followId);
		if (follow != null) {
			Follows follows = followsService.create(user, follow);
			return convertService.convertTo(followsService.convertToString(follows));
		} else {
			return null;
		}
	}

	/**
	 * do not follow other user.
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param followId
	 *            : other user's id
	 * @return the information of follow to tell user whether unfollows
	 *         successfully.
	 */
	@WebMethod
	public String deleteFollow(String userName, String password, int followId) {
		User user = userService.get(userName, password);
		if (user != null) {
			User follow = userService.getById(followId);
			if (follow != null) {
				if (followsService.delete(user, follow)) {
					return "delete follow successfully";
				} else {
					return "You do not have the permission to delete this follow.";
				}

			} else {
				return "delete error: can not find the follow.";
			}
		} else {
			return "wrong name or password.";
		}

	}

	/**
	 * remove a follower
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param followerId
	 *            : id of follower
	 * @return the information to tell user whether remove a follower successfully.
	 */
	@WebMethod
	public String deleteFollower(String userName, String password, int followerId) {
		User user = userService.get(userName, password);
		if (user != null) {
			User follower = userService.getById(followerId);
			if (follower != null) {
				if (followsService.delete(follower, user)) {
					return "delete follower successfully";
				} else {
					return "You do not have the permission to delete this follower.";
				}
			} else {
				return "delete error: can not find the follower.";
			}
		} else {
			return "wrong name or password.";
		}
	}

	/**
	 * get all follows of user
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @return information of all follows.
	 */
	@WebMethod
	public String[] getFollowsOfUser(String userName, String password) {
		User user = userService.get(userName, password);
		if (user != null) {
			List<Follows> follows = followsService.getFollowsOfUser(user);
			return convertService.convertTo(followsService.convertListToString(follows));
		} else {
			return null;
		}
	}

	/**
	 * get all follows of user
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @return information of all followers.
	 */
	@WebMethod
	public String[] getFollowersOfUser(String userName, String password) {
		User user = userService.get(userName, password);
		if (user != null) {
			List<Follows> follows = followsService.getFollowersOfUser(user);
			return convertService.convertTo(followsService.convertListToString(follows));
		} else {
			return null;
		}
	}
}
