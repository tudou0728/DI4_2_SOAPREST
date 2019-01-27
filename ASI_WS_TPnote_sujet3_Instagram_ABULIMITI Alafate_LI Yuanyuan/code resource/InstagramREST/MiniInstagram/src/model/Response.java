package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * the class of entity 'response' (response of a publication).
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Entity
public class Response implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * the id of a response
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rId;

	/**
	 * the comment of a response
	 */
	private String comment;
	/**
	 * the date of the response
	 */
	private Date date;

	/**
	 * owner of response
	 */
	@ManyToOne
	private User user;

	/**
	 * publication correspond to this response
	 */
	@ManyToOne
	private Publication publication;

	public Response() {

	}

	public Response(String comment, User user, Publication publication) {
		super();
		this.comment = comment;
		this.date = new Date();
		this.user = user;
		this.publication = publication;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

}
