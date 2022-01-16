//fail to complete the part for players to customize

import java.awt.Color;
import java.awt.Graphics;

/*
class LevelSelect
This class is used to initialize the data of different levels and draw the icons of levels
*/
public class LevelSelect {
    

    //draw the level buttons
    void paintself(Graphics g){
        g.setColor(Color.BLUE);
        g.drawRect(100,50,300,80);
        g.fillRect(100,50,300,80);
        GameUtil.drawWord(g,Color.black,30,"EASY",200,100);

        g.setColor(Color.BLUE);
        g.drawRect(100,180,300,80);
        g.fillRect(100,180,300,80);
        GameUtil.drawWord(g,Color.black,30,"MEDIUM",200,230);

        g.setColor(Color.BLUE);
        g.drawRect(100,310,300,80);
        g.fillRect(100,310,300,80);
        GameUtil.drawWord(g,Color.black,30,"HARD",200,360);

        g.setColor(Color.BLUE);
        g.drawRect(100,440,300,80);
        g.fillRect(100,440,300,80);
        GameUtil.drawWord(g,Color.black,30,"CUSTOM",200,490);
    }

    //change the status and level if click on the related level button
    boolean hard(){
        if(GameUtil.MOUSE_X>100&&GameUtil.MOUSE_X<400){
            if(GameUtil.MOUSE_Y>50&&GameUtil.MOUSE_Y<130){
                GameUtil.level=0;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.MOUSE_Y>180&&GameUtil.MOUSE_Y<260){
                GameUtil.level=1;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.MOUSE_Y>310&&GameUtil.MOUSE_Y<390){
                GameUtil.level=2;
                GameUtil.state=0;
                return true;
            }
            if(GameUtil.MOUSE_Y>440&&GameUtil.MOUSE_Y<520){
                GameUtil.level=3;
                GameUtil.state=0;
                return true;
            }
        }
        return false;
    }

    //define the data of different levels
    void hard(int level){
        switch(level){
            case 0:
                GameUtil.numMine = 10;
                GameUtil.MAP_W = 9;
                GameUtil.MAP_H = 9;
                break;
            case 1:
                GameUtil.numMine = 40;
                GameUtil.MAP_W = 16;
                GameUtil.MAP_H = 16;
                break;
            case 2:
                GameUtil.numMine = 99;
                GameUtil.MAP_W = 30;
                GameUtil.MAP_H = 16;
                break;
            case 3:
                
                break;
            default:
        }
    }


}

