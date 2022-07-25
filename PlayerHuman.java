import java.io.*;

public class PlayerHuman extends Player {

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
  public PlayerHuman(String name, char token) {
    super(name, token);
    this.type = "Human";
  }

  //Override method for getMove(), parse a move from standard input, System.in.
  @Override
  public int getMove() {
    int intMove = 0;
    System.out.println("Please enter a column to place a token.");
    try {
      String playerMove = br.readLine();
      intMove = Integer.parseInt(playerMove);
      return intMove;
    } catch (IOException e) {
      System.out.println("IO exception has occurred!");
    }
    return intMove;
    
  }

  //method to close BR when game is over.
  public void closeBR() {
    try {
      br.close();
    }
    catch (IOException e) {
      System.out.println("An IO exception has occurred while trying to close the stream.");
    }
  }
}