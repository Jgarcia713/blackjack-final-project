package view;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.PlayerAccount;
import model.PlayerAccountCollection;

/**
 * This class represents the login window for Blackjack GUI. Users cannot
 * progress until they log in.
 */
public class LoginPane extends Pane {

	private PlayerAccountCollection playerCollection;
	private String finalUsername = "";
	private TextField userField;
	private PasswordField passField;
	private Button login, startGame, newAccount;
	private Runnable loginSuccessListener; // A runnable anonymous function passed in to resume the main GUI
	private Group loginElements, userStats;
	private GraphicsContext gc;
	private Canvas canvas;
	private Image background;

	/**
	 * Create the login pane
	 * 
	 * @param canvas     - A canvas created in BlackjackGUI
	 * @param background - the background image for the GUI
	 */
	public LoginPane(Canvas canvas, Image background, PlayerAccountCollection playerCollection) {
		this.playerCollection = playerCollection;
		gc = canvas.getGraphicsContext2D();
		this.canvas = canvas;
		this.background = background;
		gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

		this.getChildren().add(canvas);
		gc.setGlobalAlpha(.6);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.LIGHTBLUE);
		gc.setGlobalAlpha(1);
		gc.fillRect(300, 150, 400, 220);
		gc.setFill(Color.BLACK);

		initializeElements();
		setEventHandlers();
	}

	/**
	 * Set the event handlers for the the different attributes
	 */
	private void setEventHandlers() {
		userField.setOnAction(e -> {
			if (userField.getText().length() > 0)
				passField.requestFocus();
		});
		passField.setOnAction(e -> {
			if (passField.getText().length() > 0)
				login.requestFocus();
		});
		login.setOnAction(e -> {
			String username = userField.getText();
			String password = passField.getText();
			if (username.length() > 0 && password.length() > 0) {
				if (playerCollection.checkForUsername(username)) {
					// username exists
					if (playerCollection.checkPassword(username, password)) {
						// password is correct
						finalUsername = username;
						this.showUserStats();
					} else {
						// password is incorrect
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Account exists but password is incorrect");
						alert.showAndWait();
					}

				} else {
					// username doesn't exist
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Player Account does not exist");
					alert.showAndWait();
				}
			}
		});

		startGame.setOnAction(e -> {
			if (loginSuccessListener != null) { // Run the anonymous function passed in
				loginSuccessListener.run();
			}
		});

		newAccount.setOnAction(e -> {
			String username = userField.getText();
			String password = passField.getText();
			// add Player
			boolean isComplete = playerCollection.addPlayer(username, password);
			if (isComplete) {
				// new player created
				finalUsername = username;
				this.showUserStats();
			} else {
				// username already taken
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Player username is already taken");
				alert.showAndWait();
			}
		});
	}

	private void showUserStats() {
		gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setGlobalAlpha(.6);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setGlobalAlpha(1);
		loginElements.setVisible(false);

		PlayerAccount playerAccount = playerCollection.getPlayer(finalUsername);
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add((playerAccount.getHighestBalance() + "").length());
		values.add((playerAccount.getLowestBalance() + "").length());
		values.add((playerAccount.getBiggestBet() + "").length());
		values.add((playerAccount.getBiggestAmountWon() + "").length());
		int max = Collections.max(values);

		Label username = new Label("Welcome " + playerAccount.getUsername() + ",\nWould you care to play a game?");
		username.setLayoutX(10);
		username.setLayoutY(0);
		username.setStyle("-fx-font-size: 46; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label stats = new Label("Your Play Stats:");
		stats.setLayoutX(10);
		stats.setLayoutY(170);
		stats.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label balance = new Label(
				String.format("%-20s %" + max + ".1f", "Current Balance:", playerAccount.getBalance()));
		balance.setLayoutX(10);
		balance.setLayoutY(220);
		balance.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label highestBalance = new Label(
				String.format("%-20s %" + max + ".1f", "Highest Balance:", playerAccount.getHighestBalance()));
		highestBalance.setLayoutX(10);
		highestBalance.setLayoutY(270);
		highestBalance.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label lowestBalance = new Label(
				String.format("%-20s %" + max + ".1f", "Lowest Balance:", playerAccount.getLowestBalance()));
		lowestBalance.setLayoutX(10);
		lowestBalance.setLayoutY(320);
		lowestBalance.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label biggestBet = new Label(
				String.format("%-20s %" + max + ".1f", "Biggest Bet:", playerAccount.getBiggestBet()));
		biggestBet.setLayoutX(10);
		biggestBet.setLayoutY(370);
		biggestBet.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label biggestAmountWon = new Label(
				String.format("%-20s %" + max + ".1f", "Biggest Amount Won:", playerAccount.getBiggestAmountWon()));
		biggestAmountWon.setLayoutX(10);
		biggestAmountWon.setLayoutY(420);
		biggestAmountWon.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label longestWinStreak = new Label(String.format("%-20s %" + max + ".1f", "Longest Win Streak:",
				playerAccount.getLongestWinStreak() * 1.0));
		longestWinStreak.setLayoutX(10);
		longestWinStreak.setLayoutY(470);
		longestWinStreak.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		Label currentWinStreak = new Label(String.format("%-20s %" + max + ".1f", "Current Win Streak:",
				playerAccount.getCurrentWinStreak() * 1.0));
		currentWinStreak.setLayoutX(10);
		currentWinStreak.setLayoutY(520);
		currentWinStreak.setStyle("-fx-font-size: 34; -fx-text-fill: white; -fx-font-family: Consolas;");

		userStats.getChildren().addAll(balance, highestBalance, lowestBalance, biggestBet, biggestAmountWon,
				longestWinStreak, currentWinStreak, stats, username);
		userStats.setVisible(true);
	}

	/**
	 * Set an anonymous function to run upon logging in. This allows the main GUI to
	 * resume displaying
	 * 
	 * @param listener - an anonymous function that ends this scene when ran
	 */
	public void setOnLoginSuccessListener(Runnable listener) {
		this.loginSuccessListener = listener;
	}

	/**
	 * Initialize all the different GUI elements for displaying a log in window
	 */
	private void initializeElements() {
		Label title = new Label("Login or Create a New Account");
		title.setStyle("-fx-font-size: 25; -fx-font-weight: bold;");
		title.setLayoutX(315);
		title.setLayoutY(160);

		Label username = new Label("Username");
		username.setStyle("-fx-font-size: 18;");
		username.setLayoutX(330);
		username.setLayoutY(210);
		userField = new TextField();
		userField.setLayoutX(420);
		userField.setLayoutY(213);
		userField.setPrefWidth(200);

		Label password = new Label("Password");
		password.setStyle("-fx-font-size: 18;");
		password.setLayoutX(330);
		password.setLayoutY(260);
		passField = new PasswordField();
		passField.setLayoutX(420);
		passField.setLayoutY(263);
		passField.setPrefWidth(200);

		login = new Button("Login!");
		login.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		login.setLayoutX(395);
		login.setLayoutY(310);

		newAccount = new Button("Create new account");
		newAccount.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");
		newAccount.setLayoutX(475);
		newAccount.setLayoutY(310);

		loginElements = new Group();
		loginElements.getChildren().addAll(title, username, userField, password, passField, login, newAccount);
		this.getChildren().add(loginElements);

		startGame = new Button("Start Game!");
		startGame.setLayoutX(465);
		startGame.setLayoutY(700);
		startGame.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");

		userStats = new Group();
		userStats.getChildren().addAll(startGame);
		userStats.setVisible(false);
		this.getChildren().add(userStats);

	}

	/**
	 * Return the username to the main GUI
	 * 
	 * @return a string of the username
	 */
	public String getUsername() {
		return userField.getText();
	}

}
