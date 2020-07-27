package tictactoe.frontend;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Helper {
  private Scanner sc;

  public Helper() {
    sc = null;
  }

  // Input Number Console
  public int enterNumber( int max, String message ) {
    sc = null;
    int number = 0;
    do {
      try {
        sc = new Scanner( System.in );
        number = sc.nextInt();
        while ( number < 1 || number > max ) {
          System.out.println( colorRed() + "*** " + message + " not valid, enter another" + resetColor() );
          System.out.print( "- enter the " + message + ": " );
          number = sc.nextInt();
        }
      } catch ( InputMismatchException ime ) {
        System.out.println( colorRed() + "***Watch out! You can only insert numbers" + resetColor() );
        System.out.print( "- enter the " + message + ": " );
        assert sc != null;
        sc.next();
      } finally {
        if ( sc == null ) {
          assert false;
          sc.close();
        }
      }
    } while ( number < 1 || number > max );
    return number;
  }

  //Colors Console
  public String colorRed() { return "\033[31m"; }

  public String colorGreen() { return "\033[32m"; }

  public String colorYellow() { return "\033[33m"; }

  public String colorBlue() { return "\033[34m"; }

  public String colorPurple() { return "\033[35m"; }

  public String colorCyan() { return "\033[36m"; }

  public String resetColor() { return "\u001B[0m"; }

  //Message Game
  public String messageWinnerGame( String winner ) {
    return "The winner is " + winner + ", Congratulations!";
  }

  public String messageDrawGame() {
    return "It's a Draw :'v";
  }

  public String messageFinishGame() {
    return "Thanks for Play!!! :)";
  }
}
