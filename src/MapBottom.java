import java.awt.Color;
import java.awt.Graphics;

/*
class MapBottom
This class is used to draw the icons on the bottom layer.
*/
public class MapBottom {
    
    int mineLeft;

    BottomMine bottomMine = new BottomMine();
    BottomNum bottomNum = new BottomNum();
    {
        bottomMine.newMine();
        bottomNum.newNum();
    }

    void reGame(){
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                GameUtil.DATA_BOTTOM[i][j] = 0;
            }
        }
        bottomMine.newMine();
        bottomNum.newNum();

    }

    void paintSelf(Graphics g){
        g.setColor(Color.RED);
        //draw the horizontal lines of the mine field
        for(int i = 0; i <= GameUtil.MAP_H; i++){
            g.drawLine(GameUtil.OFFSET, 
             3*GameUtil.OFFSET + i*GameUtil.SQUARE_LENGTH,
             GameUtil.OFFSET + GameUtil.MAP_W*GameUtil.SQUARE_LENGTH, 
             3*GameUtil.OFFSET + i*GameUtil.SQUARE_LENGTH);
        }
        //draw the vertical lines of the mine field
        for(int j = 0; j <= GameUtil.MAP_W; j++){
            g.drawLine(GameUtil.OFFSET + j*GameUtil.SQUARE_LENGTH, 
             3*GameUtil.OFFSET,
             GameUtil.OFFSET + j*GameUtil.SQUARE_LENGTH, 
             3*GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*GameUtil.MAP_H);
        }
        
        
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                //draw the mine image on the corresponding squares
                if(GameUtil.DATA_BOTTOM[i][j] == -1){
                    g.drawImage(GameUtil.mine,
                     GameUtil.OFFSET + (i-1)*GameUtil.SQUARE_LENGTH,
                     3*GameUtil.OFFSET + (j-1)*GameUtil.SQUARE_LENGTH,
                     GameUtil.SQUARE_LENGTH - 2,
                     GameUtil.SQUARE_LENGTH - 2,
                     null); 
                }

                //draw the number image on the corresponding squares
                if(GameUtil.DATA_BOTTOM[i][j] > 0){
                    g.drawImage(GameUtil.numbers[GameUtil.DATA_BOTTOM[i][j]],
                     GameUtil.OFFSET + (i-1)*GameUtil.SQUARE_LENGTH + 15,
                     3*GameUtil.OFFSET + (j-1)*GameUtil.SQUARE_LENGTH + 5,
                     null); 
                }
                           
            }

        }

        //draw the game status icon
        switch(GameUtil.state){
            case 0:
                //record the current time
                GameUtil.END_TIME = System.currentTimeMillis();
                g.drawImage(GameUtil.face,
                    GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W/2),
                    GameUtil.OFFSET,
                    GameUtil.SQUARE_LENGTH,
                    GameUtil.SQUARE_LENGTH,
                    null);
                
                break;

            case 1:
                g.drawImage(GameUtil.win,
                    GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W/2),
                    GameUtil.OFFSET,
                    GameUtil.SQUARE_LENGTH,
                    GameUtil.SQUARE_LENGTH,
                    null);
                
                break;

            case 2:
                g.drawImage(GameUtil.lose,
                    GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (GameUtil.MAP_W/2),
                    GameUtil.OFFSET,
                    GameUtil.SQUARE_LENGTH,
                    GameUtil.SQUARE_LENGTH,
                    null);
                
                break;

            default:

        }


        if(GameUtil.numMine >= GameUtil.numFlag){
            mineLeft = GameUtil.numMine - GameUtil.numFlag;
        }
        else mineLeft = 0;

        //draw the icon for the number of left mines
        //stop at 0 if the number of flags is bigger than the actual number of mines
        GameUtil.drawWord(g, Color.BLACK, 30, 
            "" + mineLeft,
            GameUtil.OFFSET,
            2*GameUtil.OFFSET);

        //draw the timer icon
        GameUtil.drawWord(g, Color.BLACK, 30,
            "" + (GameUtil.END_TIME - GameUtil.START_TIME)/1000, 
            GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W-1), 
            2*GameUtil.OFFSET);
            
    }
}
