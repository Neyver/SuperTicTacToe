import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;
import tictactoe.backend.UltimateTicTacToe;
import tictactoe.frontend.Console;
import tictactoe.frontend.GUI;
import tictactoe.frontend.ITicTacToeUI;
import tictactoe.frontend.SuperConsole;

public class Main {

    public static void main(String[] args) {
        ITicTacToe ticTacToe  = new UltimateTicTacToe();
        ITicTacToeUI superConsole = new SuperConsole(ticTacToe);
        //ITicTacToeUI gui = new GUI(ticTacToe);

        //gui.run();
        superConsole.run();
    }
}
