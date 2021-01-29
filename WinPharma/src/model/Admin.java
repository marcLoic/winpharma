package model;

public class Admin {

	public int adminID;
	public String username;
	public String pwd;
	
	public Admin(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
