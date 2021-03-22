package people;

import card.Card;
import deck.Deck;

/**
 * @author Rubén Ramírez Rivera
 *
 */
public class Dealer extends Person{
  
  // Static variables
  private final static String[] dealersNames = {"Susana Oria", "Elsa Pato", "Mario Neta"};
  
  // Variables 
  Deck deck;
  
  // Constructor
  public Dealer() {
    super(getDealersName());
    
    this.deck = new Deck();
  }
  
  // Methods
  public Card giveCard() {
    return this.deck.getCard();
  }
  
  public void resetDeck() {
    this.deck.reset();
  }

  // Static methods
  static String getDealersName() {
    return dealersNames[(int)(Math.random() * 2)];
  }
  
}
