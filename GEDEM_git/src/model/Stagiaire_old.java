package model;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * 
 * @author greta
 *
 */

public class Stagiaire_old  {
	
	//private int idStagiaire;
	private int idFormation;
	private Formation formation;
	//private ArrayList<Creneau>  listeCreneauxPresent;
	//private ArrayList<Creneau>  listeCreneauxAbsent;

	private String nomStagiaire;
	private String prenomStagiaire;
	private String telStagiaire;
	private String mailStagiaire;
	private String roleStagiaire;
	private ArrayList<Stagiaire_old> listeStagiaire;
	
	/**
	 * CONSTRUCTEUR 1
	 * 
	 * @param ids
	 * @param ns
	 * @param ps
	 * @param tels
	 * @param mls
	 */
	public Stagiaire_old(int ids, String ns, String ps, String tels, String mls, String rstag) {
		this.nomStagiaire = ns;
		this.prenomStagiaire = ps;
		this.telStagiaire = tels;
		this.mailStagiaire = mls;
		this.roleStagiaire = rstag;
		this.listeStagiaire = new ArrayList<Stagiaire_old>();
					
	}
	
		public Stagiaire_old(Formation form,int ids, String ns, String ps, String tels, String mls) {
			this.nomStagiaire = ns;
			this.prenomStagiaire = ps;
			this.telStagiaire = tels;
			this.mailStagiaire = mls;
						
		}
		
		
		public Stagiaire_old(Formation form,int ids, String ns, String ps, String mls) {
			
			this.nomStagiaire = ns;
			this.prenomStagiaire = ps;
			this.mailStagiaire = mls;
			
						
		}

		public int getIdFormation() {
			return idFormation;
		}

		public void setIdFormation(int idFormation) {
			this.idFormation = idFormation;
		}

		public String getNomStagiaire() {
			return nomStagiaire;
		}

		public void setNomStagiaire(String nomStagiaire) {
			this.nomStagiaire = nomStagiaire;
		}

		public String getPrenomStagiaire() {
			return prenomStagiaire;
		}

		public void setPrenomStagiaire(String prenomStagiaire) {
			this.prenomStagiaire = prenomStagiaire;
		}

		public String getTelStagiaire() {
			return telStagiaire;
		}

		public void setTelStagiaire(String telStagiaire) {
			this.telStagiaire = telStagiaire;
		}

		public String getMailStagiaire() {
			return mailStagiaire;
		}

		public void setMailStagiaire(String mailStagiaire) {
			this.mailStagiaire = mailStagiaire;
		}

		public ArrayList<Stagiaire_old> getListeStagiaire() {
			return listeStagiaire;
		}

		public void setListeStagiaire(ArrayList<Stagiaire_old> listeStagiaire) {
			this.listeStagiaire = listeStagiaire;
		}
		

		public String getRoleStagiaire() {
			return roleStagiaire;
		}

		protected void afficheStagiaire(int id) {
System.out.println("Vous avez entre : "+this.idFormation+", "+this.nomStagiaire+", "+this.prenomStagiaire+", ");
}
}
	
	
	
	
	

