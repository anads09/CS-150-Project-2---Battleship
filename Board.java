/*
Austin Nadler
CS150-002
Program #2
10/05/2018
*/
package cs150proj02;
import java.util.InputMismatchException;
import java.util.Random;

public class Board {
    private int boardSize;
    private int numShips;
    private int numShipsSunken;
    private Space [][] board;
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 10;
    private static final String HIT = "Hit!";
    private static final String MISS = "Miss!";
    private static final String NEAR_MISS = "Near miss!";
    
    public Board() {
        boardSize = 8;
        numShips = 5;
        numShipsSunken = 0;
    }   
    
    public void initBoard(int size) {
        for(int row = 0; row < size - 1; row++) {
            for(int col = 0; col < size - 1; col++) {
                board[row][col] = new Space();
            }
        }
    }
    
    public int getBoardSize() {
        return boardSize;
    }
    
    public void setBoardSize(int rows, int columns){
        try {
            if (rows != columns){
                throw new InputMismatchException("Error: Board isn't a square.");
            }
            if (rows < MIN_SIZE || rows > MAX_SIZE || columns < MIN_SIZE || columns > MAX_SIZE ) {
                throw new IndexOutOfBoundsException("Error: Invalid number of rows / columns. (" + MIN_SIZE + "-" + MAX_SIZE + ")");
            } else {
                boardSize = (rows + columns) / 2;  
                board = new Space [boardSize][boardSize];
                for(int row = 0; row < boardSize; row++) {
                    for(int col = 0; col < boardSize; col++){
                        board[row][col] = new Space();
                    }
                }
            }
                
        } catch (InputMismatchException ex) {
            System.out.print(ex.getMessage());
            System.exit(1);
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }      
        
    }
    // FOR TESTING
    public String getShipLocations() {
        String locations = "";
        for(int row = 0; row < boardSize - 1; row++) {
            for(int col =  0; col < boardSize - 1; col++) {
                if(board[row][col].doesSpaceHaveShip() == true) {
                    locations += "(" + row + ", " + col + ")";                    
                }
            }
        }
        return locations;
    }
    
    // getter not needed for numShips
    public void setNumShips(int numShips) {
        try {
            if (numShips > 0 && numShips <= boardSize*boardSize) {
                this.numShips = numShips;
            } else {
                throw new InputMismatchException("Error: Number of ships must be between 1 and board size ^ 2");
            }                    
                    
        }catch (InputMismatchException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);
        }
    }
    
    public boolean hasSpaceBeenShotAt(int row, int col) {
        if(!(board[row][col].getValue().equals(Space.BLANK))){
            return true;
        } else { 
            return false;
        }
    }
    
    public boolean didILose() {
        if (numShipsSunken >= numShips) {
            return true;
        } else {
            return false;
        }
    }
    
    public String tryShot(int row, int col) {  
        String result = "";
        try {
               
            if(row < 0 || row > boardSize - 1 || col < 0 || col > boardSize - 1) {                
                throw new IndexOutOfBoundsException("Error: Shot was out of bounds.");
            }
        } catch (IndexOutOfBoundsException ex){
           System.out.println(ex.getMessage());
           System.exit(1);
        }
        
        if (board[row][col].doesSpaceHaveShip() == true) {
                board[row][col].setValueToHIT();
                board[row][col].destroyShip();
                numShipsSunken++; 
                result = HIT;
        }
        
        // If space is in top left corner only check below and to the right 
        else if(row == 0 && col == 0){
            if (board[row + 1][col].doesSpaceHaveShip() == true || board[row][col + 1].doesSpaceHaveShip() == true) {
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }  
        // If space is in top right corner only check to the left and below it    
        } else if (row == 0 && col == boardSize - 1) {
            if(board[row + 1][col].doesSpaceHaveShip() == true || board[row][col - 1].doesSpaceHaveShip() == true){
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }
        // If space is in bottom left corner, only check above and to the right
        } else if(row == boardSize - 1 && col == 0) {
            if (board[row - 1][col].doesSpaceHaveShip() == true || board[row][col + 1].doesSpaceHaveShip() == true) {
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                return MISS;
            }
        // If space is in bottom right corner only check to the left and above
        } else if (row == boardSize - 1 && col == boardSize - 1) {
            if(board[row - 1][col].doesSpaceHaveShip() == true || board[row][col - 1].doesSpaceHaveShip() == true) {
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }
        // if space is on left edge but not a corner only check above, below, and to the right
        } else if(col == 0  && row != 0 && row != boardSize - 1) {
            if(board[row][col + 1].doesSpaceHaveShip() == true || board[row - 1][col].doesSpaceHaveShip() == true || board[row + 1][col].doesSpaceHaveShip() == true) {
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }
        // If space is on bottom only check right left and above
        } else if (row == boardSize - 1) {
            if(board[row - 1][col].doesSpaceHaveShip() == true || board[row][col - 1].doesSpaceHaveShip() == true || board[row][col +1].doesSpaceHaveShip() == true) {
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }
        // If space is on right edge only check above below and left
        } else if (col == boardSize - 1) {
            if(board[row][col - 1].doesSpaceHaveShip() == true || board[row - 1][col].doesSpaceHaveShip() == true || board[row + 1][col].doesSpaceHaveShip() == true) {
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }
        // If space in on the top but not on edge check below left and right
        } else if (row == 0 && col != 0 && col != boardSize - 1) {
            if(board[row + 1][col].doesSpaceHaveShip() == true || board[row][col - 1].doesSpaceHaveShip() == true || board[row][col + 1].doesSpaceHaveShip()) {
                board[row][col].setValueToNEAR();
                result = NEAR_MISS;
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }
        }
        
        else if (board[row - 1][col].doesSpaceHaveShip() == true || board[row + 1][col].doesSpaceHaveShip() == true 
                    || board[row][col - 1].doesSpaceHaveShip() == true || board[row][col + 1].doesSpaceHaveShip() == true) {
                board[row][col].setValueToNEAR();    
                result = NEAR_MISS;                    
            } else {
                board[row][col].setValueToMISS();
                result = MISS;
            }  
        return result;
    }
    
    public void printBoard() {
        System.out.print("  ");
        for(int i = 0; i < boardSize; i++) {
            System.out.print(i);
        }
        System.out.println();
        for(int row = 0; row < boardSize; row++) {
            System.out.print(row + " ");
            for(int col = 0; col < boardSize; col++) {
               System.out.print(board[row][col].getValue());                
            }
            System.out.println();
        }
    }
    
    public void setShips(int numShips){
        try {
            int row;
            int col;
            if(numShips < 1 || numShips > (boardSize * boardSize)) {
                throw new IndexOutOfBoundsException("Error: Number of ships must be between 1 and " + boardSize * boardSize);
            } else {
                this.numShips = numShips;
                for(int i = 0; i < numShips; i++) {
                    do {
                        row = new Random().nextInt(boardSize - 1);
                        col = new Random().nextInt(boardSize - 1);
                        if (board[row][col].doesSpaceHaveShip() == true)
                            placeShip(row,row);
                     } while (placeShip(row,col) == false);
                 }
             }
             
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
            System.exit(1);                 
        }
    }
    // return true if it worked, false if it didn't
    public boolean placeShip(int row, int col){
        if(board[row][col].doesSpaceHaveShip() == true){
            return false;            
        } else {
            board[row][col].setShip();
            return true;
        }
    }
}
    

