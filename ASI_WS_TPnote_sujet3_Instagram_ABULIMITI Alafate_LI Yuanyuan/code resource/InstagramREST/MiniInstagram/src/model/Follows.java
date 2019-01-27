package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * the class of entity 'Follows'.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
@Entity
public class Follows implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * the id of 'Follows'
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fId;

	/**
	 * follow : user 'follower' follows user 'follow'
	 */
	@ManyToOne
	private User follow;

	/**
	 * follower : user 'follower' follows user 'follow'
	 */
	@ManyToOne
	private User follower;

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public User getFollow() {
		return follow;
	}

	public void setFollow(User follow) {
		this.follow = follow;
	}

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public Follows(User follow, User follower) {
		this.follow = follow;
		this.follower = follower;
	}

	public Follows() {
	}

}
