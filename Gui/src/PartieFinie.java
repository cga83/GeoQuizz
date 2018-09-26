import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PartieFinie {
	private StackPane root;
	
	public PartieFinie(StackPane root, Joueur joueur) {
		this.root = root;
		construirePageScores(joueur);
	}
	
	void construirePageScores(Joueur joueur) {
		// on enlève les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // définition des objets graphiques
		 // création du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // affichage des scores
		 Text todo = new Text("PAGE A FAIRE");
		 
		 pane.setTop(menu);
		 pane.setCenter(todo);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}

