package tictactoe.backend;

public interface ITicTacToe extends IObservable{
    void create ();
    boolean markMove (int row, int column, char move);
    boolean checkTicTacToe();
    char winner();
    boolean draw();
    char [][] getBoard();
}
