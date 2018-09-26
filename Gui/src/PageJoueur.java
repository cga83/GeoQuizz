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
	
	public PageJoueur(StackPane root, String login, Joueur joueur) {
		this.root = root;
		construirePageJoueur(login, joueur);
	}
	
	void construirePageJoueur(String login, Joueur joueur) {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // ajout d'un texte
		 String texte = "Salut " + login + " !";
		 Text textePage = new Text(texte);
		 textePage.setFill(Color.WHITE);
		 textePage.setFont(Font.font ("Verdana", 30));
		 
		 // ajout de boutons
		 VBox vboxBoutons = new VBox();
		 Button boutonClassement = new Button("Classement");
		 boutonClassement.setOnAction(value ->  {
			 new PageClassement(root, login,joueur);
		 });
		 Button boutonScores = new Button("Scores");
		 boutonScores.setOnAction(value ->  {
			 new PageScores(root, login, joueur);
		 });
		 Button boutonNouveauJeu = new Button("Nouvelle partie");
		 boutonNouveauJeu.setOnAction(value ->  {
			 new DemarrerJeu(root, login, joueur);
		 });
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
