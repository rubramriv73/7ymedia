package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import card.Card;
import exceptions.ExceededScoreException;
import people.Dealer;
import people.Player;
import util.Menu;



/**
 * @author Rubén Ramírez Rivera
 *
 */
public class Game {

  private static final String GAME = "SPANISH BLACKJACK // SEVEN AND A HALF";
  private static final int MAX_ROUNDS = 5;
  private static Scanner s = new Scanner(System.in);

  private static String [] mainOptions = {"Player's Options", "Play", "Exit"};
  private static String [] playerOptions = {"Add Player", "Delete Player", "Show Players", "Back to main menu"};
  private static Menu mainMenu = new Menu(GAME,mainOptions);
  private static Menu playerMenu = new Menu("Player Options",playerOptions);

  private static int rounds = 0;
  private static double maxScore = -1;
  private static int maxRoundsWinned = -1;


  public static void main(String[] args) {


    var players = new ArrayList<Player>();
    var dealer = new Dealer();


    do {
      switch (mainMenu.pickOption()) {
        case 1: // Player's Options
          showPlayersMenu(players);
          break;

        case 2: // Play
          if (!(playersCreated(players))) {
            System.out.println("\n" + dealer.getNick().toUpperCase() + "is going to be the dealer os this game:");
            getPlayersOrder(players, dealer);
            showGame(rounds, players, dealer);
          } else {
            System.out.println("To play you need to create at least 1 player.");
          }

          break;

        default: // Exit
          System.out.println("\nSee you next time!!\n");
          return;
      }
    } while (true);

  }

  private static boolean playersCreated(ArrayList<Player> players) {
    return players.isEmpty();
  }

  public static void getPlayersOrder(ArrayList<Player> players, Dealer dealer){
    resetRound(players, dealer);
    System.out.println("\nGiving a card to each player to set the order: ");
    try {
      for (int i = 0; i < players.size(); i++) {
        System.out.println(getPlayerName(getPlayer(players, i)).toUpperCase() + " turn: ");
        getPlayer(players, i).setCards(dealer.giveCard());
        showPlayersCards(players, i);
      }
      Collections.sort(players);
      System.out.println("Players order for this game: ");
      showPlayers(players);

    } catch (ExceededScoreException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * @param rounds
   * @param players
   * @param dealer
   * @return
   */
  private static void showGame(int rounds, ArrayList<Player> players, Dealer dealer) {
    do {
      resetRound(players, dealer);
      System.out.println(("\nSTART OF ROUND" + (rounds + 1) + "\n"));
      playRound(players, dealer);            
      try {
        giveRound(players);
      } catch (ExceededScoreException e) {
        System.out.println(e.getMessage());
      }
      rounds++;

    } while (rounds < MAX_ROUNDS && askToContinue());

    getWinner(players);
  }

  /**
   * @param players
   * @param dealer
   */
  private static void resetRound(ArrayList<Player> players, Dealer dealer) {
    dealer.resetDeck();
    for (Player player : players) {
      player.resetScore();
      player.resetCards();
    }
  }

  /**
   * @param players
   * @param dealer
   */
  private static void playRound(ArrayList<Player> players, Dealer dealer) {
    for (int i = 0; i < players.size(); i++) {
      System.out.println("\n" + getPlayerName(getPlayer(players, i)).toUpperCase() + " turn:");

      do {
        try {
          getPlayer(players, i).setCards(dealer.giveCard());
          showPlayersCards(players, i);
        } catch (ExceededScoreException e) {
          showPlayersCards(players, i);
          System.out.println(e.getMessage()); 
          break;
        }

      } while (!(askForACard().equalsIgnoreCase("n")));


      getMaxScore(players,i);

    }
  }

  /**
   * @param players
   * @throws ExceededScoreException 
   */
  private static void giveRound(ArrayList<Player> players) throws ExceededScoreException {
    System.out.println("\nMax Score of the round: " + maxScore);
    System.out.println("Who wins this round?...");
    if (maxScore == 0) {
      throw new ExceededScoreException("All players exceeded the max score. Nobody wins!!");
    }
    for (Player player : players) {
      if (player.getScore() == maxScore) {
        player.setRounds();
        System.out.print(getPlayerName(player).toUpperCase() + " ");
      }

    }
    System.out.println("WINS");
  }

  /**
   * @param players
   * @param i
   */
  private static void showPlayersCards(ArrayList<Player> players, int i) {
    for (Card card : getPlayer(players, i).getCards()) {
      System.out.println(card);
    }
  }


  /**
   * @param players
   * @param i
   * @return
   */
  private static Player getPlayer(ArrayList<Player> players, int index) {
    return players.get(index);
  }

  /**
   * @param player
   * @return
   */
  private static String getPlayerName(Player player) {
    return player.getNick();
  }

  /**
   * @param players
   */
  private static void getMaxScore(ArrayList<Player> players, int index) {
    if (getPlayer(players, index).getScore() > maxScore && getPlayer(players, index).getScore() <= 7.5) {
      maxScore = getPlayer(players, index).getScore();

    }

  }

  /**
   * @param players
   */
  private static void getWinner(ArrayList<Player> players) {
    System.out.println("\nWinner of the game...");
    for (Player player : players) {
      maxRoundsWinned = (player.getRounds() > maxRoundsWinned ? player.getRounds() : maxRoundsWinned);
    }

    for (Player player : players) {
      if (player.getRounds() == maxRoundsWinned) {
        System.out.print(getPlayerName(player).toUpperCase() + " ");
      }
    }
    System.out.println("\n");
  }


  // Static Methods

  private static void showPlayersMenu(ArrayList<Player> players) {
    boolean backToMenu;
    do {
      backToMenu = true;
      switch (playerMenu.pickOption()) {
        case 1: // Add Player
          addPlayer(players);
          break;

        case 2: case 3:
          if (!(playersCreated(players))) {
            switch (playerMenu.pickOption()) {
              case 2: // Delete Player
                removePlayer(players);
                break;

              case 3: // Show Players
                showPlayers(players);
                break;

            }

          } else {
            System.out.println("Haven't created players yet");

          }

          break;



        default:
          System.out.println("\nGoing back to main menu\n");
          backToMenu = false;
          break;
      }
    } while (backToMenu);
  }

  /**
   * @param players
   */
  private static void addPlayer(ArrayList<Player> players) {
    players.add(new Player(askPlayerName()));
    System.out.println("Added Player\n");
  }

  /**
   * @param players
   */
  private static void removePlayer(ArrayList<Player> players) {
    if (players.remove(new Player(askId("\nGive us the Id of the player to delete:")))) {
      System.out.println("Deleted Player\n");

    } else {
      System.out.println("Couldn't delete the player. Incorrect Id.");
    }

  }

  /**
   * 
   * @param players
   */
  private static void showPlayers(ArrayList<Player> players) {
    for (Player player : players) {
      System.out.println(player);

    }

  }

  public static String askPlayerName() {
    String name;
    do {
      System.out.println("\nGive the name of the player");
      name = s.nextLine();

    } while (name.equals(""));

    return name;
  }

  public static String askForACard() {
    String answer;
    do {
      System.out.println("Need a card??(Y/N)");
      answer = s.nextLine();
    } while (!(answer.equalsIgnoreCase("y")) && !(answer.equalsIgnoreCase("n")));

    return answer;
  }

  public static int askId(String msg) {
    do {
      try {
        System.out.println(msg);
        return Integer.parseInt(s.nextLine());
      } catch (NumberFormatException e) {
        System.out.println("Please give us a correct id");
      }
    } while (true);
  }

  public static boolean askToContinue() {
    String answer;
    boolean continueRound = true;
    do {
      System.out.println("Do you want to continue playing??(Y/N)");
      answer = s.nextLine();
    } while (!(answer.equalsIgnoreCase("y")) && !(answer.equalsIgnoreCase("n")));
    if (answer.equalsIgnoreCase("n")) {
      continueRound = false;

    }

    return continueRound;
  }


}
