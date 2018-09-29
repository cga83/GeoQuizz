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

public class PageScores {
	private StackPane root;
	
	public PageScores(StackPane root, Joueur joueur) {
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
		 FonctionsUtilisateur csv = new FonctionsUtilisateur();
		 String[] classement = csv.LireScore(joueur.getLogin()); // TO DO : mettre le login de manière dynamique
		 Text titre = new Text("Meilleurs scores");
		 titre.setFont(Font.font("Verdana", 25));
		 titre.setTextAlignment(TextAlignment.CENTER);
		 titre.setFill(Color.WHITE);
		 ImageView image = new ImageView(new Image("meilleurscore.png"));
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
		 vboxClassement.getChildren().addAll(titre, image, texteClassement);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxClassement);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}
