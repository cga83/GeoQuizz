import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PageClassement {
	private StackPane root;
	
	public PageClassement(StackPane root, Joueur joueur) {
		this.root = root;
		construirePageClassement(joueur);
	}
	
	void construirePageClassement( Joueur joueur) {
		// on enl�ve les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // d�finition des objets graphiques
		 // cr�ation du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // cr�ation de deux boutons pour afficher les meilleurs scores de deux mani�res diff�rentes
		 HBox hboxBoutons = new HBox();
		 hboxBoutons.setSpacing(30);
		 hboxBoutons.setAlignment(Pos.CENTER);
		 Button boutonTotal = new Button("Score total");
		 Button boutonMeilleurScore = new Button("Meilleur score");
		 boutonMeilleurScore.setOnAction(value -> {
			 new PageClassementMeilleurScore(root, joueur);
		 });
		 boutonTotal.getStyleClass().add("buttonStyle2");
		 boutonMeilleurScore.getStyleClass().add("buttonStyle1");
		 hboxBoutons.getChildren().addAll(boutonTotal, boutonMeilleurScore);
		 
		 ImageView image = new ImageView(new Image("podium.png"));
		 
		 // affichage des scores
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
		 vboxClassement.getChildren().addAll(hboxBoutons, image, titre, texteClassement);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxClassement);
		 
		 // ajout � la racine
		 root.getChildren().addAll(pane);
	}
}
