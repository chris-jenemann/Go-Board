import java.util.Scanner;

public class App {
   static String[][] board = new String[9][9];

   public App() {
   }

   public static void main(String[] args) {
      Scanner scn = new Scanner(System.in);
      createBoard(board);
      boolean playing = true;
      boolean blackTurn = true;

      while(playing) {
        if(blackTurn)
        {
            System.out.println("Black's turn");
        }
        else
        {
            System.out.println("White's turn");
        }
         showBoard(board);
         System.out.print("Enter x(-1 to quit):");
         int x = scn.nextInt();
         System.out.print("Enter y(-1 to quit):");
         int y = scn.nextInt();
         if (x == -1 && y == -1) {
            break;
         }
        if(blackTurn)
        {
            placeStone(x, y, "black");
            blackTurn = false;
        }
        else
        {
            placeStone(x, y, "white");
            blackTurn = true;
        }
       

      }

      scn.close();
   }

   static void createBoard(String[][] board) {
      for(int i = 0; i < 9; ++i) {
         for(int j = 0; j < 9; ++j) {
            board[i][j] = "+";
         }
      }

   }

   static void placeStone(int x, int y, String color) {
      if (x >= 0 && x <= 8 && y >= 0 && y <= 8) {
         if (!board[x][y].equals("B") && !board[x][y].equals("W")) {
            if (color.equals("black")) {
               board[x][y] = "B";
            } else if (color.equals("white")) {
               board[x][y] = "W";
            }

         } else {
            System.out.println("Position already occupied");
         }
      } else {
         System.out.println("Invalid position");
      }
   }

   static void showBoard(String[][] board) {
      System.err.println("  012345678");

      for(int i = 0; i < 9; ++i) {
         System.out.print("" + i + " ");

         for(int j = 0; j < 9; ++j) {
            System.out.print(board[i][j]);
         }

         System.out.println();
      }

   }
}

