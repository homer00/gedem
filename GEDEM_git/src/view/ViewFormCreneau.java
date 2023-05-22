package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.ControlCreneau;

public class ViewFormCreneau extends JPanel implements ActionListener{
	
	private JLabel label_titre;
	private JLabel label_info;
	private JLabel label_date_deb;
	private JLabel label_date_fin;
	private JLabel label_cren;
	protected JLabel label_jtd,label_jtf;
	private JButton bt1;
	private JTextField jt_date_deb;
	private JTextField jt_date_fin;
	private ControlCreneau ccren;
	private String jtd;
	private String jtf;
	
	
	public ViewFormCreneau(App appli) {
		appli.setSize(new Dimension(800,600));
		JPanel conteneur = new JPanel();
		appli.add(conteneur);
		conteneur.setLayout(new BoxLayout(conteneur, BoxLayout.PAGE_AXIS));
		
		JPanel panel1 = new JPanel();
		conteneur.add(panel1);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		
		label_titre = new JLabel("Formation : génération de créneaux");
		Font font = new Font("Arial", Font.BOLD, 18);
		label_titre.setFont(font);
		label_info = new JLabel("Pour les dates, respecter le format \"29-08-2022\"");
		LineBorder thickBorder = new LineBorder(Color.BLUE, 12);
		label_info.setBorder(thickBorder);
		label_date_deb = new JLabel("Date de début");
		label_date_fin = new JLabel("Date de fin");
		label_cren = new JLabel("Créneau");
		label_jtd = new JLabel();
		label_jtf = new JLabel();
		
		jt_date_deb = new JTextField(8);
		jt_date_fin = new JTextField(8);
		
		//jtd = String.valueOf(jt_date_deb);
		jtd = jt_date_deb.getText();
		jtf = jt_date_fin.getText();
		
		bt1 = new JButton("Générer"); // Bouton + action
		bt1.addActionListener(this);
		
		panel1.add(label_titre);
		panel1.add(label_info);
		
		panel1.add(label_date_deb);
		panel1.add(jt_date_deb);
		panel1.add(label_date_fin);
		panel1.add(jt_date_fin);
		panel1.add(label_cren);
		panel1.add(bt1);
		panel1.add(label_jtd);
		panel1.add(label_jtf);
		
		
		
		add(conteneur);
		add(panel1);
		
		//appli.getContentPane().add(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==bt1 && jtd!="" && jtf!="") {
		
		try {
			//label_jtd.setText(jtd);
			//label_jtf.setText(jtf);
			//ccren = new ControlCreneau(jtd,jtf);
			ccren = new ControlCreneau("22-08-2022","15-11-2022");
			System.out.println("Génération des créneaux.");
		
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println("Erreur de formatage date de début, date de fin.");
			e1.printStackTrace();
		}
		}
		else System.out.println("passé try/catch");
	}

	public JTextField getJt_date_deb() {
		return jt_date_deb;
	}

	public void setJt_date_deb(JTextField jt_date_deb) {
		this.jt_date_deb = jt_date_deb;
	}

	public JTextField getJt_date_fin() {
		return jt_date_fin;
	}

	public void setJt_date_fin(JTextField jt_date_fin) {
		this.jt_date_fin = jt_date_fin;
	}

	public String getJtd() {
		return jtd;
	}

	public void setJtd(String jtd) {
		this.jtd = jtd;
	}

	public String getJtf() {
		return jtf;
	}

	public void setJtf(String jtf) {
		this.jtf = jtf;
	}
	
	

}
