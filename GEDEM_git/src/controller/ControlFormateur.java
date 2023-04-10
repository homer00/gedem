package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

//import com.mysql.cj.jdbc.result.ResultSetMetaData;

import model.Formation;
import tools.ControlConnection;
import view.App;
//import view.ViewFormateur;
import view.ViewListeFormation;


public class ControlFormateur {
	private ArrayList<Formation> listFormation;
	private ArrayList<String> enteteFormation;
	private String requete;
	private String resultFormStr;
	private Vector<String> leVector;
	private Vector<Vector> leVector2;
	private String leVectorStr;
	public ArrayList<String> monArrayList;

	
	
	public ControlFormateur(App appli,ViewListeFormation vf) {
		
	
		listFormation = new ArrayList<Formation>();
		enteteFormation = new ArrayList<String>();
		leVector = new Vector<String>();
		leVector2 = new Vector<Vector>();
		
		
	}
	// Méthode pour afficher la Table des formations dans la console
	
	public void showTableFormation() {
		ControlConnection cc = new ControlConnection("gedem","root","root");
		
		requete = "SELECT * FROM formation";
		try {
			ResultSet rs = cc.getStatement().executeQuery(requete);
			
			while (rs.next()) {
					listFormation.add(new Formation(rs.getInt(1),	rs.getString(2), rs.getDate(3),rs.getDate(4)));
			}
			for (int i=0; i<listFormation.size(); ++i) {
				resultFormStr += listFormation.get(i).getIdFormation()+"\t"+
						listFormation.get(i).getIntituleFormation()+"\t"+
						listFormation.get(i).getDebutFormation()+"\t"+
						listFormation.get(i).getFinFormation()+"\t";
						//+listFormation.get(i).getCommentFormation()+"\n";
			}
			System.out.println(resultFormStr);
			cc.fermerConn();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ControlFormateur : Erreur dans le traitement executeQuery, ControlFormateur");
			e.printStackTrace();
		}
		}
	
	// méthode pour afficher la liste des Formations, dans le JPanel ==============================
	
		public Vector<Vector> showTableFormationJT() {
			ControlConnection cc = new ControlConnection("gedem","root","root");
			requete = "SELECT * from formation";
			try {
				ResultSet rs = cc.getStatement().executeQuery(requete);
				ResultSetMetaData rsmd = rs.getMetaData();
				int cpt = 1;
				while (cpt < rsmd.getColumnCount()) {
					enteteFormation.add(rsmd.getColumnName(cpt));
					//enteteFormation : ArrayList qui récupère le nom des colonnes de la table
					cpt++;
					
				}
				System.out.println("Entete  "+ enteteFormation);
				while (rs.next()) {
					monArrayList = new ArrayList<String>();
					monArrayList.add(Integer.toString(rs.getInt(1)));
					monArrayList.add(rs.getString(2));
					monArrayList.add(rs.getString(3));
					monArrayList.add(rs.getString(4));
					//monArrayList.add(rs.getString(5));
					this.leVector2.addElement(new Vector<String>(monArrayList));
				
				}

				
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ControlFormateur : Erreur dans le traitement executeQuery, ControlFormateur");
				e.printStackTrace();
			}
			cc.fermerConn();
			
			return leVector2;
		
	}
		public Vector<String> getLeVector() {
			return leVector;
		}
		public void setLeVector(Vector<String> leVector) {
			this.leVector = leVector;
		}
		public String getLeVectorStr() {
			return leVectorStr;
		}
		public void setLeVectorStr(String leVectorStr) {
			this.leVectorStr = leVectorStr;
		}
	
		public Vector<Vector> getLeVector2() {
			return leVector2;
		}
		public void setLeVector2(Vector<Vector> leVector2) {
			this.leVector2 = leVector2;
		}
		public ArrayList<String> getEnteteFormation() {
			return enteteFormation;
		}
		public void setEnteteFormation(ArrayList<String> enteteFormation) {
			this.enteteFormation = enteteFormation;
		}
		
}
