package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * This class represents the login window for Blackjack GUI. Users cannot
 * progress until they log in.
 */
public class LoginPane extends Pane {

	private TextField userField;
	private PasswordField passField;
	private Button login;
	private Runnable loginSuccessListener; // A runnable anonymous function passed in to resume the main GUI

	/**
	 * Create the login pane
	 * 
	 * @param canvas     - A canvas created in BlackjackGUI
	 * @param background - the background image for the GUI
	 */
	public LoginPane(Canvas canvas, Image background) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

		this.getChildren().add(canvas);
		gc.setGlobalAlpha(.6);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.LIGHTBLUE);
		gc.setGlobalAlpha(1);
		gc.fillRect(300, 150, 400, 220);
		gc.setFill(Color.BLACK);

		initializePane();
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
				if (loginSuccessListener != null) { // Run the anonymous function passed in
					loginSuccessListener.run();
				}
			}
		});
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
	private void initializePane() {
		Label title = new Label("Login or Create a New Account");
		title.setStyle("-fx-font-size: 25; -fx-font-weight: bold;");
		title.setLayoutX(315);
		title.setLayoutY(160);
		this.getChildren().add(title);

		Label username = new Label("Username");
		username.setStyle("-fx-font-size: 18;");
		username.setLayoutX(330);
		username.setLayoutY(210);
		this.getChildren().add(username);
		userField = new TextField();
		userField.setLayoutX(420);
		userField.setLayoutY(213);
		userField.setPrefWidth(200);
		this.getChildren().add(userField);

		Label password = new Label("Password");
		password.setStyle("-fx-font-size: 18;");
		password.setLayoutX(330);
		password.setLayoutY(260);
		this.getChildren().add(password);
		passField = new PasswordField();
		passField.setLayoutX(420);
		passField.setLayoutY(263);
		passField.setPrefWidth(200);
		this.getChildren().add(passField);

		login = new Button("Login!");
		login.setStyle("JA-fx-font-size: 15; -fx-font-weight: bold;");
		login.setLayoutX(465);
		login.setLayoutY(310);
		this.getChildren().add(login);
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
