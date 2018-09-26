import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class Joueur extends JFrame{

	//private static final String SERVEUR = "localhost";
	//private static final int PORT = 2000;
	private Socket socket;
	private DataInputStream entre;
	private DataOutputStream sortie;
	private boolean connected = false;
	private String login = "";
	private boolean authenticated = false;	
	
	Joueur() {
		System.out.println("Demarrage joueur");
		login = "titi"; // à modifier, juste pour tester
	}
	
	DataInputStream getEntree() {
		return entre;
	}
	
	DataOutputStream getSortie() {
		return sortie;
	}

	String getLogin() {
		return login;
	}
	
//se connecte au serveur grace a l'ip et au port
	boolean connectAuServeur(String server, int port) {
		try {
			System.out.println("tentative de connection à "+ server + port);
			socket = new Socket(server, port);
			entre = new DataInputStream(socket.getInputStream());
			sortie = new DataOutputStream(socket.getOutputStream());
			int numJoueur = entre.readInt();
			connected = true;
			System.out.println("Joueur n: "+numJoueur);
			
//			entre.close();
//			sortie.close();
//			socket.close();
			
			return true;
		} catch (IOException e) {e.printStackTrace(); }
		return false;
	}
	
	
//s'indentifie grace a son pseudo et son mdp
	boolean connectAuJeu(String pseudo, String mdp) {
		boolean valide = false; 
		//envoyer login mdp au serveur
		try {
			sortie.writeInt(1); //1 correspond ï¿½ la connection
			sortie.writeUTF(pseudo);
			sortie.writeUTF(mdp);
			valide = entre.readBoolean(); // le serveur renvoie true ou false
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		if (valide) {
			login = pseudo;
			authenticated = true;
			return true; // afficher page jeu
		} else {
			return false;//afficher mauvais couple pseudo/mdp
		}		
	}
	
	boolean inscrire(String pseudo, String mdp) {
		boolean valide = false; 
		//envoie login mdp au serveur
		try {
			sortie.writeInt(2); //1 correspond a l'inscription
			sortie.writeUTF(pseudo);
			sortie.writeUTF(mdp);
			valide = entre.readBoolean(); // le serveur renvoie true ou false
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		if (valide) {
			login = pseudo;
			authenticated = true;
			return true; // afficher page jeu
		} else {
			return false;//afficher mauvais couple pseudo/mdp
		}		
	}

	
}

