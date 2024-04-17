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

    public CardSprite(double x, double y, double rot, Card theCard) throws FileNotFoundException {
    	position = new Point2D(x, y);
    	rotation = rot;
    	card = new Card(Rank.ACE, Suit.CLUBS); //theCard;
    	
    	if(card.getSuit() == Suit.CLUBS) {
    		img = new Image(new FileInputStream("images/cCard.png"));
    	}
    }
    
    public void draw(GraphicsContext g) {
    	g.drawImage(img, position.getX(), position.getY(), 122, 173);
    }
}
