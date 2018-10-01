import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Serveur {

	private static ServerSocket GESTSOCKET;
	private static final int NBMAXCONN = 4;
	private static  int nbConn = 0;
	private static boolean connected = true;
	private static FonctionsUtilisateur csv = new FonctionsUtilisateur();
	private static ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Serveur();
	}

	Serveur() {
		System.out.println("Demarrage serveur");
		
		//Recupère tous les utilisateurs et mots de passe via fichier csv
		csv.ChargerTableauUsers(utilisateurs);
		
		// attente du client
		try {
			GESTSOCKET = new ServerSocket(2000);
			waitJoueur();
			
			//GESTSOCKET.close();
		} catch (IOException e) {e.printStackTrace( );} 
	}

	void waitJoueur() {
		System.out.println("attente client");

		try {
			if (nbConn < NBMAXCONN) {
				Socket socket = GESTSOCKET.accept();
				System.out.println("client connectï¿½");
				
				//relance nvelle attente 
				//connected = true;
				Thread th = new Thread(() ->  {
					waitJoueur();
				});
				th.start();
				nbConn ++;
				int numJoueur = nbConn; // TODO transformer en nom du joueur
				//communication avec le joueur			
				DataInputStream entre = new DataInputStream(socket.getInputStream());
				DataOutputStream sortie = new DataOutputStream(socket.getOutputStream());
				sortie.writeInt(numJoueur);
				
				//Joueurs.add(new Users(entre, sortie, nomJoueur));

				ecouteClient(entre, sortie, numJoueur);
				
				
//				sortie.close(); TODO deconnection
//				entre.close();
//				socket.close();
			} else {
				System.out.println("nb max connection dï¿½passï¿½");
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void ecouteClient(DataInputStream in, DataOutputStream out, int numJoueur) throws IOException {
		int actionDemander;
		while(connected) {
			//ecoute des clients
			actionDemander = in.readInt();
			if(actionDemander == action.connexion.ordinal()) {
				System.out.println("tentative de connection de " + numJoueur);
				String pseudo = in.readUTF();
				String mdp = in.readUTF();
				System.out.println(pseudo + " essaye de se connecter avec le mdp " + mdp);
				out.writeBoolean(connexion(pseudo, mdp));
			}	
			else if(actionDemander == action.inscription.ordinal()) {
				System.out.println("tentative d'inscription de" + numJoueur);
				String pseudo = in.readUTF();
				String mdp = in.readUTF();
				System.out.println("joueur" + numJoueur + " essaye de s'inscrire avec le pseudo " + pseudo);
				out.writeBoolean(inscription(pseudo, mdp));
			} else if(actionDemander == action.scorePerso.ordinal()) {
				System.out.println("reccuperation score" + numJoueur);
				String pseudo = in.readUTF();
				String[] scores = new String[10];
				scores = csv.LireScore(pseudo, utilisateurs);
				for (int i = 0; i < 10; i ++) {
					out.writeUTF(scores[i]);
				}
			} else if (actionDemander == action.scorePartie.ordinal()) {
				System.out.println("reccuperation meilleur score sur une partie pour" + numJoueur);
				String[][] scores = new String[10][2];
				scores = csv.LireScoreP();
				for (int i = 0; i < 10; i ++) {
					out.writeUTF(scores[i][0]);
					out.writeUTF(scores[i][1]);
				}
			} else if (actionDemander == action.scoreGeneral.ordinal()) {
				System.out.println("reccuperation meilleur score cumulée pour" + numJoueur);
				String[][] scores = new String[10][2];
				scores = csv.LireScoreG();
				for (int i = 0; i < 10; i ++) {
					out.writeUTF(scores[i][0]);
					out.writeUTF(scores[i][1]);
				}
			} else if (actionDemander == action.deconnexion.ordinal()) {
				System.out.println("deconexion du joueur " + numJoueur);
				csv.EcrireFichierUtilisateurs(utilisateurs);
				nbConn --;
			} else if (actionDemander == action.nouveauScore.ordinal()) {
				System.out.println("joueur" + numJoueur + "vient de finir une partie");
				String pseudo = in.readUTF();
				int score = in.readInt();
				csv.MettreScore(pseudo, score, utilisateurs);
				csv.MettreScoreTot(pseudo, score, utilisateurs);
				csv.MettreScoreG(pseudo, Integer.valueOf(csv.LireScoreTot(pseudo, utilisateurs)));
				csv.MettreScoreP(pseudo, score);
			}
		}	
	}
	
	boolean connexion(String pseudo, String mdp) {
		String mdpExistant = csv.ChercherUtilisateur(pseudo, utilisateurs);
		return mdp.equals(mdpExistant);	
	}	
	
	boolean inscription(String pseudo, String mdp) {
		boolean ajouter = csv.AjouterUtilisateur(pseudo ,mdp, utilisateurs);
		if (!ajouter) {
			System.out.println("login existant");
			return false;
		} 
		csv.EcrireFichierUtilisateurs(utilisateurs);
		return true;
	}
}






