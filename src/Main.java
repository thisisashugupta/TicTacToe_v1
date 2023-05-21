import java.util.HashMap;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("\nWelcome to TicTacToe!\nLets Begin!");
        HashMap<String,Integer> matchesWon = new HashMap<>();
        matchesWon.put(Character.toString('X'),0);
        matchesWon.put(Character.toString('O'),0);

        int R = 3, C = 3;
        char[][] board = new char[R][C];
        for (int row = 0; row < R; row++)
        {
            for (int col = 0; col < C; col++)
            {
                board[row][col] = ' ';
            }
        }

        char player = 'X'; // 'O'
        boolean gameOver = false;
        Scanner sc = new Scanner(System.in);

        // game starts here
        printBoard(board);
        while(!gameOver)
        {
            System.out.println("Player: "+ player);
            System.out.print("Enter row:  ");            int row = sc.nextInt()-1;
            System.out.print("Enter col:  ");            int col = sc.nextInt()-1;

            if (board[row][col] == ' ') {
                // proceed
                board[row][col] = player;
                gameOver = haveWon(player, board);
                if (!gameOver) {
                    player = switchPlayer(player);
                }
                printBoard(board);
                if(gameOver) {
                    System.out.println("Game Over!");
                    int gamesWon = matchesWon.get(Character.toString(player));
                    matchesWon.put(Character.toString(player), 1+gamesWon);
                }
                // if all places are filled then print game draw and end the game
                if(placesOver(board)) {
                    gameOver = true;
                    System.out.println("Game Draw");
                }
            } else {
                System.out.println("\n\nInvalid Mode. Try Again!");
            }


        }
        // show the matches won by both players
        for (int i = 0; i < 2; i++) {
            System.out.println("Player "+player+" Won "+matchesWon.get(Character.toString(player))+" matches!");
            player = switchPlayer(player);
        }
        System.out.println("ENDS!");
    }

    private static boolean placesOver(char[][] board) {
        int R = board.length;
        int C = board[0].length;
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static char switchPlayer(char player) {
        return (player == 'X') ? 'O' : 'X';
    }

    private static boolean haveWon(char player, char[][] board) {
        int R = board.length;
        int C = board[0].length;
        // has the player won?

        // horizontal win
        for (int row = 0; row < R; row++) {
            int count = 0;
            for (int col = 0; col < C; col++) {
                if(board[row][col] == player) {count++;}
            }
            if (count == 3) return true;
        }

        // vertical win
        for (int col = 0; col < C; col++) {
            int count = 0;
            for (int row = 0; row < R; row++) {
                if(board[row][col] == player) {count++;}
            }
            if (count == 3) return true;
        }

        // diagonal win
        int count = 0;
        for (int row = 0; row < R; row++) {
                if(board[row][row] == player) {count++;}
        }
        if (count == 3) return true;

        // diagonal win
        count = 0;
        for (int row = 0; row < R; row++) {
            if(board[row][C-1-row] == player) {count++;}
        }
        if (count == 3) return true;

        return false;
    }
    private static void printBoard(char[][] board) {
        int R = board.length;
        int C = board[0].length;
        // print the board
        System.out.println();
        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                System.out.print(" "+board[row][col]+" ");
                if (col!=C-1) System.out.print("|");
            }
            if (row!=R-1)   System.out.println("\n-----------");
        }
        System.out.println();
    }
}