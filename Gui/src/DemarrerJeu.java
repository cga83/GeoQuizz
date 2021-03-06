import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class DemarrerJeu {
	private StackPane root;
	
	public DemarrerJeu(StackPane root, Joueur joueur) {
		this.root = root;
		construireDemarrerJeu(joueur);
	}
	
	void construireDemarrerJeu(Joueur joueur) {
		// on enl�ve les objets de la page de connection au serveur
		 root.getChildren().clear();
		 
		 // creation d'un borderPane auquel on va ajouter les objets
		 BorderPane pane = new BorderPane();
		 
		 // d�finition des objets graphiques
		 // cr�ation du menu
		 Node menu = Utils.createMenu(this, root, joueur);
		 
		 // ajout de boutons
		 VBox vboxBoutons = new VBox();
		 Button boutonRegles = new Button("R�gles du jeu");
		 boutonRegles.setOnAction(value -> {
			 new PageRegles(root, joueur);
		 });
		 Button boutonDemarrer = new Button("D�marrer la partie");
		 boutonDemarrer.setOnAction(value -> {
			 // TO DO : remplacer cette ligne
			 String[] questionReponsesRound1 = joueur.getQuestion();
			 new LancerQuestion(root, joueur,  questionReponsesRound1, 1);
		 });
		 boutonRegles.getStyleClass().add("buttonStyle1");
		 boutonDemarrer.getStyleClass().add("buttonStyle1");
		 vboxBoutons.getChildren().addAll(boutonRegles, boutonDemarrer);
		 
		 // positionnement des objets
		 pane.setTop(menu);
		 vboxBoutons.setSpacing(30);
		 vboxBoutons.setAlignment(Pos.CENTER);
		 pane.setCenter(vboxBoutons);
		 
		 // ajout � la racine
		 root.getChildren().addAll(pane);
	}
}
