package view;

import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CardSprite {
	private Point2D position;
	private double rotation;
	private Card card;
	private Image img;
	private String outerFP;
	private String innerFP;
	private GraphicsContext g;
	private boolean hideCard;

	/**
	 * constructs the CardSprite class, which represent an image of a corresponding
	 * card
	 * 
	 * @param g:       the graphics context of the canvas to draw on
	 * @param x:       the x position of the card
	 * @param y:       the y position of the card
	 * @param rot:     the rotation of the card
	 * @param theCard: the card to represent
	 */
	public CardSprite(GraphicsContext g, double x, double y, double rot, Card theCard, boolean hideCard) {
		this.g = g;
		position = new Point2D(x, y);
		rotation = rot;
		card = theCard;
		innerFP = "blank.png";
		this.hideCard = hideCard;

		// determine base image
		switch (card.getSuit()) {
		case CLUBS:
			outerFP = "cCards";
			break;

		case SPADES:
			outerFP = "sCards";
			break;

		case HEARTS:
			outerFP = "hCards";
			break;

		case DIAMONDS:
			outerFP = "dCards";
			break;
		default:
			System.out.println("invalid suit");
			System.out.println(card.getSuit());
		}
	}

	/**
	 * draws the card onto the canvas given the canvas's graphics context
	 */
	public void draw() {
		try {
			if (hideCard)
				img = new Image(new FileInputStream("images/cardBack.png"));
			else
				img = new Image(new FileInputStream("images/" + outerFP + "/" + innerFP));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		g.drawImage(img, position.getX(), position.getY(), 122, 173);
		if (hideCard) {
			hideCard = false;
			return;
		}

		g.setFont(new Font(16));
		g.setTextAlign(TextAlignment.CENTER);
		String cardValue;
		if (card.getRank().getNum() <= 10 && card.getRank().getNum() > 1) {
			cardValue = "" + card.getRank().getValue();
		} else if (card.getRank().getNum() <= 11 && card.getRank().getNum() > 1) {
			cardValue = "J";
		} else if (card.getRank().getNum() <= 12 && card.getRank().getNum() > 1) {
			cardValue = "Q";
		} else if (card.getRank().getNum() <= 13 && card.getRank().getNum() > 1) {
			cardValue = "K";
		} else {
			cardValue = "A";
		}

		g.fillText(cardValue, position.getX() + 15, position.getY() + 19);

	}
}
