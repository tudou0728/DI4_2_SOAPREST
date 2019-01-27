package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * the class of entity 'publication'.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Entity
@XmlRootElement
public class Publication implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * the id of a publication
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;

	/**
	 * the path of the image.
	 */
	private String imagePath;
	/**
	 * the comment of a publication.
	 */
	private String comment;
	/**
	 * the date that user posts the publication.
	 */
	private Date date;

	/**
	 * owner of this publication
	 */
	@ManyToOne
	private User user;

	/**
	 * response of this publication
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "publication")
	private List<Response> responses;

	
	public Publication() {
		
	}
	
	public Publication(String imagePath, String comment, User user) {
		super();
		this.imagePath = imagePath;
		this.comment = comment;
		this.date = new Date();
		this.user = user;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
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

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}

}
