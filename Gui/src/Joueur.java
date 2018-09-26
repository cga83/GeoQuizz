import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class Joueur  extends JFrame{

	//private static final String SERVEUR = "localhost";
	//private static final int PORT = 2000;
	
	private DataInputStream entre;
	private DataOutputStream sortie;
	private Boolean connected = false;
	private String login = "";
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub	
//		 Application.launch(Main.class, args);
//		//new Joueur(args);
//		
//	}
	
	Joueur() {
//		gui = new GuiTest(this);
//		setContentPane(gui);
//		pack();
//		setVisible(true);
		System.out.println("Demarrage client");
	
	}
	
	//se connect au serveur grace a l'ip et au port
	boolean connectAuServeur(String server, int port) {
		try {
			System.out.println("tentative de connection � "+ server + port);
			Socket socket = new Socket(server, port);
			entre = new DataInputStream(socket.getInputStream());
			sortie = new DataOutputStream(socket.getOutputStream());
			int numJoueur = entre.readInt();
			System.out.println("Joueur n: "+numJoueur);
			
//			entre.close();
//			sortie.close();
//			socket.close();
			
			return true;
		} catch (IOException e) {e.printStackTrace(); }
		return false;
	}
	
	
//	//s'indentifie grace a son pseudo et son mdp
	void connectAuJeu(String pseudo, String mdp) {
		boolean valide = false; 
		//envoyer login mdp au serveur
		try {
			sortie.writeInt(1); //1 correspond � la connection
			sortie.writeUTF(pseudo);
			sortie.writeUTF(mdp);
			valide = entre.readBoolean(); // le serveur renvoie true ou false
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if (valide) {
			// afficher page jeu
		} else {
			//afficher mauvais couple pseudo/mdp
		}
	
		
		
		
	}
}
