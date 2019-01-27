package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.FollowsDAO;
import dao.PublicationDAO;
import dao.ResponseDAO;
import model.Follows;
import model.Publication;
import model.Response;
import model.User;

/**
 * response service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class ResponseService {

	private ResponseDAO responseDAO = new ResponseDAO();
	private PublicationDAO publicationDAO = new PublicationDAO();
	private FollowsDAO followsDAO = new FollowsDAO();

	/**
	 * post a response
	 * 
	 * @param response
	 *            : response
	 * @param user
	 *            : user that posts this response
	 */
	public void createRes(Response response, User user) {
		User uCreater = response.getPublication().getUser();
		if (uCreater.getuId() == user.getuId()) {
			responseDAO.create(response);
		} else if (!uCreater.isPrivacy()) {
			responseDAO.create(response);
		} else {
			List<Follows> getFollow = followsDAO.getFollow(uCreater.getuId(),user.getuId());
			if (getFollow != null || getFollow.size() > 0) {
				responseDAO.create(response);
			}
		}
	}

	/**
	 * delete a response
	 * 
	 * @param response
	 *            : response
	 * @param user
	 *            : user
	 * @return whether delete a response or not
	 */
	public boolean deleteRes(Response response, User user) {
		if (response.getUser().getuId() == user.getuId()) {
			responseDAO.delete(response);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * update a response
	 * 
	 * @param response
	 *            : response
	 * @param comment
	 *            : comment
	 * @param date
	 *            : date
	 * @param user
	 *            : user
	 * @param publication
	 *            : publication
	 */
	public void updateRes(Response response, String comment, Date date, User user, Publication publication) {
		responseDAO.update(response, comment, date, user, publication);
	}

	/**
	 * get all responses
	 * 
	 * @return a list of response
	 */
	public List<Response> getAllRes() {
		return responseDAO.getAll();
	}

	/**
	 * get all response by publication Id
	 * 
	 * @param pId
	 *            : id of publication
	 * @param user
	 *            : user
	 * @return a list of response
	 */
	public List<Response> getAllResByPid(int pId, User user) {
		Publication publication = publicationDAO.get(pId);
		int uFollowId = publication.getUser().getuId();
		if (uFollowId == user.getuId()) {
			return responseDAO.getAllByPid(pId);
		} else if (!user.isPrivacy()) {
			return responseDAO.getAllByPid(pId);
		} else {
			List<Follows> getFollow = followsDAO.getFollow(uFollowId, user.getuId());
			if (getFollow != null || getFollow.size() > 0) {
				return responseDAO.getAllByPid(pId);
			} else {
				return null;
			}
		}
	}

	/**
	 * get response by id
	 * 
	 * @param rId
	 *            : response Id
	 * @return : response
	 */
	public Response getById(int rId) {
		return responseDAO.get(rId);
	}

	/**
	 * convert a response to a list of string
	 * 
	 * @param response
	 *            : response
	 * @return a list of string
	 */
	public List<String> convertToString(Response response) {
		return responseDAO.convertToString(response);
	}

	/**
	 * convert a list of response to a two-dimensional list of string
	 * 
	 * @param responses
	 *            : list of response
	 * @return a two-dimensional list of string
	 */
	public List<List<String>> convertToString(List<Response> responses) {
		List<List<String>> responseLists = new ArrayList<>();
		if (responses != null) {
			for (int i = 0; i < responses.size(); i++) {
				responseLists.add(convertToString(responses.get(i)));
			}
		}
		return responseLists;
	}
}
