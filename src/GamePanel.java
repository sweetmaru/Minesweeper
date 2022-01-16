import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

/*
class GamePanel
This class is used to create the window and draw the icons on the window.
*/
public class GamePanel extends JFrame{

    //width and height of the window
    int width = 2*GameUtil.OFFSET + GameUtil.MAP_W*GameUtil.SQUARE_LENGTH;
    int height = 4*GameUtil.OFFSET + GameUtil.MAP_H*GameUtil.SQUARE_LENGTH;

    //an empty image
    Image offScreenImage = null;

    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    LevelSelect levelSelect = new LevelSelect();

    //true if the game starts, false if not
    boolean begin = false;
    
    //the basic settings of the window
    void launch(){
        GameUtil.START_TIME = System.currentTimeMillis();//record the start time
        this.setVisible(true);
        if(GameUtil.state == 3){
            this.setSize(500, 570);
        }
        else this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("ffMinesweeper>w<");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //receive the mouse click
        //first overrode the method mouseClicked
        //change to the method mousePressed to get a smoother operation
        this.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);

                //allow the player to restart the game during anytime of the game
                switch(GameUtil.state){
                    case 0:
                        if(e.getButton() == MouseEvent.BUTTON1){
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.LEFT = true;
                        }
                        if(e.getButton() == MouseEvent.BUTTON3){
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.RIGHT = true;
                        }
                    case 1:

                    case 2:
                        //choose the level and reset
                        //since there is only one break statement, the player is able to choose the level and restart at any state of the game
                        if(e.getButton() == MouseEvent.BUTTON1){
                            if(e.getX() >= GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W/2)
                                && e.getX() <= GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W/2) + GameUtil.SQUARE_LENGTH
                                && e.getY() >= GameUtil.OFFSET
                                && e.getY() <= GameUtil.OFFSET + GameUtil.SQUARE_LENGTH){
                                mapTop.reGame();
                                mapBottom.reGame();
                                GameUtil.numFlag = 0;
                                GameUtil.state = 0;
                                GameUtil.START_TIME = System.currentTimeMillis();
                            }
                        }

                        if(e.getButton() == MouseEvent.BUTTON2){
                            GameUtil.state = 3;
                            begin = true;
                        }
                        
                        break;

                    case 3:
                        if(e.getButton() == MouseEvent.BUTTON1){
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            begin = levelSelect.hard();
                        }
                        
                        break;

                    default:
                }
                
            }
        });
        //repaint every 40 ms to avoid zooming in and out to paint the images
        while(true){
            repaint();
            begin();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    void begin(){
        if(begin){
            begin=false;
            levelSelect.hard(GameUtil.level);
            dispose();
            GamePanel gamePanel = new GamePanel();
            GameUtil.START_TIME = System.currentTimeMillis();
            GameUtil.numFlag=0;
            mapBottom.reGame();
            mapTop.reGame();
            gamePanel.launch();
        }
    }

    //draw the window
    @Override
    public void paint(Graphics g) {
        if(GameUtil.state == 3){
            g.setColor(Color.ORANGE);
            g.fillRect(0,0,500,570);
            levelSelect.paintself(g);
        }
        else {
            //first draw the top and bottom layers on the empty image
            offScreenImage = this.createImage(width, height);
            Graphics gImage = offScreenImage.getGraphics();
            //set the background color
            gImage.setColor(Color.ORANGE);
            gImage.fillRect(0, 0, width, height);
            mapBottom.paintSelf(gImage);
            mapTop.paintSelf(gImage);

            //draw the whole image on the window
            g.drawImage(offScreenImage, 0, 0, null);
        }
        
    }


    public static void main(String[] args) {
        GamePanel g = new GamePanel();
        g.launch();
        
    }
}


