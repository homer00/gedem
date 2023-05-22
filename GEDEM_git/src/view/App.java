package view;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class App extends JFrame{

	private static App appli; // 09/05 : passage de appli en static
	private static Dimension dim;
	//private String textFrame;
	//private static ControlerConnection cc;
	public App(String str) { // ============================= CONSTRUCTEUR
		super(str);
	}
	/*
	public App(String str) { // ============================= CONSTRUCTEUR
		super(str); // str : Chaine qui représente le titre de la fenêtre
		dim = new Dimension(800,480);
		//dim = new Dimension(this.getSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(dim);
		//setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
	}
	*/

	public static void main(String[] args) { // ================ MAIN
		appli = new App("Application principale - GEDEM_git");
		//appli = new App("Application principale - GEDEM_git");
		dim = new Dimension(800,480);
		appli.setDefaultCloseOperation(EXIT_ON_CLOSE);
		appli.setPreferredSize(dim);
		appli.setLocationRelativeTo(null);
		appli.setResizable(true);
		appli.setVisible(true);

ViewConnexion cn = new ViewConnexion(appli);
		// appli en paramètre de l'instance de ViewConnexion (vue)
//Dialogue d = new Dialogue();
//appli.getContentPane().add(d);

		appli.getContentPane().add(cn);
		appli.pack();
		appli.centre(); // centrage de la JFrame, après le pack()
	}
	
	public void centre() {

        Dimension windowsSize = this.getSize();
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int x = (screenSize.width - windowsSize.width) / 2;
        int y = (screenSize.height- windowsSize.height) / 2;
        this.setLocation(x, y);
    }
}
