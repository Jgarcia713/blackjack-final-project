package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * A collection of animations that can be used on any JavaFX node.
 * Calling any of these methods will immediately play the associated animation on the node
 */
public final class AnimationLibrary {

    /**
     * Private constructor so that the class cannot be instantiated
     */
    private AnimationLibrary() {};
    private static final ScaleTransition scale =  new ScaleTransition();;

    public static void scaleUp(Node node) {
        ScaleTransition scale = new ScaleTransition();
        scale.setDuration(Duration.millis(100));
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(1.1);
        scale.setToY(1.1);
        scale.setInterpolator(Interpolator.EASE_BOTH);
        scale.setNode(node);
        scale.play();
    }
    public static void scaleDown(Node node) {
        ScaleTransition scale = new ScaleTransition();
        scale.setDuration(Duration.millis(100));
        scale.setFromX(1.1);
        scale.setFromY(1.1);
        scale.setToX(1.0);
        scale.setToY(1.0);
        scale.setInterpolator(Interpolator.EASE_BOTH);
        scale.setNode(node);
        scale.play();
    }

    public static void moveAndScale(Point2D moveTo, double scaleTo) {

    }

    public static void textTest(Text text) {
        final String content = "Lorem ipsum";
        //final Text text = new Text(10, 20, "");

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                text.setText(content.substring(0, n));
            }

        };

        animation.play();
    }
}
