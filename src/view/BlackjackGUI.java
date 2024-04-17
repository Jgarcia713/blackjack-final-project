package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class BlackjackGUI extends Application {
	Canvas canvas;
	
	@Override
	public void start(Stage stage) throws Exception {
		canvas = new Canvas(800,800);
		ImageView image = new ImageView(new Image(new FileInputStream("images/blackjack-background.png")));
		image.setFitHeight(952);
		image.setFitWidth(952);
		image.setPreserveRatio(true);
		StackPane window = new StackPane(image, canvas);
		
		Scene scene = new Scene(window, 950, 700);
		stage.setScene(scene);
		
		this.createSampleCard();
		
		stage.show();
		
	}
	
	private void createSampleCard() throws FileNotFoundException {
		CardSprite card = new CardSprite(100, 100, 0, null);
		card.draw(canvas.getGraphicsContext2D());
		CardSprite card1 = new CardSprite(300, 300, 0, null);
		card1.draw(canvas.getGraphicsContext2D());
		CardSprite card2 = new CardSprite(500, 500, 0, null);
		card2.draw(canvas.getGraphicsContext2D());
	}

}
