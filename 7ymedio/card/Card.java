package card;

/**
 * Card Class
 * 
 * In this class we are going to create each card for our game.
 * 
 * @author Rubén Ramírez Rivera
 *
 */
public class Card {
  
  // Variables
  private String suit;
  private String value;
  private double points;
  
  // Constructor
  
  /**
   *  Card Method
   *  
   *  This is the constructor of our card class
   *  
   * @param suit
   * @param value
   * @param points
   */
  public Card(String suit, String value, double points) {
    setSuit(suit);
    setValue(value);
    setPoints(points);
    
  }

  // Getters & Setters
  
  /**
   * getSuit Method
   * 
   * This method returns the suit of the actual card
   * 
   * @return the suit
   */
  public String getSuit() {
    return suit;
  }

  /**
   * @param suit the suit to set
   */
  private void setSuit(String suit) {
    this.suit = suit;
  }

  /**
   * getName Method
   * 
   * This method returns the name of the actual card
   * 
   * @return the name
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value the name to set
   */
  private void setValue(String value) {
    this.value = value;
  }

  /**
   * getValue Method
   * 
   * This method returns the value of the actual card
   * 
   * @return the value
   */
  public double getPoints() {
    return points;
  }

  /**
   * @param value the value to set
   */
  private void setPoints(double points) {
    this.points = points;
  }

  @Override
  public String toString() {
    return "Card: \tSuit=" + suit + " \n\tValue=" + value + " \n\tPoints=" + points + "\n";
  }
  
  
  
}
