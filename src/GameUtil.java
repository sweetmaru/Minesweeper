import java.awt.*;

/*
class GameUtil
This class is used to store all the static data for the game.
*/
public class GameUtil {

    //number of mines in the mine field
    static int numMine = 5;
    //number of flags in the mine field
    static int numFlag = 0;

    //number of squares in each row and column
    //the initial number should be big enough to avoid index out of bounds
    static int MAP_W = 30;
    static int MAP_H = 16;
    //offset of the mine field to the boundry of the window
    static int OFFSET = 45;

    static int SQUARE_LENGTH = 50;

    //status of the sqaures in the bottom layer: -1 mine; 0 empty; 1~8 number of mines around
    static int[][] DATA_BOTTOM = new int[MAP_W+2][MAP_H+2];
    //status of the squares in the top layer: -1 uncovered; 0 covered; 1 flagged; 2 wrongly flagged
    static int[][] DATA_TOP = new int[MAP_W+2][MAP_H+2];

    //images of the top and bottom layers in the mine field
    static Image mine = Toolkit.getDefaultToolkit().getImage("imgs/mine.png");
    static Image top = Toolkit.getDefaultToolkit().getImage("imgs/top.gif");
    static Image flag = Toolkit.getDefaultToolkit().getImage("imgs/flag.gif");
    static Image noflag = Toolkit.getDefaultToolkit().getImage("imgs/noflag.png");

    static Image[] numbers = new Image[9];
    static{
        for(int i = 1; i <= 8; i++){
            numbers[i] = Toolkit.getDefaultToolkit().getImage("imgs/num/" + i + ".png");
        }
    }

    //the coordinates of the position of the mouse click
    static int MOUSE_X;
    static int MOUSE_Y;
    //the status of the mouse click
    static boolean LEFT = false;
    static boolean RIGHT = false;

    //the status of the game: 0 during the game; 1 win; 2 lose; 3 hard level selection
    static int state = 3;

    //images of the game status
    static Image face = Toolkit.getDefaultToolkit().getImage("imgs/face.png");
    static Image win = Toolkit.getDefaultToolkit().getImage("imgs/win.png");
    static Image lose = Toolkit.getDefaultToolkit().getImage("imgs/lose.png");

    //the start time and end time of a certain game
    static long START_TIME;
    static long END_TIME;

    //the method to draw the words of the number of left mines and the timer
    static void drawWord(Graphics g, Color color, int size, String str, int x, int y){
        g.setColor(color);
        g.setFont(new Font(null, Font.BOLD, size));
        g.drawString(str, x, y);
    }

    //the hard level of the game: 0 easy; 1 medium; 2 hard; 3 customized by the player
    static int level;
}
