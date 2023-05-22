package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ControlLogin;

@SuppressWarnings("serial")
public class ViewConnexion extends JPanel implements ActionListener,ItemListener { //

	JLabel labelTitre, label, label2, message1,message;
	JCheckBox montreMdp;
	private JLabel message2;
	private JLabel labelHiddenPass;
	private JTextField uLogin;
	private JPasswordField uPassword;
	private JButton bouton_val, reset_btn;
	private App appli;
	JPanel panel2;
	public boolean flag_checked;

	public ViewConnexion(App app_conn) {
	// En paramètre du constructeur de la vue : la vue de l'Application principale (JFrame)

		this.appli = app_conn;

		this.setLayout(null);

		// ==========================================

		setBackground(new Color(200,200,180));
		//setPreferredSize(new Dimension(100,200));
		//setLayout(new FlowLayout(FlowLayout.LEFT, 20, 30));

		labelTitre = new JLabel("GEDEM");
		labelTitre.setFont(new Font("Verdana", Font.PLAIN, 38));
		labelTitre.setForeground(new Color(100,100,250));

		label = new JLabel("Utilisateur : ");
		label.setBounds(180, 150, 140, 40);

		label2 = new JLabel("Mot de passe : ");
		label2.setBounds(180, 200, 140, 40);
		
		labelHiddenPass = new JLabel();
		labelHiddenPass.setBounds(610,  260,  100, 20);
		add(labelHiddenPass);
		
		message = new JLabel("");
		message.setFont(new Font("verdana,serif", Font.PLAIN,24));
		message.setBounds(300, 350, 300, 40);

		message1 = new JLabel("");
		message2 = new JLabel("CONNEXION REFUSEE");
		//border = new LineBorder(new Color (200,120,120));

		uLogin = new JTextField(20);
		uLogin.setBounds(300, 150, 300, 40);
		uLogin.addActionListener(this);

		uPassword = new JPasswordField(20);
		uPassword.setBounds(300, 200, 300, 40);
		uPassword.addActionListener(this);
		
		
		montreMdp = new JCheckBox("Montrer le mot de passe");
		montreMdp.setBounds(300, 250, 300, 40);
		montreMdp.addActionListener(this);
		montreMdp.addItemListener(this);
		

		bouton_val = new JButton("Valider");
		bouton_val.setBounds(300, 300, 100, 40);
		bouton_val.addActionListener(this);

		reset_btn = new JButton("Effacer");
		
		reset_btn.setBounds(500, 300, 100, 40);
		reset_btn.addActionListener(this);

		add(labelTitre);
		add(label);
		add(label2);
		add(message);
		add(uLogin);
		add(montreMdp);
		//panel2.add(label2);
		add(uPassword);
		add(bouton_val);
		add(reset_btn);
		add(message1);
		//add(message2);
		app_conn.pack();

	} // Fin constructeur

	// ======================================= ACCESSEURS

	public JTextField getULogin() {
		return uLogin;
	}


	public void setULogin(JTextField login) {
		this.uLogin = login;
	}


	public JPasswordField getUPassword() {
		return uPassword;
	}


	public void setUPassword(JPasswordField password) {
		this.uPassword = password;
	}


	public JLabel getMessage2() {
		return message2;
	}


	public void setMessage2(JLabel message2) {
		this.message2 = message2;
	}

// ============================================ GESTION DES ACTIONS

	@Override
	public void actionPerformed(ActionEvent evt) {

		if(evt.getSource()==bouton_val) {
			new ControlLogin(this,appli);
			/*
			 Lorsque le bouton est actionné, on fait appel au constructeur du controleur ControlLogin,
			 en lui passant en paramètres cette Vue ViewConnexion (grâce au mot-clé this), et la Frame actuelle
			 avec appli (instance de App en cours)

			*/

	}
		if (evt.getSource()==reset_btn) { // on efface les champs si bouton "Effacer"
			this.uLogin.setText("");
			this.uPassword.setText("");
			this.labelHiddenPass.setText("");
		}

		if(evt.getSource()==uPassword) {
			// si on appuie sur [ENTREE] dans le champ password du formulaire --> changement de couleur
			// ça sert à rien mais c'est tellement bien...

			label.setForeground(Color.RED);
			label.setFont(new Font("serif", Font.PLAIN, 12));
			setBackground(new Color(200,130,220));
			label.setText(getUPassword().toString());
			if (flag_checked) {
				System.out.println("bobol");
			}
			this.appli.getContentPane().add(getMessage2());

		}
		
}
	public void itemStateChanged(ItemEvent e) { //On intercepte l'évènement de changement de la checkbox qui affiche le Password en clair
		if (e.getSource()==montreMdp) { // la checkbox "montreMDP"
			if (e.getStateChange()==ItemEvent.SELECTED) { // si lors du changement d'état , position "checked"
				JTextField passJTF = this.getUPassword();
				String passwordClair = passJTF.getText(); // on récupère le champ password, avec un cast en String
				this.labelHiddenPass.setForeground(new Color (100,20,150));
				this.labelHiddenPass.setText(passwordClair);
				flag_checked = true;
								
				System.out.print("activation checkbox \t");
				System.out.println("Le password en clair : "+passwordClair);
				
				
			}
			else {
					flag_checked = false;
					//System.out.println("pas d'activation checkbox");
			}
			if (e.getStateChange()==ItemEvent.DESELECTED) { // si lors du changement d'état , position "unchecked"
				this.labelHiddenPass.setText("");
				//System.out.println("Désactivation checkbox");
			}
		}
	} // Fin ItemStateChanged
	
	/*
	public void keyTyped(KeyEvent e) {
		//System.out.println("Touche pressée");
		
	}
	
	public void keyPressed(KeyEvent e) {
		char strc = e.getKeyChar(); // on récupère le charactère de la touche
		
		if (flag_checked) {
			System.out.println("flag_checked");
			//labelHiddenPass.setText(String.valueOf(uPassword));
			//labelHiddenPass.setText(String.valueOf(strc));
			
		}
		else {
			System.out.println("flag_checked : faux");
		}
		//labelHiddenPass.setText(String.valueOf(strc));
		
		//System.out.println("Touche pressée : "+String.valueOf(strc));

	}
	
	public void keyReleased(KeyEvent e) {
		//System.out.println("Touche pressée");
		//this.
	}
	*/
	
	public void pbConnect() {
		JLabel refus = new JLabel("Pas bon.");
		this.add(refus);

	}

	
}
