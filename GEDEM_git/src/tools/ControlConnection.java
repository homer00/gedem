package tools;
import java.sql.*;
import java.util.ArrayList;
//import java.util.ListIterator;
import java.util.Scanner;
import model.*;

public class ControlConnection {


	private String dbName="gedem" ;
	//private String sql_host = "localhost";
	private String sql_host = "192.168.1.121";
	private String dao_strClassName = "com.mysql.cj.jdbc.Driver";
	private String loginDb = "root";
	private String passwordDb = "root";
	private String strUrl = "jdbc:mysql://"+sql_host+":3306/" +  dbName+"?useSSL=false&serverTimezone=UTC";
	private Connection conn;
	private Statement st;
	
	
	
	/**
	 *  EL CONSTRUCTOR...
	 * @param dao_db
	 * @param dao_login
	 * @param dao_password
	 */
	public ControlConnection() {
				
		try {
			Class.forName(dao_strClassName);
			sop("\nConnexion effectuee.");
			System.out.println("ControlConnection : Constructeur sans parametres");
			System.out.println("Adresse de connexion : "+ strUrl+"\n\n");
			this.conn = DriverManager.getConnection(strUrl, loginDb, passwordDb);
			 this.st = conn.createStatement();
				
		}
		catch (SQLException e) {
			System.out.println("--Erreur au premier \"try\".  Probleme de Connection ou Statement - enfin j'imagine - je ne suis qu'une machine... Ne me jugez pas !...");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Classe introuvable : erreur de driver.");
			e.printStackTrace();
		}
	}
	
	/**
	 *  CONSTRUCTEUR SURCHARGE
	 *  Ce constructeur prend 3 paramètres : le nom de la base, le login et le password.
	 * @param dao_db
	 * @param dao_login
	 * @param dao_password
	 */
	public ControlConnection(String dao_db, String dao_login, String dao_password) {
		this.dbName = dao_db;
		this.loginDb = dao_login;
		this.passwordDb = dao_password;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			sop("Connexion effectuee.");
			System.out.println("ControlConnection : Constructeur 2 avec parametres");
			System.out.println("Adresse de connexion : "+ strUrl+" ");
			this.conn = DriverManager.getConnection(strUrl, loginDb, passwordDb);
			this.st = conn.createStatement();

					}
		catch (SQLException e) {
			System.out.println("Erreur au premier \"try\".  Probleme de Connection ou Statement -> Constructeur 2");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Classe introuvable : erreur de driver.");
			e.printStackTrace();
		}
	
		} // FIN CONSTRUCTEUR

	
	// ---------------------------------------getters , Setters -------------------
	
	
	public String getDao_strClassName() {
		return dao_strClassName;
	}

	public void setDao_strClassName(String dao_strClassName) {
		this.dao_strClassName = dao_strClassName;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Statement getStatement() {
		return st;
	}

	public void setSt(Statement st) {
		this.st = st;
	}

	// =========================================== Alias S.O.P
	public void sop(String texte) {
		System.out.println(texte);
	}
	// =========================================== fermerConn()	
	/**
	 *  Fonction fermerConn()
	 *  @param
	 *  {@summary}
	 */
public void fermerConn() {
	try {
		conn.close();
		st.close();
	}
	catch (SQLException efc){
		System.out.println("Erreur de fermeture de connexion / Statement (Driver jdbc)");
		efc.printStackTrace();
		}
}



}