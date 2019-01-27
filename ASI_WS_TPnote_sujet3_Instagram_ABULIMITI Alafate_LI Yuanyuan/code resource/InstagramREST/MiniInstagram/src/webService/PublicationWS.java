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

import model.Publication;
import model.User;
import service.AuthenticationService;
import service.PublicationService;
import service.ResponseService;
import service.UserService;

/**
 * web service publication service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Path("/publications")
public class PublicationWS {

	private PublicationService publicationService = new PublicationService();
	private UserService userService = new UserService();
	private ResponseService responseService = new ResponseService();
	private AuthenticationService authenticationService = new AuthenticationService();

	/**
	 * get owner's all publications
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @return publications
	 */
	@GET
	@Path("ownerAllPublications")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<List<String>>> getOwnAllPubs(@HeaderParam("Authorization") String authCredentials) {
		User user = authenticationService.get(authCredentials);
		List<Publication> publications = publicationService.getOwnAllPub(user);
		List<List<List<String>>> publicationList = publicationService.convertToString(publications);
		return publicationList;
	}

	/**
	 * get other's all publications: if other user is not private, you can get
	 * publication, but if he is private, only if you follow this user, otherwise
	 * you can not watch his publications.
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param follew_uId
	 *            : id of other user.
	 * @return publications
	 */
	@GET
	@Path("others")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<List<String>>> getOtherAllPubs(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("uId") int follew_uId) {
		User user = authenticationService.get(authCredentials);
		User uFollew = userService.getById(follew_uId);
		List<Publication> publications = publicationService.getOtherAllPub(user, uFollew);
		List<List<List<String>>> publicationList = publicationService.convertToString(publications);
		return publicationList;
	}

	/**
	 * get publication by id: (1). if this is your publication you can watch it.
	 * (2). if this is other's publication,and other user is not private, you can
	 * get this publication, but if he is private, only if you follow this user,
	 * otherwise you can not watch this publication.
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param pId
	 *            : id of publication
	 * @return publication
	 */
	@GET
	@Path("publicationId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<String>> getPub(@HeaderParam("Authorization") String authCredentials, @QueryParam("pId") int pId) {
		User user = authenticationService.get(authCredentials);
		Publication publication = publicationService.getById(pId, user);
		if (publication != null) {
			List<List<String>> pList = publicationService.convertToString(publication);
			System.out.println("lalala5");
			return pList;
		} else {
			return null;
		}
	}

	/**
	 * delete user's own publication by id of publication
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param pId
	 *            : id of publication
	 * @return delete publication whether successfully.
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePub(@HeaderParam("Authorization") String authCredentials, @QueryParam("pId") int pId) {
		User user = authenticationService.get(authCredentials);
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

	}

	/**
	 * post a publication.
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param imagePath
	 *            : path of image
	 * @param comment
	 *            : comment
	 * @return publication
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<String>> createPub(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("imagePath") String imagePath, @QueryParam("comment") String comment) {
		User user = authenticationService.get(authCredentials);
		Publication publication = new Publication(imagePath, comment, user);
		publicationService.createNewPub(publication);
		List<List<String>> pList = publicationService.convertToString(publication);
		return pList;
	}

}
