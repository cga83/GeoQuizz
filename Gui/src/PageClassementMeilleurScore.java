import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PageClassementMeilleurScore {
	private StackPane root;
	
	public PageClassementMeilleurScore(StackPane root, Joueur joueur) {
		this.root = root;
		construirePageClassementMeilleurScore(joueur);
	}
	
	void construirePageClassementMeilleurScore(Joueur joueur) {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // création de deux boutons pour afficher les meilleurs scores de deux manières différentes
		 HBox hboxBoutons = new HBox();
		 hboxBoutons.setSpacing(30);
		 hboxBoutons.setAlignment(Pos.CENTER);
		 Button boutonTotal = new Button("Score total");
		 boutonTotal.setOnAction(value -> {
			 new PageClassement(root, joueur);
		 });
		 Button boutonMeilleurScore = new Button("Meilleur score");
		 boutonTotal.getStyleClass().add("buttonStyle1");
		 boutonMeilleurScore.getStyleClass().add("buttonStyle2");
		 hboxBoutons.getChildren().addAll(boutonTotal, boutonMeilleurScore);
		 
		 // affichage des scores
		 // TO DO : changer la fonction quand elle sera prête
		 FonctionsCSV csv = new FonctionsCSV();
		 String[][] classement = csv.LireScoreG();
		 Text titre = new Text("Joueur - Score");
		 titre.setFont(Font.font("Verdana", 25));
		 titre.setTextAlignment(TextAlignment.CENTER);
		 titre.setFill(Color.WHITE);
		 Text texteClassement = new Text();
		 String texte = "";
		 for (int i = 0; i<classement.length; i++) {
			 texte+=classement[i][0] + " - " + classement[i][1] + "\n";
		 }
		 texteClassement.setText(texte);
		 texteClassement.setFill(Color.WHITE);
		 texteClassement.setFont(Font.font("Verdana", 15));
		 
		 VBox vboxClassement = new VBox();
		 vboxClassement.setAlignment(Pos.CENTER);
		 vboxClassement.setSpacing(30);
		 vboxClassement.getChildren().addAll(hboxBoutons, titre, texteClassement);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxClassement);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}
