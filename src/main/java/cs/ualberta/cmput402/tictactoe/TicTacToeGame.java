package cs.ualberta.cmput402.tictactoe;

import cs.ualberta.cmput402.tictactoe.board.Board;
import cs.ualberta.cmput402.tictactoe.board.Board.Player;
import cs.ualberta.cmput402.tictactoe.board.exceptions.InvalidMoveException;

import java.util.Scanner;

/**
 * Created by snadi on 2018-07-18.
 */
public class TicTacToeGame {

    private Board board;

    public TicTacToeGame(){
        board = new Board();
    }

    public void promptNextPlayer(){
        switch(board.getCurrentPlayer()){
            case X:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;
            case O:
                System.out.println("It's player " + board.getSymbol(board.getCurrentPlayer()) + "'s turn. Please enter the coordinates of your next move as x,y: ");
                break;

        }
    }

    public void playGame(){
        Scanner keyboardScanner = new Scanner(System.in);

        while (board.getWinner() == null){
            board.printBoard();
            promptNextPlayer();
            String line = keyboardScanner.nextLine();
            String input[] = line.split(",");
            try {
                board.playMove(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            } catch (InvalidMoveException e) {
                System.out.println("Invalid coordinates. Try again");
                promptNextPlayer();
            }
        }

        board.printBoard();

        if (board.getWinner() == Player.NONE) {
            System.out.println("This is a tie!");
        }
        else {
            System.out.println("Player " + board.getWinner() + " has won the game!");
        }
    }

    public void clearBoard() {
        board = new Board();
    }

    public static void main(String args[]){
        TicTacToeGame game = new TicTacToeGame();

        String playAgain = "y";
        while (playAgain.equals("y") || playAgain.equals("Y")) {
            game.playGame();

            System.out.println("Do you want to play again? (y/n)");
            Scanner keyboardScanner = new Scanner(System.in);

            playAgain = "";
            while (!playAgain.equals("Y") && !playAgain.equals("y")
                    && !playAgain.equals("N") && !playAgain.equals("n")) {
                playAgain = keyboardScanner.nextLine();
                System.out.println("play again: " + playAgain);

            }

            game.clearBoard();
        }
    }
}
