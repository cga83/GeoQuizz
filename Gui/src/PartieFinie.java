import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PartieFinie {
	private StackPane root;
	private int score;
	
	public PartieFinie(StackPane root, Joueur joueur, int score) {
		this.root = root;
		this.score = score;
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
		 VBox vboxScore = new VBox();
		 vboxScore.setSpacing(30);
		 vboxScore.setAlignment(Pos.CENTER);
		 Text texteScore = new Text("Score : " + score);
		 texteScore.setFont(Font.font("Verdana", 30));
		 texteScore.setTextAlignment(TextAlignment.CENTER);
		 texteScore.setFill(Color.WHITE);
		 
		 Text messageScore = new Text();
		 switch(score) {
		 case(0):
			 messageScore.setText("T'es un gros naze !\nVa falloir réviser...");
		 	 break;
		 case(1):
			 messageScore.setText("Bof...");
		 	 break;
		 case(2):
			 messageScore.setText("C'est un début...");
		 	 break;
		 case(3): 
			 messageScore.setText("Pas mal, peut mieux faire...");
		 	 break;
		 case(4):
			 messageScore.setText("Bien joué !");
		 	 break;
		 case(5):
			 messageScore.setText("Excellent !");
		 	 break;
		 }
		 messageScore.setFont(Font.font("Verdana", 20));
		 messageScore.setTextAlignment(TextAlignment.CENTER);
		 messageScore.setFill(Color.WHITE);
		 
		 vboxScore.getChildren().addAll(texteScore, messageScore);
		 
		 VBox vboxBoutons = new VBox();
		 vboxBoutons.setSpacing(30);
		 vboxBoutons.setAlignment(Pos.CENTER);
		 Button boutonRejouer = new Button("Rejouer");
		 boutonRejouer.getStyleClass().add("buttonStyle1");
		 boutonRejouer.setOnAction(value ->  {
			 // TO DO : remplacer cette ligne
			 String[] questionReponsesRound1 = joueur.getQuestion();
			 new LancerQuestion(root, joueur, questionReponsesRound1, 1);
		 });
		 Button boutonAccueil = new Button("Accueil");
		 boutonAccueil.getStyleClass().add("buttonStyle1");
		 boutonAccueil.setOnAction(value ->  {
			 new PageJoueur(root, joueur);
		 });
		 
		 vboxBoutons.getChildren().addAll(boutonRejouer, boutonAccueil);
		 
		 pane.setTop(menu);
		 pane.setCenter(vboxScore);
		 pane.setBottom(vboxBoutons);
		 
		 // ajout à la racine
		 root.getChildren().addAll(pane);
	}
}

