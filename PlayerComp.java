import java.util.Random;

public class PlayerComp extends Player { 
Random r = new Random();
  
  public PlayerComp(String name, char token) {
    super(name, token);
    this.type = "Computer";
  }


  //Override method for getMove(), return a column number between 1 and 7.
  @Override
  public int getMove() {
    System.out.println("Computer is thinking about its next move...\n");
    int randomMove = r.nextInt(1,8);
    return randomMove;
  }
}