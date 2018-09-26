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

public class PageScores {
	private StackPane root;
	private String login;
	
	public PageScores(StackPane root, String login) {
		this.root = root;
		this.login = login;
		construirePageScores();
	}
	
	void construirePageScores() {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root);
		 
		 // affichage des scores
		 FonctionsCSV csv = new FonctionsCSV();
		 String[] classement = csv.LireScore("titi"); // TO DO : mettre le login de manière dynamique
		 Text titre = new Text("Meilleurs scores");
		 titre.setFont(Font.font("Verdana", 25));
		 titre.setTextAlignment(TextAlignment.CENTER);
		 titre.setFill(Color.WHITE);
		 Text texteClassement = new Text();
		 String texte = "";
		 for (int i = 0; i<classement.length; i++) {
			 texte+=i+1 + " : " + classement[i] + "\n";
		 }
		 texteClassement.setText(texte);
		 texteClassement.setFill(Color.WHITE);
		 texteClassement.setFont(Font.font("Verdana", 15));
		 
		 VBox vboxClassement = new VBox();
		 vboxClassement.setAlignment(Pos.CENTER);
		 vboxClassement.setSpacing(30);
		 vboxClassement.getChildren().addAll(titre, texteClassement);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxClassement);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}
