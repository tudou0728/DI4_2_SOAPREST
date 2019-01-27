package service;

import java.util.ArrayList;
import java.util.List;

import dao.FollowsDAO;
import model.Follows;
import model.User;

/**
 * follows service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class FollowsService {
	private FollowsDAO followsDAO = new FollowsDAO();

	/**
	 * create a follow
	 * 
	 * @param follower
	 *            : the follower
	 * @param follow
	 *            : the follow
	 * @return follows
	 */
	public Follows create(User follower, User follow) {
		if (follow.getuId() != follower.getuId()) {
			Follows follows = new Follows(follow, follower);
			return followsDAO.create(follows);
		} else {
			return null;
		}
	}

	/**
	 * delete a follow
	 * 
	 * @param follower
	 *            : the follower
	 * @param follow
	 *            : the follow
	 * @return whether delete or not
	 */
	public boolean delete(User follower, User follow) {
		List<Follows> follows = followsDAO.getFollow(follow.getuId(), follower.getuId());
		if (follows != null && follows.size() > 0) {
			followsDAO.delete(follows.get(0));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * search all the follows of a user
	 * 
	 * @param user
	 *            : user
	 * @return a list of 'Follows'
	 */
	public List<Follows> getFollowsOfUser(User user) {
		return followsDAO.getFollowsOfUser(user.getuId());
	}

	/**
	 * search all the followers of a user
	 * 
	 * @param user
	 *            : user
	 * @return a list of 'Follows'
	 */
	public List<Follows> getFollowersOfUser(User user) {
		return followsDAO.getFollowersOfUser(user.getuId());
	}

	/**
	 * convert a 'Follows' object to a list of string.
	 * 
	 * @param follows
	 *            : a 'Follows'
	 * @return a list of string
	 */
	public List<String> convertToString(Follows follows) {
		return followsDAO.convertToString(follows);
	}

	/**
	 * convert a list of follows to a two-dimensional list of string
	 * 
	 * @param follows
	 *            : a list of follows
	 * @return a two-dimensional list of string
	 */
	public List<List<String>> convertToListOfString(List<Follows> follows) {
		List<List<String>> lists = new ArrayList<List<String>>();
		for (int i = 0; i < follows.size(); i++) {
			lists.add(followsDAO.convertToString(follows.get(i)));
		}
		return lists;
	}
}
