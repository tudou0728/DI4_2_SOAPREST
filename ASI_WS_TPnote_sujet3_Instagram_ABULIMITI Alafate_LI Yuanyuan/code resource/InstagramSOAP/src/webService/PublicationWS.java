package webService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.Publication;
import model.User;
import service.ConvertService;
import service.PublicationService;
import service.ResponseService;
import service.UserService;

/**
 * web service publication service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@WebService
public class PublicationWS {

	private PublicationService publicationService = new PublicationService();
	private ResponseService responseService = new ResponseService();
	private UserService userService = new UserService();
	private ConvertService convertService = new ConvertService();

	/**
	 * get owner's all publications
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @return publications
	 */
	@WebMethod
	public String[] getOwnAllPubs(String userName, String password) {
		User user = userService.get(userName, password);
		if (user != null) {
			List<Publication> publications = publicationService.getOwnAllPub(user);
			List<String> publicationString = publicationService.convertToAString(publications);
			return convertService.convertTo(publicationString);
		} else {
			return null;
		}
	}

	/**
	 * get other's all publications: if other user is not private, you can get
	 * publication, but if he is private, only if you follow this user, otherwise
	 * you can not watch his publications.
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param follew_uId
	 *            : id of other user.
	 * @return publications
	 */
	@WebMethod
	public String[] getOtherAllPubs(String userName, String password, int follew_uId) {
		User user = userService.get(userName, password);
		if (user != null) {
			User uFollow = userService.getById(follew_uId);
			List<Publication> publications = publicationService.getOtherAllPub(user, uFollow);
			List<String> publicationString = publicationService.convertToAString(publications);
			return convertService.convertTo(publicationString);
		} else {
			return null;
		}
	}

	/**
	 * get publication by id: (1). if this is your publication you can watch it.
	 * (2). if this is other's publication,and other user is not private, you can
	 * get this publication, but if he is private, only if you follow this user,
	 * otherwise you can not watch this publication.
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param pId
	 *            : id of publication
	 * @return publication
	 */
	@WebMethod
	public String getPub(String userName, String password, int pId) {
		User user = userService.get(userName, password);
		if (user != null) {
			Publication publication = publicationService.getById(pId, user);
			if (publication != null) {
				return publicationService.convertToAString(publication);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * delete user's own publication by id of publication
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param pId
	 *            : id of publication
	 * @return delete publication whether successfully.
	 */
	@WebMethod
	public String deletePub(String userName, String password, int pId) {
		User user = userService.get(userName, password);
		if (user != null) {
			Publication publication = publicationService.getById(pId, user);
			if (publication != null) {
				if (publicationService.deletePub(publication, user)) {
					return "delete publication successfully";
				} else {
					return "You do not have the permission to delete this publication.";
				}
			} else {
				return "can not find the publication.";
			}
		} else {
			return "wrong name or password.";
		}

	}

	/**
	 * post a publication.
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param imagePath : path of image
	 * @param comment : comment
	 * @return publication
	 */
	@WebMethod
	public String createPub(String userName, String password, String imagePath, String comment) {
		User user = userService.get(userName, password);
		if (user != null) {
			Publication publication = new Publication(imagePath, comment, user);
			publicationService.createNewPub(publication);
			return publicationService.convertToAString(publication);
		} else {
			return null;
		}
	}

}
