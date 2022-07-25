public abstract class Player {
  private String name;
  private char token;
  public String type;

  public Player(String name, char token) {
    this.name = name;
    this.token = token;
  }

  //Getters for Player fields
  public String getName(){
    return this.name;
  }

  public char getToken(){
    return this.token;
  }

  public String getType() {
    return this.type;
  }

  //Abstract method to be implemented by 2 child classes for valid move.
  public abstract int getMove();
}