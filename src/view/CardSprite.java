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

    private GraphicsContext g;
    public CardSprite(GraphicsContext g, double x, double y, double rot, Card theCard) {
    	this.g = g;
        position = new Point2D(x, y);
    	rotation = rot;
    	card = theCard; //theCard;

        // determine base image
        switch (card.getSuit()) {
            case CLUBS:
                try {
                    img = new Image(new FileInputStream("images/cCard.png"));
                } catch (FileNotFoundException e){
                    System.out.println("Cant find club card image");
                }
                break;

            case SPADES:
                try {
                    img = new Image(new FileInputStream("images/sCard.png"));
                } catch (FileNotFoundException e){
                    System.out.println("Cant find club card image");
                }
                break;

            case HEARTS:
                try {
                    img = new Image(new FileInputStream("images/hCard.png"));
                } catch (FileNotFoundException e){
                    System.out.println("Cant find club card image");
                }
                break;

            case DIAMONDS:
                try {
                    img = new Image(new FileInputStream("images/dCard.png"));
                } catch (FileNotFoundException e){
                    System.out.println("Cant find club card image");
                }
                break;
            default:
                System.out.println("invalid suit");
                System.out.println(card.getSuit());
        }
    }
    
    public void draw() {
        g.drawImage(img, position.getX(), position.getY(), 122, 173);
        g.setFont(new Font(16));
        g.setTextAlign(TextAlignment.CENTER);
        g.fillText(Integer.toString(card.getRank().getValue()), position.getX() + 15, position.getY() + 19);
    }
}
