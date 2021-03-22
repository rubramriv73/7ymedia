package people;

/**
 * @author Rubén Ramírez Rivera
 *
 */
public class Person {
  
  //Variables
  private String nick;

  public Person(String nick) {
    setNick(nick);
    
  }

  /**
   * @return the nick
   */
  public String getNick() {
    return nick;
  }

  /**
   * @param nick the nick to set
   */
  private void setNick(String nick) {
    this.nick = nick;
  }
  
  
}
