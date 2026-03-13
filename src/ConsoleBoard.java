import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleBoard {
  static boolean[][] dead= new boolean[9][9];
  static boolean[][] visited = new boolean[9][9];
  static boolean[][] territory = new boolean[9][9];
  static int blackScore=0;
  static int whiteScore=0;
  static ArrayList borders = new ArrayList<>();

 static String[][] board =
 {
/*0*/{"+","+","W","+","+","+","+","+","+"},  
/*1*/{"+","W","+","W","+","+","+","+","+"},
/*2*/{"+","W","+","W","+","+","+","+","+"},
/*3*/{"+","+","W","+","+","+","+","+","+"},
/*4*/{"+","+","+","+","B","+","+","+","+"},
/*5*/{"+","+","+","B","+","B","+","+","+"},
/*6*/{"+","+","+","B","+","B","+","+","+"},
/*7*/{"+","+","+","+","B","+","+","+","+"},
/*8*/{"+","+","+","+","+","+","+","+","+"},
 };

  public static void main(String[] args) {
  playing();
  }

  static void createBoard(String[][] board) {
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        board[i][j] = "+";
      }
    }
  }
  
  static void whipeBoard(boolean[][] board) {
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        board[i][j] = false;
      }
    }
  }

  static void placeStone(int x, int y, String color) {
    if (x >= 0 && x <= 8 && y >= 0 && y <= 8) {
      if (!board[y][x].equals("B") && !board[y][x].equals("W")) {
        if (color.equals("B")) 
          {
            board[y][x] = "B";
          } else if (color.equals("W")) 
          {
            board[y][x] = "W";
          }
      } else {
        System.out.println("Position already occupied");
      }
    } else {
      System.out.println("Invalid position");
    }

    checkSurrounding(x,y,color);
    confirmCapture(color);
  }

public static void checkSurrounding(int x, int y, String color)
{
   System.out.println("Checking surrounding of " + x + "," + y);
    whipeBoard(visited);
    System.out.println("checkPeice result on (7,2): " + checkPeice(7, 2, "B"));
    whipeBoard(visited);
    if(x+1<=8 && !board[y][x+1].equals("+") && !board[y][x+1].equals(color))
    {
        if(!checkPeice(x+1, y, board[y][x+1]))
            confirmCapture(board[y][x+1]);
        whipeBoard(visited);
    }

    if(x-1>=0 && !board[y][x-1].equals("+") && !board[y][x-1].equals(color))
    {
        if(!checkPeice(x-1, y, board[y][x-1]))
            confirmCapture(board[y][x-1]);
        whipeBoard(visited);
    }

    if(y+1<=8 && !board[y+1][x].equals("+") && !board[y+1][x].equals(color))
    {
        if(!checkPeice(x, y+1, board[y+1][x]))
            confirmCapture(board[y+1][x]);
        whipeBoard(visited);
    }

    if(y-1>=0 && !board[y-1][x].equals("+") && !board[y-1][x].equals(color))
    {
        if(!checkPeice(x, y-1, board[y-1][x]))
            confirmCapture(board[y-1][x]);
        whipeBoard(visited);
    }
    whipeBoard(visited);
    
    if(!checkPeice(x, y, color))
      confirmCapture(color);
    whipeBoard(visited);
 
    whipeBoard(visited);
}
  
public static void confirmCapture(String capturedColor)
{
    int captured = 0;

    for(int i = 0; i < 9; i++)
        for(int j = 0; j < 9; j++)
            if(visited[i][j])
            {
                board[i][j] = "+";
                captured++;
            }
    
    if(capturedColor.equals("B"))
        whiteScore += captured;
    else
        blackScore += captured;
}
  
static int countTrues(boolean[][] board) {
    int count = 0;
    for (int i = 0; i < 9; ++i) {
      for (int j = 0; j < 9; ++j) {
        if (board[i][j]) {
          count++;
        }
      }
    }
    return count;
  }

static void showBoard(String[][] board) {
    System.out.println("  012345678");
    for (int i = 0; i < 9; ++i) {
      System.out.print("" + i + " ");
      for (int j = 0; j < 9; ++j) {
        System.out.print(board[i][j]);
      }
      System.out.println();
    }
  }
  
static void showBoardB(boolean[][] board) {
    System.err.println("  012345678");
    for (int i = 0; i < 9; ++i) {
      System.out.print("" + i + " ");
      for (int j = 0; j < 9; ++j) {
        System.out.print(board[i][j]);
        if(board[i][j]==true)
        {
          System.out.print("  ");
        }
        else
        {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
  
public static boolean checkPeice(int x, int y, String peice)
  { 
    if(x<0 || x>8 || y<0 || y>8)
      return false;

    if(board[y][x].equals("+") || dead[y][x])
      return true;

    if(!board[y][x].equals(peice) || visited[y][x])
      return false;

    visited[y][x] = true;

    boolean a = checkPeice(x-1,y,peice);
    boolean b = checkPeice(x+1,y,peice);
    boolean c = checkPeice(x,y-1,peice);
    boolean d = checkPeice(x,y+1,peice);

    return a || b || c || d;
  }

public static void checkteritory(int x, int y)
  { 
    if(x<0 || x>8 || y<0 || y>8)
      return;

    if(!board[y][x].equals("+") || visited[y][x])
    {
      if(!visited[y][x])
      {
        borders.add(board[y][x]);
      }
      return;
    }

    visited[y][x] = true;
    territory[y][x] = true;
      
    checkteritory(x-1,y);
    checkteritory(x+1,y);
    checkteritory(x,y-1);
    checkteritory(x,y+1);
  }
  
public static void checkAllTeritory()
{
  for(int x=0; x<9; x++)
  {
    for(int y=0; y<9; y++)
    {
      if(!visited[y][x] && board[y][x].equals("+"))
      {
        checkteritory(x,y);
        if(borders.size()>0)
        {  
          if(allSame(borders))
          {
            if(borders.get(0).equals("B"))
            {
              blackScore+=counter(territory);
              whipeBoard(territory);
            }
            else
            {
              whiteScore+=counter(territory);
              whipeBoard(territory);
            }
            borders.clear();
          }
          else
          {
            borders.clear();
            whipeBoard(territory);
          }
        }
      }
    }
  }
}

public static boolean allSame(ArrayList x)
{
  Object j = x.get(0);
  for(Object i : x)
  {
    if(!i.equals(j))
      return false;
  }
  return true;
}

public static int counter(boolean[][] bool)
{
  int count = 0;
  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      if (bool[i][j]) {
        count++;
      }
    }
  }
  return count;
}
  
public static void playing(){
    Scanner scn = new Scanner(System.in);
    boolean playing = true;
    boolean blackTurn = true;
  while (playing) {
      showBoard(board);
      if (blackTurn) {
        System.out.println("Black's turn");
      } else {
        System.out.println("White's turn");
      }

      System.out.print("Enter x(-1 to quit):");
      int x = scn.nextInt();
      System.out.print("Enter y(-1 to quit):");
      int y = scn.nextInt();
      if (x == -1 && y == -1) {
        checkAllTeritory();
        System.out.println("Final score:");
        System.out.println("B Score: "+ blackScore);
        System.out.println("W Score: " + whiteScore);
        break;
      }
      if (blackTurn) {
        placeStone(x, y, "B");
        blackTurn = false;
      } else {
        placeStone(x, y, "W");
        blackTurn = true;
      }
      System.out.println("B Score: "+ blackScore);
      System.out.println("W Score: " + whiteScore);
    }
    scn.close();
  }
}