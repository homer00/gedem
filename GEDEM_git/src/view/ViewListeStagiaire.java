package view;

import model.*;
import tools.ControlConnection;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.ControlStagiaire;

@SuppressWarnings("serial")
public class ViewListeStagiaire extends JPanel{


	private JTextArea zoneTexte;
	private JPanelImg fond;
	
	
	/**
	 * Constructeur __________________________ CONSTRUCTEUR
	 */
	public ViewListeStagiaire(App appli) {
	
		//this.app = appli;
		final Image monImage;
	
		try {
			
			monImage = ImageIO.read(new File("./img/texture_coton.png"));
			fond = new JPanelImg (monImage);
			fond.getImage();
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}

	JLabel etiket1 = new JLabel("Liste des Stagiaires", JLabel.CENTER);
	etiket1.setOpaque(true);
	etiket1.setBackground(Color.white);

	ControlStagiaire cs1 = new ControlStagiaire();
	String resultat = cs1.listerStagiaireStr();
	zoneTexte = new JTextArea(resultat);

	fond.add(zoneTexte, "Center");
	//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.setLayout(new GridLayout(2,1));
	fond.setBorder(new EmptyBorder(20,20,20,20));
	fond.add(etiket1);
	this.add(fond);

	
	
	
}
}
