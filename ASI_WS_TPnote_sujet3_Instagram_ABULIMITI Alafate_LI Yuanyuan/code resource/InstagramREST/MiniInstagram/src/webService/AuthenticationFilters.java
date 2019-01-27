package webService;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import service.AuthenticationService;

/**
 * class filter that implements 'ContainerRequestFilter' to verify whether user
 * log in or not.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Provider
public class AuthenticationFilters implements ContainerRequestFilter {

	public static final String AUTHENTICATION_HEADER_KEY = "Authorization";

	/**
	 * filter HEADER: Basic Authentication
	 */
	@Override
	public void filter(ContainerRequestContext containerRequestContext) throws IOException {
		// TODO Auto-generated method stub
		if (!((containerRequestContext.getUriInfo().getAbsolutePath().getPath().contains("users"))
				&& containerRequestContext.getMethod().equals("POST"))) {
			String authCredentials = containerRequestContext.getHeaderString(AUTHENTICATION_HEADER_KEY);
			AuthenticationService authenticationService = new AuthenticationService();
			if (!authenticationService.authenticate(authCredentials)) {
				ResponseBuilder responseBuilder = null;
				responseBuilder = Response.serverError();
				Response response = responseBuilder.status(Status.UNAUTHORIZED)
						.entity("User cannot access the resource, please logIn by using HEADERS: Basic Authentication.")
						.build();
				containerRequestContext.abortWith(response);
				return;
			} else {
				return;
			}
		} else {
			return;
		}
	}

}
