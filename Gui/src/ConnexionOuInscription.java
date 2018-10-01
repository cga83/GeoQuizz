// TO DO : r�cup�rer un argument "I" ou "C" et en fonction afficher un titre diff�rent et traiter la suite diff�rement

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class ConnexionOuInscription {
	public static enum Mode { CONNEXION, INSCRIPTION };
	// cr�ation de la racine
	private StackPane root;
	private Mode mode;
	
	public ConnexionOuInscription(StackPane root, Mode mode,  Joueur joueur) {
		this.root = root;
		this.mode = mode;
		construireConnexionOuInscription(joueur);
	}
	 
	 // construction des objets que l'on va afficher
	 private void construireConnexionOuInscription( Joueur joueur)  
	 {
		 // on enl�ve les �l�ments de la page pr�c�dente
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
	 
		 // d�finition des objets graphiques
		 // cr�ation du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // ajout de texte dans une vbox
		 VBox vboxPrincipal = new VBox();
		 vboxPrincipal.setAlignment(Pos.CENTER);
		 vboxPrincipal.setSpacing(30);
		 Text titre = new Text(mode==Mode.CONNEXION? "Connecte-toi !": "Inscris toi!");
		 titre.setFont(Font.font ("Lato", 30));
		 titre.setTextAlignment(TextAlignment.CENTER);
		 titre.setFill(Color.WHITE);

		 // choix du serveur et du port
		 HBox hboxPseudo = new HBox();
		 hboxPseudo.setAlignment(Pos.CENTER);
		 Text textePseudo = new Text("pseudo :          ");
		 textePseudo.setFill(Color.WHITE);
		 TextField choixPseudo = new TextField();
		 hboxPseudo.getChildren().addAll(textePseudo, choixPseudo);
		 HBox hboxMdp = new HBox();
		 hboxMdp.setAlignment(Pos.CENTER);
		 Text texteMdp = new Text("mot de passe : ");
		 texteMdp.setFill(Color.WHITE);
		 PasswordField choixMdp = new PasswordField();
		 hboxMdp.getChildren().addAll(texteMdp, choixMdp);
		 
		 // bouton pour valider
		 Button boutonValider = new Button("Ok !");
		 boutonValider.getStyleClass().add("buttonStyle1");
		 boutonValider.setOnAction(value ->  {
			 String mdp = choixMdp.getText();
			 String pseudo = choixPseudo.getText();
			 if (mode==Mode.CONNEXION) {
				 // si la connexion a march� ...
				 // on peut setter le login du joueur
				 if (joueur.connectAuJeu(pseudo,mdp)) {
					 new PageJoueur(root, joueur);
				 } else {
					 String messageConnexionFailed = "le couple pseudo mot de passe n'est pas reconnu";
					 Utils.createPopup(messageConnexionFailed); 
				 }
				
			 }
			 else {
				 if(joueur.inscrire(pseudo, mdp)) {
					 String message = "Ca y est, "+ pseudo + " tu es inscrit :) Maintenant connecte toi ci-dessous!" ;
					 Utils.createPopup(message);
					// si l'inscription a march�, on redirige vers la page de connexion
					 new ConnexionOuInscription(root, ConnexionOuInscription.Mode.CONNEXION, joueur);
				 }
				 else {
					 String messageInscriptionFailed = "Ce pseudo exist d�j�, recommencer avec un autre pseudo";
					 Utils.createPopup(messageInscriptionFailed);
				 }
			 }
		 });
		 
		 vboxPrincipal.getChildren().addAll(menu, titre, hboxPseudo, hboxMdp, boutonValider);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxPrincipal);
		 
		 root.getChildren().addAll(pane);
		 
	
		// ajout de tous les objets � la racine (qui est reli�e � primaryStage)
	    root.getChildren().addAll();     
	 }
}
