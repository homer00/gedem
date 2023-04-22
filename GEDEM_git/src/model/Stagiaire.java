package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import com.mysql.cj.xdevapi.Statement;

import tools.ControlConnection;

public class Stagiaire {
	// Mysql : h_personne_stagiaire
	private int idStagiaire;
	private int idPersonne;
	ControlConnection cc;
	
	
	
	public Stagiaire(int idStagiaire, int idPersonne) {
		this.idStagiaire = idStagiaire;
		this.idPersonne = idPersonne;
		
	}
	
	public void afficheStagiaires() {
		
		// requete SQL pour sélectionner l'ensemble des stagiaires
		String reqAS = "SELECT p.nom,p.prenom,s.idStagiaire FROM Personne p JOIN h_personne_stagiaire s USING (idPersonne) WHERE idRole='3'";
		// Dans la table role, idRole=3 correspond à "User", qui est le rôle correspondant aux stagiaires.
		
		try {
			cc = new ControlConnection();
			ResultSet rs = cc.getStatement().executeQuery(reqAS);
			while (rs.next()) {
				System.out.println("Nom : "+rs.getString(1)+"\tPrenom : "+rs.getString(2)+"\tid Stagiaire : "+rs.getInt(3));
			}
		}
		catch (SQLException e){
			System.out.println("Erreur SQL - Classe Stagiaire");
			e.printStackTrace();
			 
		}

		/*
		Vector vec1 = new Vector();
		Collections.addAll(vec1,aList);
		String affStag="";
		
		
		for (int i=0; i<vec1.size();i++) {
			affStag += vec1.get(i);
		}
		
		return affStag;
		//Connection

	*/
		
	}

	//=========================================  ACCESSEURS
	int getIdStagiaire() {
		return this.idStagiaire;
	}
	int getIdPersonne() {
		return this.idPersonne;
	}
	void setIdStagiaire(int ids) {
		this.idStagiaire = ids;
	}
	void setIdPersonne(int idp) {
		this.idPersonne = idp;
	}
	
}
