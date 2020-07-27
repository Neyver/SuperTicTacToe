package tictactoe.backend;

import tictactoe.controller.MyEvent;
import tictactoe.frontend.ITicTacToeUI;

import java.util.ArrayList;
import java.util.List;

public class UltimateTicTacToe implements ITicTacToe {
  private final int dimension;
  private final List<ITicTacToeUI> listeners;
  private List<ITicTacToe> games;
  private char[][] board;
  private int numberMove;
  private char turn;
  private char winner;
  private int initialGame;

  public UltimateTicTacToe() {
    dimension = 9;
    initialGame = -1;
    board = new char[dimension][dimension];
    listeners = new ArrayList<>();
    games = new ArrayList<ITicTacToe>();
    create();
  }

  @Override
  public void create() {
    numberMove = 0;
    turn = 'X';
    winner = '\0';
    addGame();
    notifyListener( new MyEvent( this, "create", false, true ) );
  }

  @Override
  public boolean markMove( int row, int column, char move ) {
    boolean res = games.get( initialGame - 1 ).markMove( row, column, move );
    setInitialGame( convertNumber( row, column ) );
    notifyListener( new MyEvent( this, "markMove", false, true ) );
    return res;
  }

  @Override
  public boolean checkTicTacToe() {
    boolean res = false;
    for ( int i = 0; i < 9; i++ ) {
      boolean checkTicTacToe = games.get( i ).checkTicTacToe();
      if ( checkTicTacToe ) {
        games.get( 9 ).markMove( convertRow( i ), convertColumn( i ), games.get( i ).winner() );
      }
    }
    res = games.get( 9 ).checkTicTacToe();
    if ( !res ) {
      games.get( 9 ).create();
    }
    return res;
  }

  @Override
  public char winner() {
    return games.get( 10 ).winner();
  }

  @Override
  public boolean draw() {
    boolean res = false;
    for ( int i = 0; i < 9; i++ ) {
      boolean checkTicTacToe = games.get( i ).checkTicTacToe();
      if ( checkTicTacToe ) {
        games.get( 9 ).markMove( convertRow( i ), convertColumn( i ), games.get( i ).winner() );
      }
    }
    res = games.get( 9 ).draw();
    if ( !res ) {
      games.get( 9 ).create();
    }
    return res;
  }

  @Override
  public char[][] getBoard() {
    int n = 0;
    int game = 0;
    for ( int m = 0; m < 9; m++ ) {
      char[][] boardAux = games.get( game ).getBoard();
      if ( n == 2 && m == 2 ) {
        break;
      }
      if ( m % 3 == 0 && m != 0 ) {
        n++;
        m = 0;
      }
      llenar( boardAux, m, n );
      game++;
    }
    return board;
  }

  public void llenar( char[][] matriz, int column, int row ) {
    int rowBoard = 0;
    int columnBoard = 0;
    int x = row * 3;
    int y = column * 3;
    for ( int i = x; i < x + 3; i++ ) {
      for ( int j = y; j < y + 3; j++ ) {
        board[i][j] = matriz[rowBoard][columnBoard];
        columnBoard++;
      }
      columnBoard = 0;
      rowBoard++;
    }
  }

  @Override
  public void addListener( ITicTacToeUI ticTacToeUI ) {
    listeners.add( ticTacToeUI );
  }

  private void notifyListener( MyEvent event ) {
    for ( ITicTacToeUI ui : listeners ) {
      ui.update( event );
    }
  }

  public void setInitialGame( int number ) {
    this.initialGame = number;
  }

  private int convertNumber( int row, int column ) {
    if ( row == 0 && column == 0 ) {
      return 1;
    }
    if ( row == 0 && column == 1 ) {
      return 2;
    }
    if ( row == 0 && column == 2 ) {
      return 3;
    }
    if ( row == 1 && column == 0 ) {
      return 4;
    }
    if ( row == 1 && column == 1 ) {
      return 5;
    }
    if ( row == 1 && column == 2 ) {
      return 6;
    }
    if ( row == 2 && column == 0 ) {
      return 7;
    }
    if ( row == 2 && column == 1 ) {
      return 8;
    } else {
      return 9;
    }
  }

  private void addGame() {
    for ( int i = 0; i < 10; i++ ) {
      games.add( new TicTacToe() );
    }
  }

  private int convertRow( int number ) {
    return ( number ) / 3;
  }

  private int convertColumn( int number ) {
    return ( number ) % 3;
  }
}
