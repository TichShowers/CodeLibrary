package models;

import mvc.security.PasswordHasher;

/**
 * 
 * @author Colin Bundervoet
 *
 */
public class User {
	private int id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String avatar;
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	private boolean isAdmin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void changePassword(String plaintext)
	{
		this.password = PasswordHasher.hashWithMD5(plaintext);
	}
	
	public boolean checkPassword(String plaintext)
	{
		return password.equals(PasswordHasher.hashWithMD5(plaintext));
	}

	public User(int id, String name, String username, String email, String avatar, boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.avatar = avatar;
		this.isAdmin = isAdmin;
	}

	
	

}
