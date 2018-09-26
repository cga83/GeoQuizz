import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	private static ServerSocket GESTSOCKET;
	private static final int NBMAXCONN = 4;
	private static  int nbConn = 0;
	private static boolean connected = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Serveur();
	}

	Serveur() {
		System.out.println("Demarrage serveur");
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
				String nomJoueur = entre.readUTF();
				
				//Joueurs.add(new Users(entre, sortie, nomJoueur));
				
				System.out.println(nomJoueur+"connected"); 
				
				
				
				
				//ecouteClient(entre, sortie, numJoueur);
				
				
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
	
	void ecouteClient(DataInputStream In, DataOutputStream Out, int nomJoueur) throws IOException {
		int action;
		while(connected) {
			//ecoute des clients
			action = In.readInt();
			if(action == 1) {
				System.out.println("tentative de connection de nomJoueur");
				String pseudo = In.readUTF();
				String mdp = In.readUTF();
				System.out.println(pseudo + " essaye de se connecter avec le mdp " + mdp);
				 connexion(pseudo,mdp);
			}		
		}	
	}
	
	boolean connexion(String pseudo, String mdp) {
		String mdpExistant;
		//mdpExistant = ChercherUtilisateur(pseudo)
		mdpExistant = "test"; // TODO remplacer par ligne d'au dessus
		if (mdpExistant == null) {
			System.out.println("login inexistant");
			return false;
		} else {
			if(mdp == mdpExistant) {
				System.out.println("connexion r�ussi");
				return true;
			} else {
				System.out.println("mauvais mot de passe");
				return false;
			}
		}
		
	}
	
}






