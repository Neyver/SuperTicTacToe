import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.frontend.Helper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class HelperTest {
    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    @Test
    public void enterNumberValid(){
        Helper helper = new Helper();
        String input = "7";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int number = helper.enterNumber(9,"number play");
        Assert.assertEquals(7,number);
    }

    @Test
    public void enterNumberNotValid(){
        Helper helper = new Helper();
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        input = "2";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int number = helper.enterNumber(2,"number play");
        Assert.assertEquals(2,number);
    }

    @Test
    public void colorRedValid(){
        Helper helper = new Helper();
        String color = helper.colorRed();
        Assert.assertEquals("\033[31m",color);
    }

    @Test
    public void colorGreenValid(){
        Helper helper = new Helper();
        String color = helper.colorGreen();
        Assert.assertEquals("\033[32m",color);
    }

    @Test
    public void colorYellowValid(){
        Helper helper = new Helper();
        String color = helper.colorYellow();
        Assert.assertEquals("\033[33m",color);
    }

    @Test
    public void colorBlueValid(){
        Helper helper = new Helper();
        String color = helper.colorBlue();
        Assert.assertEquals("\033[34m",color);
    }

    @Test
    public void colorPurpleValid(){
        Helper helper = new Helper();
        String color = helper.colorPurple();
        Assert.assertEquals("\033[35m",color);
    }

    @Test
    public void colorCyanValid(){
        Helper helper = new Helper();
        String color = helper.colorCyan();
        Assert.assertEquals("\033[36m",color);
    }

    @Test
    public void resetColorValid(){
        Helper helper = new Helper();
        String color = helper.resetColor();
        Assert.assertEquals("\u001B[0m",color);
    }

    @Test
    public void messageDrawGameValid(){
        Helper helper = new Helper();
        String message = helper.messageDrawGame();
        Assert.assertEquals("It's a Draw :'v",message);
    }

    @Test
    public void messageFinishGameValid(){
        Helper helper = new Helper();
        String message = helper.messageFinishGame();
        Assert.assertEquals("Thanks for Play!!! :)",message);
    }

    @Test
    public void messageWinnerGameValid(){
        Helper helper = new Helper();
        String message = helper.messageWinnerGame("Alicia");
        Assert.assertEquals("The winner is Alicia, Congratulations!",message);
    }
}