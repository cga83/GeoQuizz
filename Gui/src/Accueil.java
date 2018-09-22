    import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
     
     
    public class Accueil extends Application {
     
         /**objets graphiques représentant un cercle*/
         public  Rectangle rectangle;
         
       /**definir la troupe des objets graphiques*/
            StackPane root;
     
         /**lancement de l'application*/
         public void start(Stage primaryStage) {
     
            construireScene( primaryStage);
         }
         
        /**construction des objets affichés*/
         void construireScene(Stage primaryStage)  
         {
            int largeur = 500;
            int hauteur = 800;
            //definir la troupe
            root = new StackPane();
            root.setId("pane");
            
            // definir css
            //root.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
            
            //definir la scene principale
            Scene scene = new Scene(root, largeur, hauteur, Color.rgb(15,181,94));
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
            
            root.getChildren().addAll(hboxTitle, geoImage);
     
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
