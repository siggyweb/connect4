public class Board {
  private char[][] gameBoard;
  private boolean gameIsWon = false;
  private int connectN;

  public Board(int n) {
    gameBoard = new char[6][7];
    this.connectN = n;
  }

  public int getConnectN() {
    return this.connectN;
  }

  // method to return the state of the board as string for printing or parsing
  public String stateOfBoard() {
    String returnString = "";
    for (int row = 0; row < gameBoard.length; row++) {
      for (int col = 0; col < gameBoard[row].length; col++) {
        if (gameBoard[row][col] == 'r') {
          returnString += "| r ";
        } else if (gameBoard[row][col] == 'y') {
          returnString += "| y ";
        } else {
          returnString += "|   ";
        }
      }
      returnString += "|\n";
    }
    returnString += "  1   2   3   4   5   6   7\n";
    return returnString;
  }

  // Method to place token into board at specified column
  public void placeCounter(char token, int position, Player p) {
    boolean placed = false;
    for (int row = gameBoard.length - 1; row >= 0; row--) {
      if (!placed) {
        if (gameBoard[row][position - 1] != '\0') {
          // skip
          continue;
        } else {
          gameBoard[row][position - 1] = token;
          placed = true;
        }
      }
    }
    // Use recursion to get another random move if player/comp tries to play a full
    // column.
    if (placed == false) {
      placeCounter(token, p.getMove(), p);
    }
  }

  // method to check the whole board for horizontal win based on current player token
  private boolean horizontalWin(char token) {
    int count = 0;
    for (int row = 0; row < gameBoard.length; row++) {
      for (int col = 0; col < gameBoard[row].length; col++) {
        if (gameBoard[row][col] == token) {
          count = count + 1;
          if (count == connectN) {
            System.out.printf("Horizontal win, row %d col %d count: %d\n", row+1, col+1, count);
            return true;
          }
        } else {
          count = 0;
        }
      }
      //reset count for a new row
      count = 0;
    }
    return false;
  }

  // method to check the whole board for vertical win based on current player token
  private boolean verticalWin(char token) {
    int count = 0;
    for (int col = 0; col < gameBoard[0].length; col++) {
      for (int row = 0; row < gameBoard.length; row++) {
        if (gameBoard[row][col] == token) {
          count = count + 1;
          if (count == connectN) {
            System.out.printf("Vertical win, row: %d col: %d count: %d \n", row+1, col+1, count);
            return true;
          }
        } else {
          count = 0;
        }
      }
      //reset count for a new column
      count = 0;
    }
    return false;
  }

  // method to check for descending diagonal win
  private boolean descendingDiagonalWin(char token) {
    int count = 0;

    // iterate through every cell in the board
    for (int row = 0; row < gameBoard.length; row++) {
      for (int col = 0; col < gameBoard[0].length; col++) {
        count = 0;
        // for each cell iterate for a possible diagonal win from cell to bottom
        // right using offset "diag"
        try {
          for (int diag = 0; diag < connectN; diag++) { 
            if (gameBoard[row+diag][col+diag] == token) {
              count = count + 1;
              if (count == connectN) {
                System.out.printf("Diagonal desc win, row: %d, col: %d count%d\n", row+1, col+1, count);
                return true;
              }
            } 
            else {
              count = 0;
            }
          }
         count = 0;           
        } catch (ArrayIndexOutOfBoundsException e) {
          // Skip indices in array that do not exist as they cannot be winning moves.
          continue;
        }
        
      } // end of col loop
    } // end of row loop
    return false;
  }

  // method to check for ascending diagonal win
  private boolean ascendingDiagonalWin(char token) {
    int count = 0;

    // iterate through every cell in the board
    for (int row = gameBoard.length; row > 0; row--) {
      for (int col = gameBoard[0].length; col > 0; col--) {
        count = 0;
        // for each cell iterate for a possible diagonal win from cell to top
        // right using offset "diag"
        try {
          for (int diag = 0; diag < connectN; diag++) { 
            if (gameBoard[row+diag][col-diag] == token) {
              count = count + 1;
              if (count == connectN) {
                System.out.printf("Diagonal asc win, row: %d, col%d count%d\n", row+1, col+1, count);
                return true;
              }
            } 
            else {
              count = 0;
            }
            
          }
         count = 0;           
        } catch (ArrayIndexOutOfBoundsException e) {
          // Skip indices in array that do not exist as they cannot be winning moves.
          continue;
        }
        
      } // end of col loop
    } // end of row loop
    return false;
  }

  

  // method to check for all end game conditions. 
  //Returns false is game is over, true if not.
  public boolean advanceGame(char token) {
    //end game clauses
    if (horizontalWin(token)) {
      return false;
    } else if (verticalWin(token)) {
      return false;
    } else if (descendingDiagonalWin(token)) {
      return false;
    } else if (ascendingDiagonalWin(token)) {
      return false;
    } else {
      //continue game
      return true;
    }
  }

  // method that returns if game is won
  public boolean gameIsWon() {
    return this.gameIsWon;
  }

  // method to check for any valid moves left and return true if board full.
  public boolean boardIsFull() {
    for (int row = 0; row < gameBoard.length; row++) {
      for (int col = 0; col < gameBoard[row].length; col++) {
        if (gameBoard[row][col] == '\0') {
          return false;
        }
      }
    }
    return true;
  }

}