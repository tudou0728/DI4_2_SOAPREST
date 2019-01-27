package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.Publication;
import model.Response;
import model.User;

/**
 * the class DAO to access the data of the table publication.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class PublicationDAO {

	/**
	 * post a 'Publication'.
	 * 
	 * @param publication
	 *            : a 'Publication'
	 * @return publication : a 'Publication'
	 */
	public Publication create(Publication publication) {
		try {
			if (get(publication.getpId()) == null) {
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(publication);
				transaction.commit();
			}
			return publication;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get 'Publication' by id.
	 * 
	 * @param pId
	 *            : id of a 'Publication'
	 * @return publication : a 'Publication'
	 */
	public Publication get(int pId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(Publication.class, pId);
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get all the publication of the user.
	 * 
	 * @param user
	 *            : a user
	 * @return a list of 'Publication'
	 */
	public List<Publication> getAll(User user) {
		Session session = HibernateUtil.currentSession();
		try {
			int user_uId = user.getuId();
			String sql = "Select publication from Publication publication where publication.user.id = :id ";
			Query query = session.createQuery(sql);
			query.setParameter("id", user_uId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * delete a 'Publication'
	 * 
	 * @param publication
	 *            : a 'Publication'
	 */
	public void delete(Publication publication) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(publication);
			transaction.commit();
			HibernateUtil.closeSession();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * update a publication
	 * 
	 * @param publication
	 *            : publication
	 * @param imagePath
	 *            : the path of image
	 * @param comment
	 *            : the comment of a publication
	 * @param responses
	 *            : responses of a publication
	 * @return publication
	 */
	public Publication update(Publication publication, String imagePath, String comment, List<Response> responses) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			publication.setImagePath(imagePath);
			publication.setComment(comment);
			publication.setResponses(responses);
			session.update(publication);
			transaction.commit();
			return publication;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * convert a publication to a two-dimensional list of the string with id,
	 * comment, path of image, user id and date of publication.
	 * 
	 * @param publication
	 *            : a publication
	 * @return
	 */
	public List<List<String>> convertToString(Publication publication) {
		List<List<String>> publications = new ArrayList<List<String>>();
		List<String> information = new ArrayList<>();
		information.add("publication id: " + publication.getpId());
		information.add("publication comment: " + publication.getComment());
		information.add("publication imagePath: " + publication.getImagePath());
		information.add("publication user id: " + publication.getUser().getuId());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(publication.getDate());
		information.add("the day of publication: " + date);
		publications.add(information);
		if (publication.getResponses() != null) {
			List<Response> responses = publication.getResponses();
			for (int i = 0; i < responses.size(); i++) {
				List<String> response = new ArrayList<>();
				response.add("response: " + (i + 1));
				response.add("response id: " + responses.get(i).getrId());
				response.add("response comment: " + responses.get(i).getComment());
				response.add("response user id: " + responses.get(i).getUser().getuId());
				String dateResponse = simpleDateFormat.format(responses.get(i).getDate());
				response.add("response date: " + dateResponse);
				publications.add(response);
			}
		}
		return publications;
	}
}
