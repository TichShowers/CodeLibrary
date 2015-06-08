package models;

import java.util.Date;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class Comment {
	private int id;
	private int fragment;
	private User user;
	private Date at;
	private String what;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFragment() {
		return fragment;
	}

	public void setFragment(int fragment) {
		this.fragment = fragment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getAt() {
		return at;
	}

	public void setAt(Date at) {
		this.at = at;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public Comment(int id, int fragment, User user, Date at, String what) {
		this.id = id;
		this.fragment = fragment;
		this.user = user;
		this.at = at;
		this.what = what;
	}
}
