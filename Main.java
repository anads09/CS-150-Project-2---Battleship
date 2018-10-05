/*
Austin Nadler
CS150-002
Program #2
10/05/2018
*/
package cs150proj02;

import java.util.*;

public class Main {
    static Board playerBoard;
    static Board computerBoard;
    static Scanner in = new Scanner(System.in);
    static final int DEFAULT_BOARD_SIZE = 8;
    static final int DEFAULT_NUM_SHIPS = 5;
    
    public static void main(String[] args) {       
        
        playerBoard = new Board();
        computerBoard = new Board();   
        String askCustom;
        do {
            System.out.print("Do you want to use custom rules? (Y/N) (Custom board size and number of ships): ");
            askCustom = in.next();
            if(askCustom.equalsIgnoreCase("Y")) {         
                System.out.print("Enter board size AS ONE INTEGER (e.g. 5 = 5x5 board) 1 - 10. Only first int will be read: ");
                int size = in.nextInt();
                playerBoard.setBoardSize(size, size);
                computerBoard.setBoardSize(size, size);
                System.out.print("Enter number of ships: ");
                int numShips = in.nextInt();
                playerBoard.setNumShips(numShips);
                playerBoard.setNumShips(numShips);
                playerBoard.setShips(numShips);
                computerBoard.setShips(numShips);
            
            } else {
                playerBoard.setBoardSize(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
                computerBoard.setBoardSize(DEFAULT_BOARD_SIZE, DEFAULT_BOARD_SIZE);
                playerBoard.setNumShips(DEFAULT_NUM_SHIPS);
                playerBoard.setNumShips(DEFAULT_NUM_SHIPS);
            }    
        } while (!(askCustom.equalsIgnoreCase("Y")) && !(askCustom.equalsIgnoreCase("N")));
        //System.out.println(playerBoard.getShipLocations());
        System.out.println(computerBoard.getShipLocations());
        int numTurns = 1;
           do {
                printPlayerBoard();
                printComputerBoard();
                System.out.println("Turn #" + numTurns);
                System.out.println("It's your turn. Make your shot.");
                System.out.print("Which row? (0.." + (playerBoard.getBoardSize() - 1) + "): ");
                int row = in.nextInt();
                System.out.print("Which column? (0.." + (playerBoard.getBoardSize() - 1) + "): ");
                int col = in.nextInt();
                String shotResult = computerBoard.tryShot(row, col);
                System.out.println(shotResult);
                System.out.println("Computer's shot: ");
                System.out.print("The computer shoots at ");
                String computerShotResult = computerTryShot();
                System.out.println(computerShotResult);
                numTurns++;

            } while (hasSomeoneWon(playerBoard, computerBoard) == false);    
           printPlayerBoard();
           printComputerBoard();
           
           if(playerBoard.didILose() == true) {
               System.out.println("Computer wins!");
           } else {
               System.out.println("You win!");
           }
    }
  
    public static boolean checkSize(int num) {
        if (num < 0) 
            return true;
        else 
            return false;
    }
    public static void printPlayerBoard() {
        System.out.println("Your ship grid:");
        playerBoard.printBoard();    
        System.out.println();
    }
    
    public static void printComputerBoard() {
        System.out.println("Computer's ship grid:");
        computerBoard.printBoard();   
        System.out.println();
    }
    
    public static boolean hasSomeoneWon(Board player, Board computer) {
        if (player.didILose() == true || computer.didILose() == true)
            return true;
        else
            return false;
    }
    
    public static String computerTryShot() { 
        String result = "";
        int row;
        int col;
        
        do {
            row = new Random().nextInt(playerBoard.getBoardSize() - 1);
            col = new Random().nextInt(playerBoard.getBoardSize() - 1);
            if(playerBoard.hasSpaceBeenShotAt(row, col) == false) {
                System.out.println(row + ", " + col );
                return playerBoard.tryShot(row, col);
            }
            
            playerBoard.tryShot(row, col);
        } while (playerBoard.hasSpaceBeenShotAt(row,col) == true);
        result = result + "" + row + ", " + col; 
        return result;
    }
}



