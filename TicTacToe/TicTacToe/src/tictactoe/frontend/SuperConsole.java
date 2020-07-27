package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;
import tictactoe.backend.UltimateTicTacToe;
import tictactoe.controller.MyEvent;

public class SuperConsole implements ITicTacToeUI {
  private final UltimateTicTacToe game;
  private final Helper helper;
  private char piece;

  public SuperConsole( ITicTacToe game ) {
    this.game = (UltimateTicTacToe) game;
    this.game.addListener( this );
    helper = new Helper();
    piece = 'X';
  }

  @Override
  public void run() {
    setLabelsGameInit();
    play();
  }

  @Override
  public void update( MyEvent event ) {
    if ( event.getPropertyName().equals( "markMove" ) ) {
      changeLabelTurn();
      showBoardGame();
      checkStatusGame();
    }
    if ( event.getPropertyName().equals( "create" ) ) {
      setLabelsMessagesOptionsInit();
    }

  }

  private void play() {
    showBoardGame();
    System.out.println( "-- Enter the game number" );
    int number = helper.enterNumber( 11, "Enter the game number" );
    game.setInitialGame( number );
    setLabelsMessagesOptionsInit();
    while ( true ) {
      movePlayer();
    }
  }

  private void setLabelsGameInit() {
    System.out.println();
    System.out.println( helper.colorYellow() + "\n ----------- TIC TAC TOE 1.0 ----------- \n" + helper.resetColor() );
  }

  private void setLabelsMessagesOptionsInit() {
    piece = 'X';
    System.out.println( helper.colorBlue() + "\n\nChoose a number from 1 to 9 for play" + helper.resetColor() );
    System.out.println( helper.colorGreen() + "> Press 10 new game \n> Press 11 to the exit game" + helper.resetColor() );
    System.out.print( "- enter the play number " + piece + " : " );
  }

  private void movePlayer() {
    int number = helper.enterNumber( 11, "play number" );
    if ( number == 10 ) {
      game.create();
    } else {
      if ( number == 11 ) {
        System.out.println( helper.messageFinishGame() );
        System.exit( 0 );
      } else {
        if ( !game.markMove( convertRow( number ), convertColumn( number ), piece ) ) {
          System.out.println( helper.colorRed() + "***play not valid, box already checked" + helper.resetColor() );
          System.out.print( "- re-enter the play number: " );
          movePlayer();
        }
      }
    }
  }

  private void checkStatusGame() {
    if ( game.checkTicTacToe() ) {
      String winner = String.valueOf( game.winner() );
      System.out.println( helper.messageWinnerGame( winner ) + "\n" );
      starSubMenu();
    } else {
      if ( game.draw() ) {
        System.out.println( helper.messageDrawGame() + "\n" );
        starSubMenu();
      } else {
        System.out.print( "- enter the play number " + piece + " : " );
      }
    }
  }

  private void starSubMenu() {
    System.out.println( helper.colorGreen() + "> Press 10 new game \n> Press 11 to the exit game" + helper.resetColor() + "\n" );
    System.out.print( "- enter the option: " );
  }

  private void showBoardGame() {
    char[][] boardPlay = game.getBoard();
    System.out.println();
    for ( int i = 0; i < boardPlay.length; i++ ) {
      char[] chars = boardPlay[i];
      for ( int j = 0; j < boardPlay.length; j++ ) {
        char box = chars[j];
        if ( box == 'X' ) {
          System.out.print( "[ " + helper.colorPurple() + box + helper.resetColor() + " ]" );
        } else {
          if ( box == 'O' ) {
            System.out.print( "[ " + helper.colorCyan() + box + helper.resetColor() + " ]" );
          } else {
            System.out.print( "[   ]" );
          }
        }
      }
      System.out.println();
    }
    System.out.println();
  }

  private void changeLabelTurn() {
    if ( piece == 'X' ) {
      piece = 'O';
    } else {
      piece = 'X';
    }
  }

  private int convertRow( int number ) {
    return ( number - 1 ) / 3;
  }

  private int convertColumn( int number ) {
    return ( number - 1 ) % 3;
  }
}
