import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

public class LancerQuestion {
	private final static int NBQUESTION = 5;
	private final static int TEMPSPARQUESTION = 5;
	private StackPane root;
	private int round;
	private int score;
	private int tempsRestant;
	private int tempsEcoule;

	public LancerQuestion(StackPane root, Joueur joueur, String[] questionReponses, int round) {
		this.score = 0;
		this.root = root;
		this.round = round;
		this.tempsRestant = TEMPSPARQUESTION;
		tempsEcoule=0;
		construireQuestion(joueur, questionReponses);
	}

	// si la partie a déjà commencé, on change le score
	public LancerQuestion(StackPane root, Joueur joueur, String[] questionReponses, int round, int score) {
		this.score = score;
		this.root = root;
		this.round = round;
		this.tempsRestant = TEMPSPARQUESTION;
		tempsEcoule=0;
		construireQuestion(joueur, questionReponses);
	}

	void construireQuestion(Joueur joueur, String[] questionReponses) {
		// on enlève les objets de la page de connection au serveur
		root.getChildren().clear();

		// creation d'un borderPane auquel on va ajouter les objets
		BorderPane pane = new BorderPane();

		// définition des objets graphiques
		// création du menu
		Node menu = Utils.createMenu(this, root, joueur);

		// TO DO : utiliser la fonction qui sera créé
		// si la fonction retourne { question, rep1 , rep2, rep3, bonne rep } c'est
		// parfait sinon faudra modifier un peu
		String[] reponses = { questionReponses[1], questionReponses[2], questionReponses[3], questionReponses[4] };
		reponses = Utils.shuffleArray(reponses);

		// TO DO : supprimer après avoir tester plusieurs fois
		for (int i = 0; i < reponses.length; i++) {
			System.out.println(reponses[i] + "\n");
		}
		
		// HBox pour le score et le temps restant
		HBox hboxUtils = new HBox();
		hboxUtils.setAlignment(Pos.CENTER);
		hboxUtils.setSpacing(30);
		Text texteScore = new Text("Score : " + score);
		texteScore.setFont(Font.font("Verdana", 15));
		texteScore.setTextAlignment(TextAlignment.CENTER);
		texteScore.setFill(Color.WHITE);
		Text texteTempsRestant = new Text("Temps : " + tempsRestant);
		texteTempsRestant.setFont(Font.font("Verdana", 15));
		texteTempsRestant.setTextAlignment(TextAlignment.CENTER);
		texteTempsRestant.setFill(Color.WHITE);
		ImageView image = new ImageView(new Image("background_small.png"));
		hboxUtils.getChildren().addAll(texteScore, image, texteTempsRestant);

		VBox vboxQuestionReponses = new VBox();
		vboxQuestionReponses.setAlignment(Pos.CENTER);
		vboxQuestionReponses.setSpacing(30);
		Text question = new Text("Question " + round +" :\n"+questionReponses[0]);
		question.setFont(Font.font("Verdana", 25));
		question.setTextAlignment(TextAlignment.CENTER);
		question.setFill(Color.WHITE);
		
		// TODO : supprimer la ligne qui suit !
		String[] questionReponsesRound2 = joueur.getQuestion();
		
		// Création d'une timeline pour qu'une nouvelle question soit affichée au bout de 5s
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), 
			a -> {
				new LancerQuestion(root, joueur, questionReponsesRound2, round + 1, score);
		}));
		Timeline timelineCounter = new Timeline(new KeyFrame(Duration.seconds(1),
			b -> {
				tempsEcoule++;
				int tempsAffiche = tempsRestant - tempsEcoule;
				if (tempsAffiche<=2) texteTempsRestant.setStyle("-fx-fill:red;");
				texteTempsRestant.setText("Temps : " + tempsAffiche);
		}));
		timelineCounter.setCycleCount(Timeline.INDEFINITE); 
		timelineCounter.play();
		timeline.play();
		
		// les timers doivent s'arrêter si on quitte la page avant d'avoir fini le jeu
		pane.sceneProperty().addListener((obs, oldValue, newValue) -> {
			timeline.stop();
			timelineCounter.stop();
		});

		String bonneReponse = questionReponses[4];

		Button reponse1 = new Button(reponses[0]);
		Button reponse2 = new Button(reponses[1]);
		Button reponse3 = new Button(reponses[2]);
		Button reponse4 = new Button(reponses[3]);
		reponse1.setOnAction(value -> {
			Utils.checkResponse(root, joueur, round, score, NBQUESTION, reponse1, reponse2, reponse3, reponse4, bonneReponse);
			timeline.stop();
		});

		reponse2.setOnAction(value -> {
			Utils.checkResponse(root, joueur, round, score, NBQUESTION, reponse2, reponse1, reponse3, reponse4, bonneReponse);
			timeline.stop();
		});

		reponse3.setOnAction(value -> {
			Utils.checkResponse(root, joueur, round, score, NBQUESTION, reponse3, reponse2, reponse1, reponse4, bonneReponse);
			timeline.stop();
		});

		reponse4.setOnAction(value -> {
			Utils.checkResponse(root, joueur, round, score, NBQUESTION, reponse4, reponse2, reponse3, reponse1, bonneReponse);
			timeline.stop();
		});
		reponse1.getStyleClass().add("buttonStyle1");
		reponse2.getStyleClass().add("buttonStyle1");
		reponse3.getStyleClass().add("buttonStyle1");
		reponse4.getStyleClass().add("buttonStyle1");
		vboxQuestionReponses.getChildren().addAll(hboxUtils, question, reponse1, reponse2, reponse3, reponse4);

		pane.setTop(menu);
		pane.setCenter(vboxQuestionReponses);

		// ajout à la racine
		root.getChildren().addAll(pane);
	}
}
