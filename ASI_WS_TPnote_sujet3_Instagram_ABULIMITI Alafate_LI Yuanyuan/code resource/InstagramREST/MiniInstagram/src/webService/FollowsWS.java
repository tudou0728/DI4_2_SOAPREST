package webService;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Follows;
import model.User;
import service.AuthenticationService;
import service.FollowsService;
import service.UserService;

/**
 * web service follow service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Path("/follows")
public class FollowsWS {
	private FollowsService followsService = new FollowsService();
	private AuthenticationService authenticationService = new AuthenticationService();
	private UserService userService = new UserService();

	/**
	 * user follows other user.
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param followId
	 *            : other user's id
	 * @return the information of follow to tell user whether follows successfully.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> create(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("followId") int followId) {
		User user = authenticationService.get(authCredentials);
		User follow = userService.getById(followId);
		if (follow != null) {
			Follows follows = followsService.create(user, follow);
			return followsService.convertToString(follows);
		} else {
			return null;
		}
	}

	/**
	 * do not follow other user.
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param followId
	 *            : other user's id
	 * @return the information of follow to tell user whether unfollows
	 *         successfully.
	 */
	@DELETE
	@Path("follow")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFollow(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("followId") int followId) {
		User user = authenticationService.get(authCredentials);
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
	}

	/**
	 * remove a follower
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param followerId
	 *            : id of follower
	 * @return the information to tell user whether remove a follower successfully.
	 */
	@DELETE
	@Path("follower")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFollower(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("followerId") int followerId) {
		User user = authenticationService.get(authCredentials);
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
	}

	/**
	 * get all follows of user
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @return information of all follows.
	 */
	@GET
	@Path("allFollows")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<String>> getFollowsOfUser(@HeaderParam("Authorization") String authCredentials) {
		User user = authenticationService.get(authCredentials);
		List<Follows> follows = followsService.getFollowsOfUser(user);
		return followsService.convertToListOfString(follows);
	}

	/**
	 * get all follows of user
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @return information of all followers.
	 */
	@GET
	@Path("allFollowers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<String>> getFollowersOfUser(@HeaderParam("Authorization") String authCredentials) {
		User user = authenticationService.get(authCredentials);
		List<Follows> follows = followsService.getFollowersOfUser(user);
		return followsService.convertToListOfString(follows);
	}
}
