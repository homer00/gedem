package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controller.ControlLog;
import controller.ControlStagiaire;

public class ViewLog extends JPanel implements ActionListener{


	private ControlLog cl;
	String res;
	private JLabel labelDate;
	private JLabel labelf1;
	private JTable tableLog;
	private JScrollPane scrollpane;

	public ViewLog  (App appli) {
		
		setBorder(BorderFactory.createSoftBevelBorder(1, Color.yellow,Color.red,Color.blue,Color.green));

		this.setLayout(new GridLayout(2,2,10,30));

		labelf1 = new JLabel("Liste des Connexions en échec - vue Administrateur");
		this.add(labelf1);
		/*
		JLabel labeld1 = new JLabel(""); // pour affichage Date
		setLabelDate(labeld1); // méthode setLabelDate de la partie "Accesseurs"
		this.add(labeld1);
		*/
		cl = new ControlLog(appli,this);

		Vector<Vector> leVector = cl.showTableLog(); // leVector : contient l'ensemble des lignes du tableau de Stagiaires
		//showTableFormation est une méthode de la classe ControlStagiaire, qui retourne un objet de type Vector
		Vector<String> v = new Vector<>(cl.getEnteteLog()); // v : contient la liste des entêtes du tableau (rsMetaData)
		for (String element : cl.getEnteteLog()) {
			System.out.print(element+" - ");
		}
		System.out.println("\n[ViewListeLog]leVector :"+leVector);
		tableLog = new JTable(leVector,v);
		this.scrollpane = new JScrollPane(tableLog);
		scrollpane.setPreferredSize(new Dimension(200,200));
		add(scrollpane,BorderLayout.CENTER);
		
		//appli.setSize(getMaximumSize()); // pour maximiser l'affichage
		
	}

// ========================================= ACCESSEURS
	public JLabel getLabelDate() {
		return labelDate;
	}

	public void setLabelDate(JLabel labelDate) {
		DateFormat monFormat = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss");
		Date aujourdhui = Calendar.getInstance().getTime();
		String resultat = monFormat.format(aujourdhui);
		System.out.println("resultat : "+resultat);
		//this.labelDate.setText(resultat);
	}
// ================================================= ACTIONPERFORMED
	@Override
	public void actionPerformed(ActionEvent evt) {
		//if (evt.getSource()==btnf1) {
		if (evt.getSource()==this.getActionForKeyStroke(null)) {
			res = Integer.toString(evt.getID());
			System.out.println(res);


			this.setLabelDate(labelDate);

		}
	}

	
}
