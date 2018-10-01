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
		
		//Recup�re tous les utilisateurs et mots de passe via fichier csv
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
				System.out.println("client connect�");
				
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
				System.out.println("nb max connection d�pass�");
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
				System.out.println("reccuperation meilleur score cumul�e pour" + numJoueur);
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
			} else if (actionDemander == action.question.ordinal()) { 
				String[] questionreponses = new String[5]; 
				questionreponses = getQuestion(); 
				for(int i = 0; i<5; i++) { 
					out.writeUTF(questionreponses[i]); 
				} 
			}  else if (actionDemander == action.nouveauScore.ordinal()) {
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
	
	String[] getQuestion() {
		String[] reponses = new String[5];
		FonctionsCSV csv = new FonctionsCSV();
		int indice = (int) (Math.random() * ( 6 )) ;
		System.out.println("tirage au sort : " + indice);
		if(indice == 0) { // Deviner le num�ro du d�partement � partir du nom
			String[] bonnereponse = csv.CoupleDepartementNumero();
			String rep1 = csv.NumeroDepRandom();
			String rep2 = csv.NumeroDepRandom();
			String rep3 = csv.NumeroDepRandom();
			while(rep1.equals(bonnereponse[1]))
				rep1 = csv.NumeroDepRandom();
			while(rep2.equals(bonnereponse[1]) || rep2.equals(rep1))
				rep1 = csv.NumeroDepRandom();
			while(rep3.equals(bonnereponse[1]) || rep3.equals(rep1) || rep3.equals(rep2))
				rep1 = csv.NumeroDepRandom();
			reponses[0] = "Quel est le num�ro du \nd�partement " + bonnereponse[0] + " ?";
			reponses[1] = rep3;
			reponses[2] = rep1;
			reponses[3] = rep2;
			reponses[4] = bonnereponse[1];
		} else if (indice == 1) { // Deviner le nom du d�partement � partir du num�ro
			String[] bonnereponse = csv.CoupleDepartementNumero();
			String rep1 = csv.DepartementRandom();
			String rep2 = csv.DepartementRandom();
			String rep3 = csv.DepartementRandom();
			while(rep1.equals(bonnereponse[0]))
				rep1 = csv.DepartementRandom();
			while(rep2.equals(bonnereponse[0]) ||  rep2.equals(rep1))
				rep2 = csv.DepartementRandom();
			while(rep3.equals(bonnereponse[0]) ||  rep3.equals(rep1) ||  rep3.equals(rep2))
				rep3 = csv.DepartementRandom();
			reponses[0] = "Quel d�partement a le \nnum�ro " +bonnereponse[1] + " ?";
			reponses[1] = rep3;
			reponses[2] = rep1;
			reponses[3] = rep2;
			reponses[4] = bonnereponse[0];
		} else if (indice == 2) { // Deviner dans quelle r�gion est ce d�partement
			String[] bonnereponse = csv.CoupleRegionDepartement();
			String rep1 = csv.RegionRandom();
			String rep2 = csv.RegionRandom();
			String rep3 = csv.RegionRandom();
			while(rep1.equals(bonnereponse[0]))
				rep1 = csv.RegionRandom();
			while(rep2.equals(bonnereponse[0]) ||  rep2.equals(rep1))
				rep2 = csv.RegionRandom();
			while(rep3.equals(bonnereponse[0]) ||  rep3.equals(rep1) ||  rep3.equals(rep2))
				rep3 = csv.RegionRandom();
			reponses[0] = "Dans quelle r�gion se trouve \n ce d�partement : " + bonnereponse[1] + " ?";
			reponses[1] = rep3;
			reponses[2] = rep1;
			reponses[3] = rep2;
			reponses[4] = bonnereponse[0];
		} else if (indice == 3) { // Deviner le chef lieu d'une r�gion donn�e
			String[] bonnereponse = csv.CoupleRegionCheflieu();
			String rep1 = csv.CheflieuRandom();
			String rep2 = csv.CheflieuRandom();
			String rep3 = csv.CheflieuRandom();
			while(rep1.equals(bonnereponse[1]))
				rep1 = csv.CheflieuRandom();
			while(rep2.equals(bonnereponse[1]) ||  rep2.equals(rep1))
				rep2 = csv.CheflieuRandom();
			while(rep3.equals(bonnereponse[1]) ||  rep3.equals(rep1) ||  rep3.equals(rep2))
				rep3 = csv.CheflieuRandom();
			reponses[0] = "Quel est le chef-lieu de la \n r�gion : " + bonnereponse[0] + " ?";
			reponses[1] = rep3;
			reponses[2] = rep1;
			reponses[3] = rep2;
			reponses[4] = bonnereponse[1];
		} else if (indice == 4) { // Dveiner la r�gion d'un chef lieu donn�
			String[] bonnereponse = csv.CoupleRegionCheflieu();
			String rep1 = csv.RegionRandom();
			String rep2 = csv.RegionRandom();
			String rep3 = csv.RegionRandom();
			while(rep1.equals(bonnereponse[0]))
				rep1 = csv.RegionRandom();
			while(rep2.equals(bonnereponse[0]) || rep2.equals(rep1))
				rep2 = csv.RegionRandom();
			while(rep3.equals(bonnereponse[0]) || rep3.equals(rep1) ||  rep3.equals(rep2))
				rep3 = csv.RegionRandom();
			reponses[0] = "De quelle r�gion \n " + bonnereponse[1] + "est le chef-lieu ?";
			reponses[1] = rep3;
			reponses[2] = rep1;
			reponses[3] = rep2;
			reponses[4] = bonnereponse[0];
		} else if (indice == 5) { // Deviner la capitale d'un pays donn�
			String[] bonnereponse = csv.CouplePaysCapitale();
			String rep1 = csv.CapitaleRandom();
			String rep2 = csv.CapitaleRandom();
			String rep3 = csv.CapitaleRandom();
			while(rep1.equals(bonnereponse[1]))
				rep1 = csv.CapitaleRandom();
			while(rep2.equals(bonnereponse[1]) ||  rep2.equals(rep1))
				rep2 = csv.CapitaleRandom();
			while(rep3.equals(bonnereponse[1]) ||  rep3.equals(rep1) ||  rep3.equals(rep2))
				rep3 = csv.CapitaleRandom();
			reponses[0] = "Quelle est la capitale de \n " + bonnereponse[0] + " ?";
			reponses[1] = rep3;
			reponses[2] = rep1;
			reponses[3] = rep2;
			reponses[4] = bonnereponse[1];
		} else if (indice == 6) { // Deviner le pays connaissant la capitale
			String[] bonnereponse = csv.CouplePaysCapitale();
			String rep1 = csv.PaysRandom();
			String rep2 = csv.PaysRandom();
			String rep3 = csv.PaysRandom();
			while(rep1.equals(bonnereponse[0]))
				rep1 = csv.PaysRandom();
			while(rep2.equals(bonnereponse[0]) ||  rep2.equals(rep1))
				rep2 = csv.PaysRandom();
			while(rep3.equals(bonnereponse[0]) ||  rep3.equals(rep1) ||  rep3.equals(rep2))
				rep3 = csv.PaysRandom();
			reponses[0] = "De quel pays " + bonnereponse[1] +"\n est-elle la capitale ?";
			reponses[1] = rep3;
			reponses[2] = rep1;
			reponses[3] = rep2;
			reponses[4] = bonnereponse[0];
		}
		return reponses ;
	}
}






