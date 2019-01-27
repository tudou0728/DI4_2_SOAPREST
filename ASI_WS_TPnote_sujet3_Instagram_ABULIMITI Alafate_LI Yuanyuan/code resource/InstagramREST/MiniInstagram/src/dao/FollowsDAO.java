package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import model.Follows;
import model.Publication;

/**
 * the class DAO to access the data of the table Follows
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class FollowsDAO {
	/**
	 * get all information of 'Follows'
	 * 
	 * @return the list of 'Follows'
	 */
	public List<Follows> getAll() {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "from Follows follows";
			Query query = session.createQuery(sql);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * create a 'Follows'
	 * 
	 * @param follow
	 *            : the follow information
	 * @return 'Follows'
	 */
	public Follows create(Follows follow) {
		try {
			if (getFollow(follow.getFollow().getuId(), follow.getFollower().getuId()) == null
					|| getFollow(follow.getFollow().getuId(), follow.getFollower().getuId()).size() == 0) {
				Session session = HibernateUtil.currentSession();
				Transaction transaction = (Transaction) session.beginTransaction();
				session.save(follow);
				transaction.commit();
			}
			return follow;
		} finally {
			// TODO: handle finally clause
			HibernateUtil.closeSession();
		}
	}

	/**
	 * delete a 'Follows'
	 * 
	 * @param follow
	 *            : the 'Follows' information
	 */
	public void delete(Follows follow) {
		Session session = HibernateUtil.currentSession();
		try {
			Transaction transaction = (Transaction) session.beginTransaction();
			session.delete(follow);
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
	 * search all the followers of a user
	 * 
	 * @param uId
	 *            : Id of a user
	 * @return a list of 'Follows'
	 */
	public List<Follows> getFollowersOfUser(int uId) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "select follows from Follows follows where follows.follow.uId=:uId";
			Query query = session.createQuery(sql);
			query.setParameter("uId", uId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * search all the follows of a user
	 * 
	 * @param uId
	 *            : Id of a user
	 * @return a list of 'Follows'
	 */
	public List<Follows> getFollowsOfUser(int uId) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "select follows from Follows follows where follows.follower.uId=:uId";
			Query query = session.createQuery(sql);
			query.setParameter("uId", uId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get information of a 'Follows'
	 * 
	 * @param uFollowId
	 *            : id of follow
	 * @param uFollowerId
	 *            : id of follower
	 * @return Follows
	 */
	public List<Follows> getFollow(int uFollowId, int uFollowerId) {
		Session session = HibernateUtil.currentSession();
		try {
			String sql = "select follows from Follows follows where follows.follow.uId=:uFollowId and follows.follower.uId=:uFollowerId";
			Query query = session.createQuery(sql);
			query.setParameter("uFollowId", uFollowId);
			query.setParameter("uFollowerId", uFollowerId);
			return query.getResultList();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * get a 'Follows'
	 * 
	 * @param fId
	 *            : id of 'Follows'
	 * @return Follows
	 */
	public Publication getByFid(int fId) {
		Session session = HibernateUtil.currentSession();
		try {
			return session.find(Publication.class, fId);
		} finally {
			HibernateUtil.closeSession();
		}
	}

	/**
	 * convert a 'Follows' object to a list of string with id and name.
	 * 
	 * @param follows
	 *            : a 'Follows'
	 * @return a list of string
	 */
	public List<String> convertToString(Follows follows) {
		List<String> information = new ArrayList<String>();
		information.add("follow id: " + follows.getFollow().getuId());
		information.add("follow name: " + follows.getFollow().getUserName());
		information.add("follower id: " + follows.getFollower().getuId());
		information.add("follower name: " + follows.getFollower().getUserName());
		return information;
	}
}
