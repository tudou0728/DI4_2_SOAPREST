package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.Publication;
import model.Response;
import model.User;

/**
 * the class DAO to access the data of the table response.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class ResponseDAO {

	/**
	 * post a response
	 * 
	 * @param response
	 *            : response
	 * @return response
	 */
	public Response create(Response response) {
		try {
			if (get(response.getrId()) == null) {
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(response);
				transaction.commit();
			}
			return response;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get response by the id of a response
	 * 
	 * @param rId
	 *            : id of response
	 * @return response
	 */
	public Response get(int rId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(Response.class, rId);
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get all response
	 * 
	 * @return a list of response
	 */
	public List<Response> getAll() {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from Response response";
			Query query = session.createQuery(sql);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get all the response of a publication
	 * 
	 * @param pId
	 *            : id of publication
	 * @return a list of response
	 */
	public List<Response> getAllByPid(int pId) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "Select response from Response response where response.publication.pId = :pId";
			Query query = session.createQuery(sql);
			query.setParameter("pId", pId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}

	}

	/**
	 * delete a response
	 * 
	 * @param response
	 *            : response
	 */
	public void delete(Response response) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(response);
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
	 * update a response
	 * 
	 * @param response
	 *            : a response
	 * @param comment
	 *            : comment
	 * @param date
	 *            : the date of response
	 * @param user
	 *            : the user that posts this response
	 * @param publication
	 *            : publication
	 * @return response
	 */
	public Response update(Response response, String comment, Date date, User user, Publication publication) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			response.setComment(comment);
			response.setDate(date);
			response.setUser(user);
			response.setPublication(publication);
			session.update(publication);
			transaction.commit();
			return response;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * convert a response to a list of string with id, comment, user Id, date of
	 * response, publication ID, publication comment, user Id of publication.
	 * 
	 * @param response
	 *            : response
	 * @return a list of string
	 */
	public List<String> convertToString(Response response) {
		List<String> information = new ArrayList<>();
		information.add("response Id: " + response.getrId());
		information.add("response comment: " + response.getComment());
		information.add("response user Id: " + response.getUser().getuId());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(response.getDate());
		information.add("the date of response: " + date);
		information.add("publication Id: " + response.getPublication().getpId());
		information.add("publication comment: " + response.getPublication().getComment());
		information.add("publication user Id: " + response.getPublication().getUser().getuId());
		return information;

	}
}
