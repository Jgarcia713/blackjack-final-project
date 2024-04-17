package view;

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
    public CardSprite(GraphicsContext g, double x, double y, double rot, Card theCard) {
    	this.g = g;
        position = new Point2D(x, y);
    	rotation = rot;
    	card = theCard;
    	innerFP = "blank.png";

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
    
    public void draw() {
    	try {
			img = new Image(new FileInputStream("images/" + outerFP + "/" + innerFP));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        g.drawImage(img, position.getX(), position.getY(), 122, 173);
        g.fillText(Integer.toString(card.getRank().getValue()), position.getX() + 11, position.getY() + 18);
    }
}
