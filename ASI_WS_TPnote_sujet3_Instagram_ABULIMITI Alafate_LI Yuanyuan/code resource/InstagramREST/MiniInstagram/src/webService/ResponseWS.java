package webService;

import java.util.ArrayList;
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
import model.Response;
import model.User;
import service.AuthenticationService;
import service.PublicationService;
import service.ResponseService;

/**
 * web service response service
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Path("/responses")
public class ResponseWS {
	private ResponseService responseService = new ResponseService();
	private PublicationService publicationService = new PublicationService();
	private AuthenticationService authenticationService = new AuthenticationService();

	/**
	 * get response by publication Id: (1). If this is your publication you can
	 * watch it. (2). If this is other's publication,and the other user is not
	 * private, you can get this publication and all the responses of this
	 * publication, but if he is private, only if you follow this user, otherwise
	 * you can not watch this publication and responses.
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param pId
	 *            : id of publication
	 * @return responses
	 */
	@GET
	@Path("publicationId")
	@Produces(MediaType.APPLICATION_JSON)
	public List<List<String>> getAllResByPid(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("pId") int pId) {
		User user = authenticationService.get(authCredentials);
		List<Response> responses = responseService.getAllResByPid(pId, user);
		List<List<String>> responseList = responseService.convertToString(responses);
		return responseList;

	}

	/**
	 * delete a response by id: only owner of this response can delete this response
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param rId
	 *            : id of response
	 * @return whether delete successfully or not.
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRes(@HeaderParam("Authorization") String authCredentials, @QueryParam("rId") int rId) {
		User user = authenticationService.get(authCredentials);
		Response response = responseService.getById(rId);
		if (responseService.deleteRes(response, user)) {
			return "delete response successfullly.";
		} else {
			return "You do not have the permission to response this publication.";
		}
	}

	/**
	 * response a publication:(1). If this is your publication you can response it.
	 * (2). If this is other's publication,and the other user is not private, you
	 * can also response, but if he is private, only if you follow this user,
	 * otherwise you can not response this publication.
	 * 
	 * @param authCredentials
	 *            : HEADER_Basic_Authentication with userName and password
	 * @param comment
	 *            : comment
	 * @param pId
	 *            : id of publication
	 * @return response
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> createRes(@HeaderParam("Authorization") String authCredentials,
			@QueryParam("comment") String comment, @QueryParam("pId") int pId) {
		User user = authenticationService.get(authCredentials);
		Publication publication = publicationService.getById(pId, user);
		if (publication != null) {
			Response response = new Response(comment, user, publication);
			responseService.createRes(response, user);
			List<String> responseString = responseService.convertToString(response);
			return responseString;
		} else {
			List<String> message = new ArrayList<>();
			message.add("You do not have the permission to response this publication.");
			return message;
		}
	}

}
