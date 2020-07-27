package tictactoe.backend;

import tictactoe.frontend.ITicTacToeUI;

public interface IObservable {
  void addListener( ITicTacToeUI iTicTacToeUI );
}
