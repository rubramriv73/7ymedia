package people;

import java.util.ArrayList;
import card.Card;
import exceptions.ExceededScoreException;

/**
 * @author Rubén Ramírez Rivera
 *
 */
public class Player extends Person implements Comparable<Player>{
  
  // Static variables
  private static final double INITIAL_SCORE = 0;
  private static final double MAX_SCORE = 7.5;
  private static int defaultID = 0;
  
  // Variables
  private int id;
  private int rounds;
  private double score;
  private ArrayList<Card> cards;
  
  // Constructor
  public Player(String nick) {
    super(nick);
    this.rounds = (int)INITIAL_SCORE;
    this.score = INITIAL_SCORE;
    setId();
    this.cards = new ArrayList<>();
  }
  
  public Player(int id) {
    super("");
    setId(id);
  }

  /**
   * @return the id
   */
  public int getId() {
    return id;
  }

  private void setId() {
    defaultID++;
    this.id = defaultID;
  }
  /**
   * @param id the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the coins
   */
  public int getRounds() {
    return rounds;
  }

  /**
   * @param coins the coins to set
   */
  public void setRounds() {
    this.rounds++;
  }

  /**
   * @return the score
   */
  public double getScore() {
    return score;
  }
  
  /**
   * @param score the score to set
   * @throws ExceededScoreException 
   */
  private void setScore(Card card) throws ExceededScoreException {
    this.score += card.getPoints();
    if (this.score > MAX_SCORE) {
      this.resetScore();
      throw new ExceededScoreException("You exceeded the max score");
    }
  }
  
  public void resetScore() {
    this.score = INITIAL_SCORE;
  }
  
  public void resetCards() {
    this.cards.clear();
  }

  /**
   * @return the cards
   */
  public ArrayList<Card> getCards() {
    return cards;
  }

  /**
   * @param cards the cards to set
   * @throws ExceededScoreException 
   */
  public void setCards(Card card) throws ExceededScoreException {
    this.cards.add(card);
    setScore(card);
  }
  
  @Override
  public int compareTo(Player otherPlayer) {
    return Double.compare(otherPlayer.getScore(), this.getScore());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Player other = (Player) obj;
    if (id != other.id)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Player " + id + ":\n -Name -> " + this.getNick() + "\n";
  }
  
}
