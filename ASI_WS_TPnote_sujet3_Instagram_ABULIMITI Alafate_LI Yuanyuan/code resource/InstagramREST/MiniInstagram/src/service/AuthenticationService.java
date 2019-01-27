package service;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import model.User;

/**
 * the filter to check if the user login correctly.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class AuthenticationService {
	private UserService UserService = new UserService();

	/**
	 * verify if user logs in.
	 * 
	 * @param authCredentials
	 *            : the information of HEADERS - Basic_Authen.
	 * @return boolean : whether login
	 */
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials) {
			return false;
		}
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String userName = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		boolean authenticationStatus = UserService.login(userName, password);
		return authenticationStatus;
	}

	/**
	 * get all the information of the user logs in.
	 * 
	 * @param authCredentials
	 *            : the information of HEADERS - Basic_Authen.
	 * @return user
	 */
	public User get(String authCredentials) {
		if (null == authCredentials) {
			return null;
		}
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String userName = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		return UserService.get(userName, password);
	}
}
