package view;
import java.awt.*;
import javax.swing.*;

import controller.ControlLogin;
import tools.ControlConnection;


public class App extends JFrame{

	private App app;
	private Dimension dim;
	//private String textFrame;
	//private static ControlerConnection cc;
	
	public App(String str) { // ============================= CONSTRUCTEUR
		super(str);
		dim = new Dimension(800,480);
		//dim = new Dimension(this.getSize());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(dim);
		//setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		
		this.setVisible(true);
	}

	public static void main(String[] args) { // ================ MAIN

		
		App appli = new App("Application principale - GEDEM_git");
		
		ViewConnexion cn = new ViewConnexion(appli);
		// appli en paramètre de l'instance de ViewConnexion (vue)
		
		appli.getContentPane().add(cn);
		
		appli.pack();
		appli.centre(); // centrage de la JFrame, après le pack()
		
		
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}
	

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	public Dimension getDim() {
		return dim;
	}

/*
	public void setDim(Dimension dim) {
		this.dim = dim;
	}
	*/
	public void centre() {
		 
        Dimension windowsSize = this.getSize( );
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int x = (screenSize.width - windowsSize.width) / 2;
        int y = (screenSize.height- windowsSize.height)/ 2;
        this.setLocation(x, y);
 
    }
}
