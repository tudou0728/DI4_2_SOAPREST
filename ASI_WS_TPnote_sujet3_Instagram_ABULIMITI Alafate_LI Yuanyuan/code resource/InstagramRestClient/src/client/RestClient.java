package client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

/**
 * client to test REST webService. 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class RestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("LiYuanyuan", "haha");// add HEADER Basic_Authentication
		client.register(feature);
		/**
		 * Basic URL to server REST service: "http://localhost:8080/MiniInstagram/rest".
		 * (All code is as comment at present, you can make it from the mode comment to mode program, and test directly.)
		 */
		/**
		 * Test: webService get information of the user: @GET. user can only get his own
		 * information. 
		 */
		// this is the basic URL for the server service
		WebTarget webTarget = client.target("http://localhost:8080/MiniInstagram/rest");
		WebTarget userWebTarget = webTarget.path("users");
//		WebTarget userWebTargetInfo = userWebTarget.path("information");
//		Invocation.Builder invocationBuilder = userWebTargetInfo.request(MediaType.APPLICATION_JSON);
//		invocationBuilder.header("some-header", "true");
//		Response response = invocationBuilder.get();
//		System.out.println(response.getStatus());
//		System.out.println(response.readEntity(String.class));

		/** Test: webService create a user: @POST */
//		WebTarget userWebTargetcreate = userWebTarget.queryParam("userName", "Li");
//		WebTarget userWebTargetcreateName = userWebTargetcreate.queryParam("password", "Yuanyuan");
//		WebTarget userWebTargetcreatePrivacy = userWebTargetcreateName.queryParam("privacy", true);
//		Invocation.Builder invocationBuilderCreateUser = userWebTargetcreatePrivacy.request(MediaType.APPLICATION_JSON);
//		invocationBuilderCreateUser.header("some-header", "true");
//		Response responseCreate = invocationBuilderCreateUser.post(Entity.entity("create", MediaType.APPLICATION_JSON));
//		System.out.println(responseCreate.getStatus());
//		System.out.println(responseCreate.readEntity(String.class));

		/**
		 * Test: webService change the name of user: @PUT. user can only change his own
		 * account.
		 */
//		WebTarget userWebTarChange = userWebTarget.path("userName");
//		WebTarget userWebTargetChangeName = userWebTarChange.queryParam("newUserName", "LIYuanyuan");
//		Invocation.Builder invocationBuilderUpdateUser = userWebTargetChangeName.request(MediaType.TEXT_PLAIN);
//		invocationBuilderUpdateUser.header("some-header", "true");
//		Response responseUpdate = invocationBuilderUpdateUser.put(Entity.entity("create", MediaType.APPLICATION_JSON));
//		System.out.println(responseUpdate.getStatus());
//		System.out.println(responseUpdate.readEntity(String.class));

		/** Test: webService delete a user: @DELETE */
//		Invocation.Builder invocationBuilderDelete = userWebTarget.request(MediaType.TEXT_PLAIN);
//		invocationBuilderDelete.header("some-header", "true");
//		Response responseDelete = invocationBuilderDelete.delete();
//		System.out.println(responseDelete.getStatus());
//		System.out.println(responseDelete.readEntity(String.class));

		/** Test: webService get all the publication of user's own: @GET */
		WebTarget publicationWebTarget = webTarget.path("publications");
//		WebTarget publicationWebTargetAll = publicationWebTarget.path("ownerAllPublications");
//		Invocation.Builder invocationBuilderPublication = publicationWebTargetAll.request(MediaType.APPLICATION_JSON);
//		invocationBuilderPublication.header("some-header", "true");
//		Response responsePublication = invocationBuilderPublication.get();
//		System.out.println(responsePublication.getStatus());
//		System.out.println(responsePublication.readEntity(String.class));

		/**
		 * Test: webService get all the publication of other users: @GET . Description:
		 * if you do not follow other user,and other user is private, you can not see
		 * the publications of other user.
		 */
//		WebTarget publicationWebTargetAllOther = publicationWebTarget.path("othersAllPublications");
//		WebTarget publicationWebTargetAllOtherId = publicationWebTargetAllOther.queryParam("uId", 2);
//		Invocation.Builder invocationBuilderPublicationOthers = publicationWebTargetAllOtherId
//				.request(MediaType.APPLICATION_JSON);
//		invocationBuilderPublicationOthers.header("some-header", "true");
//		Response responsePublicationothers = invocationBuilderPublicationOthers.get();
//		System.out.println(responsePublicationothers.getStatus());
//		System.out.println(responsePublicationothers.readEntity(String.class));
		
		/** Test: webService create a user: @POST */
//		WebTarget publicationWebTarget = webTarget.path("publications");
//		WebTarget userWebTargetImage = publicationWebTarget.queryParam("imagePath", "pathLi");
//		WebTarget userWebTargetComment = userWebTargetImage.queryParam("comment", "comment Yuanyuan");
//		Invocation.Builder invocationBuilderPublier= userWebTargetComment.request(MediaType.APPLICATION_JSON);
//		invocationBuilderPublier.header("some-header", "true");
//		Response responsePost = invocationBuilderPublier.post(Entity.entity("pust publication", MediaType.APPLICATION_JSON));
//		System.out.println(responsePost.getStatus());
//		System.out.println(responsePost.readEntity(String.class));

		/**
		 * Test: webService response a publication: @POST. Description: if you do not
		 * follow other user,and other user is private, you can not response the
		 * publications of other user.
		 */
		WebTarget responseWebTarget = webTarget.path("responses");
		WebTarget responseWebTargetComment = responseWebTarget.queryParam("comment", "comment response");
		WebTarget responseWebTargetPId = responseWebTargetComment.queryParam("pId", 9);
		Invocation.Builder invocationBuilderResponse = responseWebTargetPId.request(MediaType.APPLICATION_JSON);
		invocationBuilderResponse.header("some-header", "true");
		Response responseResponse = invocationBuilderResponse
				.post(Entity.entity("response a publication", MediaType.APPLICATION_JSON));
		System.out.println(responseResponse.getStatus());
		System.out.println(responseResponse.readEntity(String.class));
	}

}
