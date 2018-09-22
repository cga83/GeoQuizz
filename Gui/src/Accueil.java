    import java.awt.Window;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
     
    public class Accueil extends Application {
    	/**définition de la racine*/
    	StackPane root;
     
         /**lancement de l'application*/
         public void start(Stage primaryStage) {
        	 construireScene(primaryStage);
         }
         
        /**construction des objets que l'on va afficher*/
         void construireScene(Stage primaryStage)  
         {
        	 // définition de la fenêtre
        	 int largeur = 500;
        	 int hauteur = 800;
        	 
        	 root = new StackPane();
            
        	 // definir css
        	 root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            
        	 //definir la scene principale
        	 Scene scene = new Scene(root, largeur, hauteur);
        	 primaryStage.getIcons().add(new Image("logo.png"));
        	 primaryStage.setTitle("GeoQuiz");
        	 primaryStage.setScene(scene);
     
        	 //definir les objets graphiques
        	 // ajout du titre dans une hbox
        	 HBox hboxTitle = new HBox();
        	 hboxTitle.setAlignment(Pos.TOP_CENTER);
            
        	 Text titleGeo = new Text();
        	 titleGeo.setFont(Font.font ("Lato", 50));
        	 titleGeo.setTextAlignment(TextAlignment.CENTER);
        	 titleGeo.setFill(Color.rgb(255,170,170));
        	 titleGeo.setText("GEO");
        	 
        	 Text titleQuiz = new Text();
        	 titleQuiz.setFont(Font.font ("Verdana", 50));
        	 titleQuiz.setTextAlignment(TextAlignment.CENTER);
        	 titleQuiz.setFill(Color.WHITE);
        	 titleQuiz.setText("Quiz");
            
        	 hboxTitle.getChildren().addAll(titleGeo, titleQuiz);
            
        	 // ajout d'une image
        	 ImageView geoImage = new ImageView(new Image("background.png"));
        	 geoImage.setFitWidth(450);
        	 geoImage.setFitHeight(450);
            
        	 // ajout de boutons dans une vbox
        	 VBox vboxButtons = new VBox();
        	 vboxButtons.setAlignment(Pos.BOTTOM_CENTER);
        	 vboxButtons.setSpacing(20);
        	 
        	 // ajout d'un bouton connexion
         	 Button buttonConnexion = new Button("Se connecter");
             buttonConnexion.setOnAction(value ->  {
            	 JOptionPane.showMessageDialog(null, "Connexion désirée");
              });
             buttonConnexion.getStyleClass().add("button");
            
            // ajout d'un bouton s'inscrire
         	Button buttonInscription = new Button("S'inscrire");
         	buttonInscription.setOnAction(value ->  {
         		JOptionPane.showMessageDialog(null, "Inscription désirée");
            });
         	buttonInscription.getStyleClass().add("button");
         	
         	vboxButtons.getChildren().addAll(buttonConnexion, buttonInscription);
            
            // ajout de tous les objets à la racine
            root.getChildren().addAll(hboxTitle, geoImage, vboxButtons);
     
            //une transition sur l'opacité de l'objet
            /*FadeTransition ft = new FadeTransition(Duration.millis(2000), rectangle);
            ft.setFromValue(1.0);
            ft.setToValue(0.01);
            ft.setCycleCount(2);
            ft.setAutoReverse(true);
            ft.play();
           
            //une transition sur la position de l'objet
            TranslateTransition tt = new TranslateTransition(Duration.millis(3000), rectangle);
            tt.setFromX(0);
            //coordonnée relative
            tt.setToX(-largeur+largeur/10);
            tt.setCycleCount(1);
            tt.play();*/
           
            //afficher le theatre
            primaryStage.show();      
         }
           
         /**lancement du prog*/
         public static void main(String[] args) {
            launch(args);
         }
      }
