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
    private int scoreboard[][];

    public TicTacToeGame(){
        board = new Board();
        scoreboard = new int[2][3];
        initTicTacToeGame();
    }

    private void initTicTacToeGame(){
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                scoreboard[i][j] = 0;
            }
        }
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

        updateScoreboard(board.getWinner());

        if (board.getWinner() == Player.NONE) {
            System.out.println("This is a tie!");
        }
        else {
            System.out.println("Player " + board.getWinner() + " has won the game!");
        }

        printScoreboard();
    }

    private void updateScoreboard(Player winner) {
        if (winner == Player.NONE) {
            scoreboard[0][2] += 1;
            scoreboard[1][2] += 1;
        }
        else if (winner == Player.O) {
            scoreboard[0][0] += 1;
            scoreboard[1][1] += 1;
        }
        else if (winner == Player.X) {
            scoreboard[0][1] += 1;
            scoreboard[1][0] += 1;
        }
    }

    public void printScoreboard() {
        System.out.println("----------Scoreboard----------");
        System.out.print("Player O: ");
        System.out.print("\twin:" + scoreboard[0][0]);
        System.out.print("\tlose:" + scoreboard[0][1]);
        System.out.println("\ttie:" + scoreboard[0][2]);

        System.out.print("Player X: ");
        System.out.print("\twin:" + scoreboard[1][0]);
        System.out.print("\tlose:" + scoreboard[1][1]);
        System.out.println("\ttie:" + scoreboard[1][2]);
        System.out.println("------------------------------");
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
