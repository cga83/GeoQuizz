import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;

public class Joueur extends JFrame{

	//private static final String SERVEUR = "localhost";
	//private static final int PORT = 2000;
	private static Joueur  INSTANCE = null;
	private Socket socket;
	private DataInputStream entre;
	private DataOutputStream sortie;
	private boolean connected = false;
	private String login = "";
	private boolean authenticated = false;	
	
	private Joueur() {
		System.out.println("Demarrage joueur");
		login = "titi"; // à modifier, juste pour tester
	}
	public static Joueur getJoueur() {
		if(INSTANCE == null) {
			INSTANCE = new Joueur();
		}
		return INSTANCE;
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
			sortie.writeInt(action.connexion.ordinal());
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
			sortie.writeInt(action.inscription.ordinal()); 
			sortie.writeUTF(pseudo);
			sortie.writeUTF(mdp);
			valide = entre.readBoolean(); // le serveur renvoie true ou false
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return valide;		
	}
	
	String[] getScorePerso() {
		String[] scores = new String[10];
		try {
			sortie.writeInt(action.scorePerso.ordinal());
			sortie.writeUTF(login);
			for (int i = 0; i < 10; i ++) {
				scores[i] = entre.readUTF();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return scores;
	}
	
	String[] getQuestion() {
		String questionreponses[] = new String[5];
		try {
			sortie.writeInt(action.question.ordinal());
			for(int i = 0; i<5; i++) {
				questionreponses[i] = entre.readUTF();
			}
		}  catch (IOException e) {
			e.printStackTrace();
		} 
		return questionreponses;
	}

	String[][] getMeilleursScoresPartie(){
		String[][] scores = new String[10][2];
		try {
			sortie.writeInt(action.scorePartie.ordinal());
			for (int i = 0; i < 10; i ++) {
				scores[i][0] = entre.readUTF();
				scores[i][1] = entre.readUTF();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return scores;
	}
	
	String[][] getMeilleursScoresGeneral(){
		String[][] scores = new String[10][2];
		try {
			sortie.writeInt(action.scoreGeneral.ordinal());
			for (int i = 0; i < 10; i ++) {
				scores[i][0] = entre.readUTF();
				scores[i][1] = entre.readUTF();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return scores;
	}
	
	
	
	void deconexion() {
		try {
			sortie.writeInt(action.deconnexion.ordinal());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	void giveScoreToserver(int score) {
		try {
			sortie.writeInt(action.nouveauScore.ordinal());
			sortie.writeUTF(login);
			sortie.writeInt(score);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}

