package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * the class of entity 'user' .
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Entity
@XmlRootElement
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * the id of the user
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uId;

	/**
	 * the name of the user
	 */
	private String userName;
	/**
	 * the password of the user
	 */
	private String password;
	/**
	 * whether the user is a private user.
	 */
	private boolean privacy;

	/**
	 * all follows
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "follower")
	private List<Follows> follows;

	/**
	 * all followers
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "follow")
	private List<Follows> followers;

	/**
	 * all publications
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Publication> publications;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public List<Follows> getFollows() {
		return follows;
	}

	public void setFollows(List<Follows> follows) {
		this.follows = follows;
	}

	public List<Follows> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follows> followers) {
		this.followers = followers;
	}

	public List<Publication> getPublications() {
		return publications;
	}

	public void setPublications(List<Publication> publications) {
		this.publications = publications;
	}

	public User() {
	}

	public User(String userName, String password, boolean privacy) {
		this.userName = userName;
		this.password = password;
		this.privacy = privacy;
	}
	
	public User clone(User user) {
		this.uId=user.getuId();
		this.password=user.getPassword();
		this.userName=user.getUserName();
		this.privacy=user.isPrivacy();
		this.follows=new ArrayList<Follows>();
		for(int i=0;i<user.getFollows().size();i++) {
			this.follows.add(user.getFollows().get(i));
		}
		this.followers=new ArrayList<Follows>();
		for(int i=0;i<user.getFollowers().size();i++) {
			this.followers.add(user.getFollowers().get(i));
		}
		this.publications=new ArrayList<Publication>();
		for(int i=0;i<user.getPublications().size();i++) {
			this.publications.add(user.getPublications().get(i));
		}
		return this;
	}
}
