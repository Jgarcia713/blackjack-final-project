package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presenter.BlackjackGame;
import javafx.animation.AnimationTimer;

public class BlackjackGUI extends Application {
	BlackjackGame game;
	final int gameWidth = 1200;
	final int gameHeight = 950;
	Canvas canvas;
	GraphicsContext g;
	Image background;

	@Override
	public void start(Stage stage) throws Exception {
		// initialize objects
		game = new BlackjackGame();
		BlackjackButtonControls buttons = new BlackjackButtonControls(game);
		BorderPane pane = new BorderPane();

		// create canvas
		canvas = new Canvas(gameWidth,gameHeight-50);
		g = canvas.getGraphicsContext2D();

		// draw background (This should probably be moved to some render method so it's redrawn every frame
		background = new Image(new FileInputStream("images/blackjack-background.png"));

		// add nodes to pane
		pane.setCenter(canvas);
		pane.setBottom(buttons);

		// initialize scene
		Scene scene = new Scene(pane, gameWidth, gameHeight);
		stage.setScene(scene);
		

		final long startNanoTime = System.nanoTime();
		// This is a timer that runs at 60 frames per second. It can be used for rendering the canvas
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
                try {
                    render();
					cardSpinAnimation(startNanoTime, currentNanoTime);
				} catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
		}.start();

		stage.show();
	}
	
	private void createSampleCard() throws FileNotFoundException {
		CardSprite card = new CardSprite(100, 100, 0, null);
		card.draw(g);
		CardSprite card1 = new CardSprite(300, 300, 0, null);
		card1.draw(g);
		CardSprite card2 = new CardSprite(500, 500, 0, null);
		card2.draw(g);
	}


	private void render() throws FileNotFoundException {
		//background
		g.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
	}

	private void cardSpinAnimation(long startNanoTime, long currentNanoTime) throws FileNotFoundException {
		double t = (currentNanoTime - startNanoTime) / 1000000000.0;
		double x = 232 + 128 * Math.cos(t);
		double y = 232 + 128 * Math.sin(t);

		CardSprite card = new CardSprite(x+200, y, 0, null);
		card.draw(g);
		CardSprite card1 = new CardSprite(x, y-90, 0, null);
		card1.draw(g);
		CardSprite card2 = new CardSprite(x-100, y, 0, null);
		card2.draw(g);

	}

	private void initializeGame() {
		game.addPlayer("Player 1", true);
	}
}
