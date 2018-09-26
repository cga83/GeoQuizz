import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PageRegles {
	private StackPane root;
	
	public PageRegles(StackPane root, String login) {
		this.root = root;
		construirePageRegles();
	}
	
	void construirePageRegles() {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root);
		 String texte = "Les règles de GeoQuiz sont simples !\nPlusieurs questions de géographie vont t'être posé.\nPour chaque questions, tu auras quatre possibilités.\nSi tu réponds correctement, tu marques des points !\nAttention, le temps pour chaque question est limité...";
		 Text regles = new Text(texte);
		 regles.setFont(Font.font ("Verdana", 15));
		 regles.setTextAlignment(TextAlignment.CENTER);
		 regles.setStyle("-fx-fill: white;");
		 
		 // ajout de boutons
		 
		 // positionnement des objets
		 pane.setTop(menu);
		 pane.setCenter(regles);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}
