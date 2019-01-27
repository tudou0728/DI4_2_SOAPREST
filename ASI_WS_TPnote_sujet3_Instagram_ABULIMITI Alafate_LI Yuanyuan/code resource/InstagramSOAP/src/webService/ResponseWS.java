package webService;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.Publication;
import model.Response;
import model.User;
import service.ConvertService;
import service.PublicationService;
import service.ResponseService;
import service.UserService;

@WebService
public class ResponseWS {
	private ResponseService responseService = new ResponseService();
	private PublicationService publicationService = new PublicationService();
	private UserService userService = new UserService();
	private ConvertService convertService = new ConvertService();

	/**
	 * get response by publication Id: (1). If this is your publication you can
	 * watch it. (2). If this is other's publication,and the other user is not
	 * private, you can get this publication and all the responses of this
	 * publication, but if he is private, only if you follow this user, otherwise
	 * you can not watch this publication and responses.
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param pId
	 *            : id of publication
	 * @return responses
	 */
	@WebMethod
	public String[] getAllResByPid(String userName, String password, int pId) {
		User user = userService.get(userName, password);
		if (user != null) {
			List<Response> responses = responseService.getAllResByPid(pId, user);
			List<String> responseList = responseService.convertToListString(responses);
			return convertService.convertTo(responseList);
		} else {
			return null;
		}
	}

	/**
	 * delete a response by id: only owner of this response can delete this response
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param rId
	 *            : id of response
	 * @return whether delete successfully or not.
	 */
	@WebMethod
	public String deleteRes(String userName, String password, int rId) {
		User user = userService.get(userName, password);
		if (user != null) {
			Response response = responseService.getById(rId);
			if (responseService.deleteRes(response, user)) {
				return "delete response successfullly.";
			} else {
				return "You do not have the permission to response this publication.";
			}
		} else {
			return "wrong name or password.";
		}
	}

	/**
	 * response a publication:(1). If this is your publication you can response it.
	 * (2). If this is other's publication,and the other user is not private, you
	 * can also response, but if he is private, only if you follow this user,
	 * otherwise you can not response this publication.
	 * 
	 * @param userName
	 *            : name for login
	 * @param password
	 *            : password for log in
	 * @param comment
	 * @param pId
	 *            : id of publication
	 * @return response
	 */
	@WebMethod
	public String[] createRes(String userName, String password, String comment, int pId) {
		User user = userService.get(userName, password);
		if (user != null) {
			Publication publication = publicationService.getById(pId, user);
			if (publication != null) {
				Response response = new Response(comment, user, publication);
				responseService.createRes(response, user);
				List<String> responseString = responseService.convertToString(response);
				return convertService.convertTo(responseString);
			} else {
				List<String> message = new ArrayList<>();
				message.add("You do not have the permission to response this publication.");
				return convertService.convertTo(message);
			}
		} else {
			return null;
		}

	}

}
