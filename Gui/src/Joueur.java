import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class Joueur  extends JFrame{

	private static final String SERVEUR = "localhost";
	private static final int PORT = 2000;
	GuiTest gui;
	private DataInputStream entre;
	private DataOutputStream sortie;
	private Boolean connected = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		new Joueur();
	}
	
	Joueur() {
		gui = new GuiTest(this);
		setContentPane(gui);
		pack();
		setVisible(true);
		System.out.println("Demarrage client");
	
	}
	
	//se connect au serveur grace à l'ip et au port
	void connectAuServeur(String server, int port) {
		try {
			System.out.println("tentative de connection à "+ server + port);
			Socket socket = new Socket(SERVEUR, PORT);
			entre = new DataInputStream(socket.getInputStream());
			sortie = new DataOutputStream(socket.getOutputStream());
			int numJoueur = entre.readInt();
			System.out.println("Joueur n°: "+numJoueur);
			
//			entre.close();
//			sortie.close();
//			socket.close();
			
			
		} catch (IOException e) {e.printStackTrace(); }
	}
	
//	//s'indentifie grace à son pseudo et son mdp
//	void connectAuJeu(String pseudo, String mdp)
}
