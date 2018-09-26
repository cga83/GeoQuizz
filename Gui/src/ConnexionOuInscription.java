// TO DO : récupérer un argument "I" ou "C" et en fonction afficher un titre différent et traiter la suite différement

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
	// création de la racine
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
		 // on enlève les éléments de la page précédente
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
	 
		 // définition des objets graphiques
		 // création du menu
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
		 TextField choixMdp = new TextField();
		 hboxMdp.getChildren().addAll(texteMdp, choixMdp);
		 
		 // bouton pour valider
		 Button boutonValider = new Button("Ok !");
		 boutonValider.getStyleClass().add("buttonStyle1");
		 boutonValider.setOnAction(value ->  {
			 String mdp = choixMdp.getText();
			 String pseudo = choixPseudo.getText();
			 String message = "Tentative de connexion de " + pseudo + " avec le mdp " + mdp + ".";
			 Utils.createPopup(message); 
			 if (mode==Mode.CONNEXION) {
				 // si la connexion a marché ...
				 new PageJoueur(root, pseudo, joueur);
			 }
			 else {
				 // si l'inscription a marché, on redirige vers la page de connexion
				 // new ConnexionOuInscription(ConnexionOuInscription.Mode.CONNEXION, root);
			 }
		 });
		 
		 vboxPrincipal.getChildren().addAll(menu, titre, hboxPseudo, hboxMdp, boutonValider);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxPrincipal);
		 
		 root.getChildren().addAll(pane);
		 
	
		// ajout de tous les objets à la racine (qui est reliée à primaryStage)
	    root.getChildren().addAll();     
	 }
}
