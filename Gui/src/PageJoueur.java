import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PageJoueur {
	private StackPane root;
	
	public PageJoueur(StackPane root, String login) {
		this.root = root;
		construirePageJoueur(login);
	}
	
	void construirePageJoueur(String login) {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root);
		 
		 // ajout d'un texte
		 String texte = "Salut " + login + " !";
		 Text textePage = new Text(texte);
		 textePage.setFill(Color.WHITE);
		 textePage.setFont(Font.font ("Verdana", 30));
		 
		 // ajout de boutons
		 VBox vboxBoutons = new VBox();
		 Button boutonClassement = new Button("Classement");
		 Button boutonScores = new Button("Scores");
		 Button boutonNouveauJeu = new Button("Nouvelle partie");
		 boutonClassement.getStyleClass().add("buttonStyle1");
		 boutonScores.getStyleClass().add("buttonStyle1");
		 boutonNouveauJeu.getStyleClass().add("buttonStyle1");
		 vboxBoutons.getChildren().addAll(boutonClassement, boutonScores, boutonNouveauJeu);
		 
		 // positionnement des objets
		 pane.setTop(menu);
		 pane.setCenter(textePage);
		 vboxBoutons.setAlignment(Pos.CENTER);
		 vboxBoutons.setSpacing(30);
		 pane.setBottom(vboxBoutons);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}
