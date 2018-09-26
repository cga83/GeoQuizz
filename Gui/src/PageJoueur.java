import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PageJoueur {
	private StackPane root;
	
	public PageJoueur(StackPane root,Joueur joueur) {
		this.root = root;
		construirePageJoueur(joueur);
	}
	
	void construirePageJoueur(Joueur joueur) {
		// on enl�ve les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // d�finition des objets graphiques
		 // cr�ation du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // ajout d'un texte
		 VBox vboxCenter = new VBox();
		 String texte = "Salut " + joueur.getLogin() + " !";
		 Text textePage = new Text(texte);
		 textePage.setFill(Color.WHITE);
		 textePage.setFont(Font.font ("Verdana", 30));
		 ImageView image = new ImageView(new Image("perso.png"));
		 vboxCenter.getChildren().addAll(textePage, image);
		 
		 // ajout de boutons
		 VBox vboxBoutons = new VBox();
		 Button boutonClassement = new Button("Classement");
		 boutonClassement.setOnAction(value ->  {
			 new PageClassement(root, joueur);
		 });
		 Button boutonScores = new Button("Scores");
		 boutonScores.setOnAction(value ->  {
			 new PageScores(root, joueur);
		 });
		 Button boutonNouveauJeu = new Button("Nouvelle partie");
		 boutonNouveauJeu.setOnAction(value ->  {
			 new DemarrerJeu(root, joueur);
		 });
		 boutonClassement.getStyleClass().add("buttonStyle1");
		 boutonScores.getStyleClass().add("buttonStyle1");
		 boutonNouveauJeu.getStyleClass().add("buttonStyle1");
		 vboxBoutons.getChildren().addAll(boutonClassement, boutonScores, boutonNouveauJeu);
		 
		 // positionnement des objets
		 pane.setTop(menu);
		 pane.setCenter(vboxCenter);
		 vboxCenter.setAlignment(Pos.CENTER);
		 vboxCenter.setSpacing(20);
		 vboxBoutons.setAlignment(Pos.CENTER);
		 vboxBoutons.setSpacing(30);
		 pane.setBottom(vboxBoutons);
		 
	    // ajout d'une transition sur l'opacit� de l'image
	    FadeTransition transitionImage = new FadeTransition(Duration.millis(3000), image);
	    transitionImage.setFromValue(0.01);
	    transitionImage.setToValue(0.8);
	    transitionImage.play(); 
		 
		 // ajout � la racine
		 root.getChildren().addAll(pane);
	}
}
