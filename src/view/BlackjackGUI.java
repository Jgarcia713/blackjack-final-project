package view;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BlackjackGUI extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane window = new BorderPane();

		ImageView image = new ImageView(new Image(new FileInputStream("images/blackjack-background.png")));
		image.setFitHeight(952);
		image.setFitWidth(952);
		image.setPreserveRatio(true);
		window.setCenter(image);
		
		Scene scene = new Scene(window, 950, 700);
		stage.setScene(scene);
		
		this.createSampleCard();
		
		stage.show();
		
	}
	
	private void createSampleCard() {
		
	}

}
