package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import model.Log;
import model.Stagiaire;
import tools.ControlConnection;
import view.App;

import view.ViewLog;

public class ControlLog {

	public String requete;
	protected ArrayList<String> enteteLog;
	protected Vector<Vector> leVector2;
	protected Vector<String> leVector;
	protected ArrayList<String> monArrayList;
	protected ArrayList<Log> listLog;
	
	public ControlLog (App appli,ViewLog vl) {
		
		listLog = new ArrayList<Log>();
		enteteLog = new ArrayList<String>();
		leVector = new Vector<>();
		leVector2 = new Vector<>();
		
	}
	
public Vector<Vector> showTableLog() { // méthode pour lister l'ensemble des stagiaires
		
		ControlConnection cc = new ControlConnection();
		
		requete = "SELECT * FROM log ;";
		
		try {
			ResultSet rs = cc.getStatement().executeQuery(requete);
			ResultSetMetaData rsmd = rs.getMetaData();
			int cpt = 1;
			while (cpt <= rsmd.getColumnCount()) {
				enteteLog.add(rsmd.getColumnName(cpt));
				//enteteFormation : ArrayList qui récupère le nom des colonnes de la table
				cpt++;
			}
			System.out.println("Entete  "+ enteteLog);
			while (rs.next()) {
				//this.listStagiaire.add(new Stagiaire(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
				//this.leVector2.addElement(new Vector<>(listStagiaire));
				
				monArrayList = new ArrayList<>();
				
				monArrayList.add(rs.getString(1));
				monArrayList.add(rs.getString(2));
				monArrayList.add(rs.getString(3));
				monArrayList.add(Integer.toString(rs.getInt(4))); // on place dans monArrayList l'ensemble des colonnes du ResultSet 
				
				this.leVector2.addElement(new Vector<>(monArrayList)); // On place la collection monArrayList dans un tableau de type Vector
	
}
		
}
catch (SQLException e) {
	// TODO Auto-generated catch block
	System.out.println("ControlLog : Erreur dans le traitement executeQuery, ControlLog");
	e.printStackTrace();
}
cc.fermerConn();

return leVector2; // RETOUR DE LA METHODE !!!
}
public ArrayList<String> getEnteteLog() {
	return enteteLog;
}

public void setEnteteFormation(ArrayList<String> enteteFormation) {
	this.enteteLog = enteteFormation;
}

public Vector<Vector> getLeVector2() {
	return leVector2;
}

public void setLeVector2(Vector<Vector> leVector2) {
	this.leVector2 = leVector2;
}

public ArrayList<String> getMonArrayList() {
	return monArrayList;
}

public void setMonArrayList(ArrayList<String> monArrayList) {
	this.monArrayList = monArrayList;
}

}