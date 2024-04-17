/**
 * A simple observable class use to notify observers of any state change in the
 * the observable. The observable class must extend this class OurObserverable.
 * 
 * @author Rick Mercer
 *
 */
package model;
import java.util.ArrayList;

public class OurObservable {
  ArrayList<OurObserver> observers = new ArrayList<>();
  
  /**
   * adds an observer to the observable's list
   * @param anObserver a instance of an Observer
   */
  public void addObserver(OurObserver anObserver) {
    observers.add(anObserver);
  }
 
  /**
   * notify the observers of the change within the observable
   * @param theObservable the observer that connects to the observable
   */
  public void notifyObservers(OurObservable theObservable) {
    for(OurObserver obs : observers) {
      obs.update(theObservable);
    }
  }
}
