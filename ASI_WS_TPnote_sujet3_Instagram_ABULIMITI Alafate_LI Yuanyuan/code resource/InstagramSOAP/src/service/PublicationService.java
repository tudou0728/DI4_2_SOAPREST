package service;

import java.util.ArrayList;
import java.util.List;

import dao.FollowsDAO;
import dao.PublicationDAO;
import dao.UserDAO;
import model.Follows;
import model.Publication;
import model.Response;
import model.User;

/**
 * publication service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class PublicationService {

	private PublicationDAO publicationDAO = new PublicationDAO();
	private FollowsDAO followsDAO = new FollowsDAO();
	private UserDAO userDAO = new UserDAO();

	/**
	 * post a 'Publication'.
	 * 
	 * @param publication
	 *            : a 'Publication'
	 * @return publication : a 'Publication'
	 */
	public void createNewPub(final Publication publication) {
		publicationDAO.create(publication);
	}

	/**
	 * delete a publication : if the user is not the owner of the publication, he
	 * can not delete this publication.
	 * 
	 * @param publication
	 *            : publication
	 * @param user
	 *            : user
	 * @return whether delete publication or not.
	 */
	public boolean deletePub(final Publication publication, User user) {
		if (publication.getUser().getuId() == user.getuId()) {
			publicationDAO.delete(publication);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * update the information of a publication
	 * 
	 * @param publication
	 *            : publication
	 * @param user
	 *            : user
	 * @param imagePath
	 *            : path of a image
	 * @param comment
	 *            : the comment of a publication
	 * @param responses
	 *            : responses of a publication
	 */
	public void updatePub(Publication publication, User user, String imagePath, String comment,
			List<Response> responses) {
		if (publication.getUser().getuId() == user.getuId()) {
			publicationDAO.update(publication, imagePath, comment, responses);
		}
	}

	/**
	 * get all publication of others
	 * 
	 * @param user
	 *            : user
	 * @param uFollew
	 *            : other user
	 * @return list of publication
	 */
	public List<Publication> getOtherAllPub(User user, User uFollew) {
		if (!uFollew.isPrivacy()) {
			return publicationDAO.getAll(uFollew);
		} else {
			List<Follows> getFollow = followsDAO.getFollow(uFollew.getuId(), user.getuId());
			if (getFollow != null && getFollow.size() > 0) {
				return publicationDAO.getAll(uFollew);
			} else
				return null;
		}
	}

	/**
	 * get publications of owner
	 * 
	 * @param user
	 *            : user owner
	 * @return a list of publication
	 */
	public List<Publication> getOwnAllPub(User user) {
		return publicationDAO.getAll(user);
	}

	/**
	 * get a publication by id
	 * 
	 * @param pId
	 *            : id of publication
	 * @param user
	 *            : user
	 * @return publication
	 */
	public Publication getById(int pId, User user) {
		Publication publication = publicationDAO.get(pId);
		if (publication != null) {
			if (user.getuId() == publication.getUser().getuId()) {
				return publication;
			} else {
				if (!publication.getUser().isPrivacy()) {
					return publication;
				} else {
					List<Follows> getFollow = followsDAO.getFollow(publication.getUser().getuId(), user.getuId());
					if (getFollow != null && getFollow.size() > 0) {
						return publication;
					} else {
						return null;
					}
				}
			}
		} else {
			return null;
		}
	}

	/**
	 * convert a publication to a two-dimensional list of string
	 * 
	 * @param publication
	 *            : publication
	 * @return a two-dimensional list of string
	 */
	public List<List<String>> convertToString(Publication publication) {
		return publicationDAO.convertToString(publication);
	}

	/**
	 * convert a list of publication to a three-dimensional list of string
	 * 
	 * @param publications
	 *            : a list of publication
	 * @return a three-dimensional list of string
	 */
	public List<List<List<String>>> convertToString(List<Publication> publications) {
		List<List<List<String>>> publication = new ArrayList<>();
		if (publications != null) {
			for (int i = 0; i < publications.size(); i++) {
				publication.add(convertToString(publications.get(i)));
			}
		}
		return publication;
	}

	public String convertToAString(Publication publication) {
		return publicationDAO.convertToAString(publication);
	}

	/**
	 * convert a list of publication to a list of string
	 * 
	 * @param publications
	 *            : a list of publications
	 * @return a list of string
	 */
	public List<String> convertToAString(List<Publication> publications) {
		List<String> result = new ArrayList<>();
		if (publications != null && publications.size() > 0) {
			for (int i = 0; i < publications.size(); i++) {
				result.add(convertToAString(publications.get(i)));
			}

		}
		return result;
	}
}
