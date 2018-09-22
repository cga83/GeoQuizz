import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	private static ServerSocket GESTSOCKET;
	private static final int NBMAXCONN = 4;
	private static  int nbConn = 0;
	
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
				System.out.println("client connecté");
				
				//relance nvelle attente 
				//connected = true;
				Thread th = new Thread(() ->  {
					waitJoueur();
				});
				th.start();
				nbConn ++;

				//communication avec le joueur			
				DataInputStream entre = new DataInputStream(socket.getInputStream());
				DataOutputStream sortie = new DataOutputStream(socket.getOutputStream());
				
				String nomJoueur = entre.readUTF();
				
				//Joueurs.add(new Users(entre, sortie, nomJoueur));
				
				System.out.println(nomJoueur+"connected"); 
				sortie.writeInt(nbConn);
				
				//ecouteClient(entre, nomJoueur);
				
				
//				sortie.close(); TODO deconnection
//				entre.close();
//				socket.close();
			} else {
				System.out.println("nb max connection dépassé");
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}






