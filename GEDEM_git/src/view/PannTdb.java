package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PannTdb extends JFrame  {
	//private App appli;
	
	public  PannTdb(App appli)  {
		//this.appli = appli;
	      JPanel panel10 = new JPanel();
	      
	      panel10.setBackground(Color.lightGray);
	      panel10.setPreferredSize(new Dimension(1010, 520));
	      JLabel labTdb = new JLabel("TABLEAU DE BORD",JLabel.CENTER);
	      labTdb.setFont(new Font("Serif", Font.BOLD, 50));
	      panel10.add(labTdb);
	      panel10.setSize(350,350);  
	      appli.getContentPane().add(panel10);
	     // return panel10;
	}
}