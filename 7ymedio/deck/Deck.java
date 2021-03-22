package deck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import card.Card;

/**
 * @author Rubén Ramírez Rivera
 *
 */
public class Deck {
  
  // Final variables
  private static final String [] SUITS = {"Golds","Cups","Swords","Clubs"};
  private static final String [] CARDS_VALUES = {"As","Two","Three","Four","Five","Six","Seven","Jack","Horse","King"};
  private static final double MIN_POINTS = 0.5;
  private static final double MAX_POINTS = 7;
  
  // Static variables
  private static HashMap<String,Double> valuesWithPoints;
  
  // Variables
  private ArrayList<Card> cards;
  
  // Constructor
  
  /**
   * 
   */
  public Deck() {
    this.cards = new ArrayList<>();
    
    setValuesWithPoints();
    this.cards.addAll(List.of(setCards()));
  }
  
  public int shuffle() {
    return (int)(Math.random() * this.cards.size());
  }
  
  public Card getCard() {
    Card card = this.cards.get(this.shuffle());
    this.cards.remove(card);
    return card;
  }
  
  public void reset() {
    this.cards.clear();
    this.cards.addAll(List.of(setCards()));
  }
  
  //Static Methods
  /**
   * 
   */
  private static void setValuesWithPoints() {
    valuesWithPoints = new HashMap<>();
    
    for (int j = 0; j < CARDS_VALUES.length; j++) {
      if((j + 1) > MAX_POINTS) {
        valuesWithPoints.put(CARDS_VALUES[j], MIN_POINTS);
        
      } else {
        valuesWithPoints.put(CARDS_VALUES[j], (double)(j + 1));
        
      }
    }
  }
  
  /**
   * 
   * @return
   */
  private static Card[] setCards() {
    Card[] cards = new Card[CARDS_VALUES.length * SUITS.length];
    int pos = 0;
    
    for (String suit : SUITS) {
      for (String value : valuesWithPoints.keySet()) {
        cards[pos] = new Card(suit, value, valuesWithPoints.get(value));
        pos++;
      }
    }
    return cards;
  }
  
}
