package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.*;
import presenter.BlackjackGame;

/**
 * this class is a javafx application which allows a player to interact with a
 * blackjack game through a GUI.
 */
public class BlackjackGUI extends Application implements OurObserver<BlackjackGame> {

	public static void main(String[] args) {
		launch(args);
	}

	private BlackjackGame game;
	private final int gameWidth = 1000;
	private final int gameHeight = 750;
	private Canvas canvas;
	private GraphicsContext gc;
	private Image background;
	private BlackjackControls controlBar;
	private LoginPane login;
	private Image chip1, chip5, chip10, chip25, chip100, chip500;
	private boolean insurance = false;
	private boolean hideCard = false;

	/**
	 * Startup for the Blackjack GUI panel. Is run on startup. It initializes all
	 * the objects and systems.
	 * 
	 * @param stage javafx stage
	 * @throws FileNotFoundException thrown if background image cant be found
	 */
	@Override
	public void start(Stage stage) throws FileNotFoundException {

		// draw background (This should probably be moved to some render method so it's
		// redrawn every frame
		background = new Image(new FileInputStream("images/blackjack-background.png"));
		chip1 = new Image(new FileInputStream("images/chips/chip1.png"), 80, 80, false, false);
		chip5 = new Image(new FileInputStream("images/chips/chip2.png"), 80, 80, false, false);
		chip10 = new Image(new FileInputStream("images/chips/chip3.png"), 80, 80, false, false);
		chip25 = new Image(new FileInputStream("images/chips/chip4.png"), 80, 80, false, false);
		chip100 = new Image(new FileInputStream("images/chips/chip5.png"), 80, 80, false, false);
		chip500 = new Image(new FileInputStream("images/chips/chip6.png"), 80, 80, false, false);

		// create canvas
		canvas = new Canvas(gameWidth, gameHeight);
		gc = canvas.getGraphicsContext2D();

		login = new LoginPane(canvas, background);
		Scene scene = new Scene(login, gameWidth, gameHeight);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Blackjack");

		// Set the login pane to end when this anonymous function is ran
		login.setOnLoginSuccessListener(() -> {
			displayBlackjackGUI(stage); // Run the main GUI display
		});

		stage.show();
	}

	/**
	 * Display the actual Blackjack GUI elements
	 * 
	 * @param stage
	 */
	private void displayBlackjackGUI(Stage stage) {
		// initialize objects
		game = new BlackjackGame();
		initializeGame();
		controlBar = new BlackjackControls(game, game.getPlayers().get(0));

		game.addObserver(this);

		Pane pane = new Pane();

		// add nodes to pane
		pane.getChildren().add(canvas);
		pane.getChildren().add(controlBar);

		// initialize scene
		Scene scene = new Scene(pane, gameWidth, gameHeight);
		stage.setScene(scene);

		// update game
		update(game);
		this.drawChips();
	}

	/**
	 * does initial setup for the game. Currently, this just means creating the new
	 * player objects
	 */
	private void initializeGame() {
		game.addPlayer(login.getUsername(), true);
	}

	/**
	 * updates the gui with the current game data. Uses an observer/observable
	 * system to do so. The observable (BlackjackGame) will notify this observer to
	 * update whenever the game state changes
	 * 
	 * @param theGame reference to the BlackjackGame object that changed
	 */
	@Override
	public void update(BlackjackGame theGame) {

		// update canvas
		gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

		for (Player player : theGame.getPlayers()) {
			ArrayList<Card> cards = player.getHand().getHand();
			for (int i = 0; i < cards.size(); i++) {
				Card card = player.getHand().getHand().get(i);

				// this long line makes the cards center around a point in the bottom center of
				// the canvas
				CardSprite cardSprite = new CardSprite(gc, canvas.getWidth() / 2.0 + (i - cards.size() / 2.0) * 120,
						canvas.getHeight() * 0.7 + 30, 0, card, false);

				cardSprite.draw();
			}
		}

		ArrayList<Card> dealerCards = theGame.getDealerHand().getHand();
		for (int i = 0; i < dealerCards.size(); i++) {
			Card card = dealerCards.get(i);
			if (i == 1 && dealerCards.size() == 2 && (!theGame.getActivePlayer().isFolded() || !theGame.isGameOver ))
				hideCard = true;
			// this long line makes the cards center around a point in the top center of the
			// canvas
			CardSprite cardSprite = new CardSprite(gc, canvas.getWidth() / 2.0 + (i - dealerCards.size() / 2.0) * 120,
					canvas.getHeight() * 0.2, 0, card, hideCard);

			cardSprite.draw();
			hideCard = false;
		}

		// update control
		if (theGame.getActivePlayer() == null)
			return;
		controlBar.updateActivePlayerLabel(theGame.getActivePlayer());

		
		// check if should ask players about insurance
		if (theGame.canBuyInsurance() && !this.insurance) {
			controlBar.showInsuranceElements(() ->{
				this.insurance = true;
				this.update(theGame);
				return;
			});
		
		} else if (theGame.isGameOver) { // check if should display game over
			gc.setFont(new Font(32));
			gc.setTextAlign(TextAlignment.CENTER);
			String resultsText = "ROUND OVER\n Dealer Score: " + game.getDealerHand().getTotal();
			for (Player player : game.getPlayers()) {
				resultsText += "\n" + player.getName() + " Score: " + player.getHandTotal();
				resultsText += "\n" + player.getName() + " Winnings: " + player.getRoundWinnings();
			}
			gc.fillText(resultsText, canvas.getWidth() / 2, canvas.getHeight() / 2);
			controlBar.showBetElements();
			drawChips();
			this.insurance = false;
		}
	}

	private void drawChips() {
		gc.drawImage(chip1, 820, 454);
		gc.drawImage(chip1, 815, 454);
		gc.drawImage(chip5, 920, 454);
		gc.drawImage(chip5, 915, 454);
		gc.drawImage(chip10, 820, 554);
		gc.drawImage(chip10, 815, 554);
		gc.drawImage(chip25, 920, 554);
		gc.drawImage(chip25, 915, 554);
		gc.drawImage(chip100, 820, 654);
		gc.drawImage(chip100, 815, 654);
		gc.drawImage(chip500, 920, 654);
		gc.drawImage(chip500, 915, 654);
	}
}
