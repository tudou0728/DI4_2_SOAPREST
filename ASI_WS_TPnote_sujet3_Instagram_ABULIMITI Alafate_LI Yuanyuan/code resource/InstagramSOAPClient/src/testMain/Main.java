package testMain;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/** test create a publication */
//		try {
//			PublicationWSProxy publicationWSProxy = new PublicationWSProxy();
//			String publication = publicationWSProxy.createPub("Li", "Yuanuan", "image path", "instagram");
//			System.out.println(publication);
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		/** test look the publication of users */
//		try {
//			PublicationWSProxy publicationWSProxy = new PublicationWSProxy();
//			String[] publications = publicationWSProxy.getOwnAllPubs("Li", "Yuanyuan");
//			for (String publication : publications) {
//				System.out.println(publication);
//			}
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		/**
		 * test look the publication of other user. if you do not follow other user, and
		 * other user is private, you can not see the publications of other user.
		 */
//		try {
//			PublicationWSProxy publicationWSProxy = new PublicationWSProxy();
//			String[] publications = publicationWSProxy.getOtherAllPubs("Li", "Yuanyuan", 13);
//			if(publications!=null)
//			{
//				for (String publication : publications) {
//					System.out.println(publication);
//				}
//			}
//			else {
//				System.out.println("no publications.");
//			}
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		/**
		 * test response a publication. if you do not follow other user, and other user
		 * is private, you can not response the publications of other user.
		 */
//		try {
//			ResponseWSProxy responseWSProxy = new ResponseWSProxy();
//			String[] responses = responseWSProxy.createRes("daqing", "daqing", "comment 20180520", 5);
//			for (String response : responses) {
//				System.out.println(response);
//			}
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			}
	}

}
