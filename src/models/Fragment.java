package models;

import java.util.Date;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class Fragment {

	private int id;
	private String title;
	private String code;
	private int language;
	private Date at;
	private int numcomments;

	public Fragment(int id, String title, String code,  Date at,
			int language, int numcomments) {
		this.id = id;
		this.title = title;
		this.code = code;
		this.language = language;
		this.at = at;
		this.numcomments = numcomments;
	}

	public int getNumcomments() {
		return numcomments;
	}

	public void setNumcomments(int numcomments) {
		this.numcomments = numcomments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getLanguage() {
		return language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public Date getAt() {
		return at;
	}

	public void setAt(Date at) {
		this.at = at;
	}

	public Fragment(int id, String title, String code, Date at, int language) {
		this(id, title, code, at, language, 0);
	}
}
