import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleBoard {
  //static String[][] board = new String[9][9];
  static boolean[][] dead= new boolean[9][9];
  static boolean[][] visited = new boolean[9][9];
  static boolean[][] territory = new boolean[9][9];
  static int blackScore=0;
  static int whiteScore=0;
  static ArrayList borders = new ArrayList<>();
  //static String[][] board={
//      0   1   2   3   4   5   6   7   8
 ///*0*/{"+","+","B","B","+","+","+","+","+"},     
// /*1*/{"+","B","W","W","B","+","+","+","+"},    
 ///*2*/{"+","B","W","+","W","B","+","+","+"}, 
// /*3*/{"+","B","W","W","W","B","+","+","+"},
 ///*4*/{"+","B","W","+","W","B","+","+","+"},
 ///*5*/{"+","+","B","W","W","B","+","+","+"},
 ///*6*/{"+","+","+","B","B","+","+","+","+"},
 ///*7*/{"+","+","+","+","+","+","+","+","+"}, 
 ///*8*/{"+","+","+","+","+","+","+","+","+"},     
 // };
 static String[][] board =
 {
/* */{"+","+","+","+","+","+","+","+","+"},  
/* */{"+","+","+","+","+","+","+","+","+"},
/* */{"+","+","W","+","+","+","B","+","+"},
/* */{"+","W","+","W","+","B","+","B","+"},
/* */{"+","+","W","+","+","+","B","+","+"},
/* */{"+","B","B","+","B","+","+","+","+"},
/* */{"B","+","+","B","+","B","+","+","+"},
/* */{"+","B","B","+","B","+","+","+","+"},
/* */{"+","+","+","+","+","+","+","+","+"},
 };

  public static void main(String[] args) {

   
    placeStone(4, 6, "B");

    confirmCapture("B");
     checkAllTeritory();
    showBoardB(visited);
    System.out.println("b:" + blackScore);
    System.out.println("w: " + whiteScore);
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

    
  }

public static void checkSurrounding(int x, int y, String color)
{
    if(x+1<=8 && board[y][x+1]!="+" && board[y][x+1]!=color)
    {
        if(!checkPeice(x+1, y, board[y][x+1]))
            confirmCapture(board[y][x+1]);
        whipeBoard(visited);
    }

    if(x-1>=0 && board[y][x-1]!="+" && board[y][x-1]!=color)
    {
        if(!checkPeice(x-1, y, board[y][x-1]))
            confirmCapture(board[y][x-1]);
        whipeBoard(visited);
    }

    if(y+1<=8 && board[y+1][x]!="+" && board[y+1][x]!=color)
    {
        if(!checkPeice(x, y+1, board[y+1][x]))
            confirmCapture(board[y+1][x]);
        whipeBoard(visited);
    }

    if(y-1>=0 && board[y-1][x]!="+" && board[y-1][x]!=color)
    {
        if(!checkPeice(x, y-1, board[y-1][x]))
            confirmCapture(board[y-1][x]);
        whipeBoard(visited);
    }

    if(!checkPeice(x, y, color))
    {
        if(color=="B")
            whiteScore = whiteScore + countTrues(visited);
        else
            blackScore = blackScore + countTrues(visited);
    }
    whipeBoard(visited);
}
  
public static void confirmCapture(String capturedColor)
{
    for(int i = 0; i < 9; i++)
        for(int j = 0; j < 9; j++)
            if(visited[i][j])
            {
                dead[i][j] = true;
            }
    
    if(capturedColor=="B")
        whiteScore = whiteScore + countTrues(visited);
    else
        blackScore = blackScore + countTrues(visited);
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

    System.err.println("  012345678");

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
    //make sure in bounds
    
    if(x<0 || x>8 || y<0 || y>8)
    {
      return false;
    }
    //checks if spot is empty
    if(board[y][x]=="+"||dead[y][x])
      {
        return true;
      }
      //checks if peice is on the other team or alread visited
      if(board[y][x]!=peice || visited[y][x])
      {
        return false;
      }
      //sets peice to visited
      visited[y][x]=true;
      //recusively checks all 4 directions for a empty spot
      if(checkPeice(x-1,y,peice)||checkPeice(x+1,y,peice)||checkPeice(x,y-1,peice)||checkPeice(x,y+1,peice))
      {
        return true;
      }

      //if no empty spots are found, returns false
      return false;
  }

  public static void checkteritory(int x, int y)
  { 
    //make sure in bounds
    if(x<0 || x>8 || y<0 || y>8)
    {
      return;
    }

    //checks if peice is on the other team or alread visited
      if(board[y][x]!="+"||visited[y][x])
      {
        if(!visited[y][x])
        {
          borders.add(board[y][x]);
        }
        return;
      }
      //sets peice to visited
      visited[y][x]=true;
      territory[y][x]=true;
      
      //recusively checks all 4 directions for a empty spot
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
      if(!visited[y][x] && board[y][x]=="+")
      {
        checkteritory(x,y);
        if(borders.size()>0)
      {  
        if(allSame(borders))
        {
          if(borders.get(0)=="B")
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

  Object j =x.get(0);
  for(Object i : x)
  {
    if(i!=j)
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
  public void playing(){
      Scanner scn = new Scanner(System.in);
    //createBoard(board);
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
        break;
      }
      if (blackTurn) {
        placeStone(x, y, "black");
        blackTurn = false;
      } else {
        placeStone(x, y, "white");
        blackTurn = true;
      }

    }

    scn.close();
}

}