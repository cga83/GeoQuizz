import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ConnectionServer extends Application {
		// création de la racine
		StackPane root;
	 
		 // lancement de l'application
		 public void start(Stage primaryStage) {
			 construireScene(primaryStage);
		 }
		 
		 // construction des objets que l'on va afficher
		 void construireScene(Stage primaryStage)  
		 {
			 // définition de la fenêtre
			 int largeur = 500;
			 int hauteur = 800;
			 
			 root = new StackPane();
			
			 // ajout d'une feuille css
			 root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
			
			 // ajout de la scène principale dans laquelle vont être contenus les éléments
			 Scene scene = new Scene(root, largeur, hauteur);
			 primaryStage.getIcons().add(new Image("logo.png"));
			 primaryStage.setTitle("GeoQuiz");
			 primaryStage.setScene(scene);
		 
			 // définition des objets graphiques
			 // ajout de texte dans une vbox
			 VBox vboxPrincipal = new VBox();
			 vboxPrincipal.setAlignment(Pos.CENTER);
			 vboxPrincipal.setSpacing(20);
			 Text texteBienvenue = new Text("Bienvenue !");
			 texteBienvenue.setFont(Font.font ("Lato", 50));
			 texteBienvenue.setTextAlignment(TextAlignment.CENTER);
			 texteBienvenue.setFill(Color.rgb(255,170,170));
			 Text texteChoixServeur = new Text("Choisis ton serveur et ton port.");
			 texteChoixServeur.setFont(Font.font ("Verdana", 20));
			 texteChoixServeur.setTextAlignment(TextAlignment.CENTER);
			 texteChoixServeur.setFill(Color.WHITE);
			 vboxPrincipal.getChildren().addAll(texteBienvenue, texteChoixServeur);
			 
			 // ajout d'une image
			 ImageView image = new ImageView(new Image("happyWorld.png"));
			 
			 // choix du serveur et du port
			 HBox hboxServeur = new HBox();
			 hboxServeur.setAlignment(Pos.CENTER);
			 Text texteServeur = new Text("serveur : ");
			 texteServeur.setFill(Color.WHITE);
			 TextField choixServeur = new TextField("localhost");
			 hboxServeur.getChildren().addAll(texteServeur, choixServeur);
			 HBox hboxPort = new HBox();
			 hboxPort.setAlignment(Pos.CENTER);
			 Text textePort = new Text("port :      ");
			 textePort.setFill(Color.WHITE);
			 TextField choixPort = new TextField("10081");
			 hboxPort.getChildren().addAll(textePort, choixPort);
			 
			 // bouton pour valider
			 Button boutonValider = new Button("Ok !");
			 boutonValider.getStyleClass().add("button");
			 boutonValider.setOnAction(value ->  {
				 String port = choixPort.getText();
				 String serveur = choixServeur.getText();
				 String message = "Tentative de connexion sur le serveur " + serveur + " et sur le port " + port + ".";
				JOptionPane.showMessageDialog(null, message);
			 });
			 
			 vboxPrincipal.getChildren().addAll(image, hboxServeur, hboxPort, boutonValider);
			 
			 root.getChildren().addAll(vboxPrincipal);
		 
		    //afficher la scène
		    primaryStage.show();      
		}
		   
		 // lancement
		 public static void main(String[] args) {
			 launch(args);
		 }

}
