package models;

import java.util.Date;

public class Fragment {

	private int id;
	private String title;
	private String code;
	private int language;
	private Date at;

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
		super();
		this.id = id;
		this.title = title;
		this.code = code;
		this.at = at;
		this.language = language;
	}
}
