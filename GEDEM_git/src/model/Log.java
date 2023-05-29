package model;

import java.sql.Date;

public class Log {

	private int idLog;
	private String login_log;
	private String password_log;
	private String date_log;
	private Date date_log2;
	
	public Log (String ll, String pl, String dl) {
		this.login_log=ll;
		this.password_log=pl;
		this.date_log=dl;
	}
	
	public Log (String ll, String pl, String dl, int il) {
		this.login_log=ll;
		this.password_log=pl;
		this.date_log=dl;
		this.idLog = il;
	}
	
	public Log (String ll, String pl, Date d, int il) {
		this.login_log=ll;
		this.password_log=pl;
		this.date_log2=d;
		this.idLog = il;
	}

	public int getIdLog() {
		return idLog;
	}

	public void setIdLog(int idLog) {
		this.idLog = idLog;
	}

	public String getLogin_log() {
		return login_log;
	}

	public void setLogin_log(String login_log) {
		this.login_log = login_log;
	}

	public String getPassword_log() {
		return password_log;
	}

	public void setPassword_log(String password_log) {
		this.password_log = password_log;
	}

	public String getDate_log() {
		return date_log;
	}

	public void setDate_log(String date_log) {
		this.date_log = date_log;
	}
	
	
}
