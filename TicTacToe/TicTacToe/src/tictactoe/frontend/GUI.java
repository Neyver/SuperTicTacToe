package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;
import tictactoe.controller.MyEvent;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame implements ITicTacToeUI {
  private final ITicTacToe game;
  private final Helper helper;
  private JButton[] button;
  private JLabel labelTurn;

  public GUI( ITicTacToe game ) {
    this.game = game;
    this.game.addListener( this );
    helper = new Helper();
  }

  @Override
  public void run() {
    startFrame();
    startLabelTurn();
    startButtons();
    startButtonNewGame();
    setVisible( true );
  }

  @Override
  public void update( MyEvent event ) {
    if ( event.getPropertyName().equals( "markMove" ) ) {
      updateBoard();
      checkStatusGame();
    }
    if ( event.getPropertyName().equals( "create" ) ) {
      newGame();
    }
  }

  private void startFrame() {
    setTitle( "TIC TAC TOE 2.0" );
    setSize( 350, 420 );
    setLocationRelativeTo( null );
    setDefaultCloseOperation( this.EXIT_ON_CLOSE );
    setResizable( false );
  }

  private void startLabelTurn() {
    JPanel panelLabelMessage = new JPanel();
    labelTurn = new JLabel( "Turn X" );
    labelTurn.setFont( new Font( "Dialogo", Font.BOLD, 16 ) );
    panelLabelMessage.add( labelTurn );
    add( panelLabelMessage, BorderLayout.NORTH );
  }

  private void startButtons() {
    JPanel panelButtons = new JPanel();
    panelButtons.setLayout( new GridLayout( 3, 3, 1, 1 ) );
    //panelButtons.setBackground(Color.BLACK);
    button = new JButton[10];
    for ( int i = 1; i < 10; i++ ) {
      button[i] = new JButton();
      button[i].setActionCommand( Integer.toString( i ) );
      button[i].setBackground( Color.WHITE );
      button[i].setFocusPainted( false );
      button[i].setFont( new Font( "Dialogo", Font.BOLD, 50 ) );
      button[i].setPreferredSize( new Dimension( 100, 100 ) );
      button[i].setToolTipText( "Click to mark" );
      button[i].addActionListener( event -> play( event.getActionCommand() ) );
      panelButtons.add( button[i] );
    }
    add( panelButtons );
  }

  private void startButtonNewGame() {
    JPanel panelButtonNewGame = new JPanel();
    JButton buttonNewGame = new JButton( "New Game" );
    //buttonNewGame.setBackground(Color.GREEN);
    buttonNewGame.setFont( new Font( "Dialogo", Font.BOLD, 13 ) );
    buttonNewGame.addActionListener( ( event ) -> game.create() );
    panelButtonNewGame.add( buttonNewGame );
    add( panelButtonNewGame, BorderLayout.SOUTH );
  }

  private void play( String numberPlay ) {
    int number = Integer.parseInt( numberPlay );
    if ( !game.checkTicTacToe() ) {
      if ( !game.markMove( convertRow( number ), convertColumn( number ),'y' ) ) {
        button[number].setFocusPainted( true );
      }
    }
  }

  private void checkStatusGame() {
    if ( game.checkTicTacToe() ) {
      String winnerOut = helper.messageWinnerGame( String.valueOf( game.winner() ) );
      labelTurn.setText( winnerOut );
      setButtonEnable( false );
    } else {
      if ( game.draw() ) {
        labelTurn.setText( helper.messageDrawGame() );
        setButtonEnable( false );
      }
    }
  }

  public void updateBoard() {
    changeLabelTurn();
    for ( int i = 1; i < 10; i++ ) {
      setButtonMove( i );
    }
  }

  private void setButtonMove( int number ) {
    String piece = getBox( convertRow( number ), convertColumn( number ) );
    button[number].setText( piece );
    if ( piece.equals( "X" ) ) {
      button[number].setForeground( Color.BLUE );
    } else {
      if ( piece.equals( "O" ) ) {
        button[number].setForeground( Color.MAGENTA );
      } else {
        button[number].setEnabled( true );
      }
    }
  }

  private String getBox( int row, int column ) {
    char[][] boardPlay = game.getBoard();
    return String.valueOf( boardPlay[row][column] );
  }

  private void newGame() {
    labelTurn.setText( "Turn X" );
    setButtonEnable( true );
    setButtonEmpty();
  }

  private void setButtonEnable( boolean status ) {
    for ( int i = 1; i < 10; i++ ) {
      button[i].setEnabled( status );
    }
  }

  private void setButtonEmpty() {
    for ( int i = 1; i < 10; i++ ) {
      button[i].setText( "" );
    }
  }

  private void changeLabelTurn() {
    if ( labelTurn.getText().equals( "Turn X" ) ) {
      labelTurn.setText( "Turn O" );
    } else {
      labelTurn.setText( "Turn X" );
    }
  }

  private int convertRow( int number ) {
    return ( number - 1 ) / 3;
  }

  private int convertColumn( int number ) {
    return ( number - 1 ) % 3;
  }
}

