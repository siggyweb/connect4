import java.util.*;


public class GameLogic {
  // Game logic is the main class which will run the game, the game is started
  // by accessing playGame().
  private Board board;
  private ArrayList<Player> players;
  private String gameWinner;
  private boolean stalemate = false;

  // GameLogic constructor creates a new connect4 board, and populate a list of
  // players.
  public GameLogic() {
    board = new Board(4);
    players = new ArrayList<>();
    players.add(new PlayerHuman("Tom", 'r'));
    players.add(new PlayerComp("Mr. X", 'y'));
  }

  // Method to produce announcement for players starting the game
  private String declarePlayers(ArrayList<Player> playerList) {
    String returnString = "";
    for (Player p : playerList) {
      returnString += "Player: " + p.getName() + " has entered the game with token: " + p.getToken() + ".\n";
    }
    return returnString;
  }

  // Method to declare winner
  private String declareWinner(String winner) {
    String victoryStr = "The winner is " + winner + ", congratulations!";
    return victoryStr;
  }

  // Method to check for valid input matching a column number
  private boolean moveIsValid(int col) {
    if (col >= 1 && col < 8) {
      return true;
    } else {
      return false;
    }
  }

  // Method to begin and run core game control flow.
  public void playGame() {
    System.out.println("\nWelcome to Connect " + board.getConnectN());
    System.out.println(declarePlayers(players));
    System.out.println("To play the game type in the number of the column you want to drop you counter in");
    System.out.println("A player wins by connecting " + board.getConnectN()
        + " counters in a row - vertically, horizontally or diagonally\n");

    System.out.println(board.stateOfBoard());

    // Loop to continue running game giving all players a turn until there is a
    // winner or the board runs out of free spaces.
    while (!board.gameIsWon() && stalemate == false) {
      for (Player p : players) {
        try {
          int move = p.getMove();
          // hold control flow until player/comp makes a move that is a valid column
          // number.
          while (!moveIsValid(move)) {
            System.out.println("Out of bounds. Please a valid column number.");
            move = p.getMove();
          }

          // Move is now a valid column number, so token is added to the board.
          board.placeCounter(p.getToken(), move, p);

          // Display new board state
          System.out.println(board.stateOfBoard());

        } catch (NumberFormatException e) {
          System.out.println("Not a number. Please enter a valid integer between one and 7\n");
        } catch (ArrayIndexOutOfBoundsException e2) {
          System.out.println("Out of bounds. Please a valid column number.\n");
        }

        // Check for a stalemate end condition
        if (board.boardIsFull()) {
          System.out.println("The board is full, the game is a stalemate.");
          stalemate = true;
        }

        // Check for a win condition here and act accordingly
        boolean progressGame = board.advanceGame(p.getToken());
        if (progressGame == false) {
          System.out.println(this.declareWinner(p.getName()));
          this.gameWinner = p.getName();
          break;
        }

      } // end of for each
    } // end of while loop

    // Game is over so close the BR, first player in players is always the human
    Player p1 = players.get(0);
    ((PlayerHuman) p1).closeBR();
  }

}