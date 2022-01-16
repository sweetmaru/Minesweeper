import java.awt.Graphics;

/*
class MapTop
This class is used to draw the icons on the top layer.
*/
public class MapTop {
    
    int temp_x;//column
    int temp_y;//row

    void reGame(){
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                GameUtil.DATA_TOP[i][j] = 0;
            }
        }
    }

    //Recursively uncovers all the area with no adjacent mines till the boundry formed with numbers
    //and flagged squares
    void openSquare(int row, int col){
        if(GameUtil.DATA_BOTTOM[row][col] == 0){
            for(int i = row-1; i <= row+1; i++){
                for(int j = col-1; j <= col+1; j++){
                    //do the recursion only if the square is still covered and in the range
                    if(GameUtil.DATA_TOP[i][j] == 0){
                        GameUtil.DATA_TOP[i][j] = -1;
                        if(i >= 1 && i <= GameUtil.MAP_H 
                            && j >= 1 && j <= GameUtil.MAP_W){
                            openSquare(i, j);
                        }
                    }
                }
            }
        } 
    }

    //open the non-flagged area around the certain square when right clicking
    void numOpen(int row, int col){
        int count = 0;
        for(int i = row-1; i <= row+1; i++){
            for(int j = col-1; j <= col+1; j++){
                if(GameUtil.DATA_TOP[i][j] == 1){
                    count++;
                }
            }
        }
        //if the number of flags around the square is equal to the number on the bottom layer
        //open the non-flagged area around the certain square
        if(count == GameUtil.DATA_BOTTOM[row][col]){
            for(int i = row-1; i <= row+1; i++){
                for(int j = col-1; j <= col+1; j++){
                    if(GameUtil.DATA_TOP[i][j] == 0){
                        GameUtil.DATA_TOP[i][j] = -1;
                        if(i >= 1 && i <= GameUtil.MAP_H 
                            && j >= 1 && j <= GameUtil.MAP_W){
                            openSquare(i, j);
                        }
                    }
                }
            }        
        }
    }

    //if lose the game
    //change the status of the top layer to display the unflagged mines and wrongly flagged squares
    void seeLose(){
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                //unflagged mines
                if(GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] == 0){
                    GameUtil.DATA_TOP[i][j] = -1;
                }
                //wrongly flagged squares
                if(GameUtil.DATA_BOTTOM[i][j] != -1 && GameUtil.DATA_TOP[i][j] == 1){
                    GameUtil.DATA_TOP[i][j] = 2;
                }
            }
        }    
    }

    //check if lose the game
    //return true if lose the game, false if not
    boolean gameLose(){
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] == -1){
                    GameUtil.state = 2;
                    seeLose();
                    return true;
                }
            }
        }
        return false;
    }

    //if win, open all the covered non-mine squares
    void seeWin(){
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_TOP[i][j] == 0){
                    GameUtil.DATA_TOP[i][j] = -1;
                }
            }
        }
    }

    //check if win the game
    //return true if win the game, false if not
    boolean gameWin(){
        int count = 0;
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                if(GameUtil.DATA_TOP[i][j] != -1){
                    count++;
                }
            }
        }
        if(count == GameUtil.numMine){
            GameUtil.state = 1;
            for(int i = 1; i <= GameUtil.MAP_W; i++){
                for(int j = 1; j <= GameUtil.MAP_H; j++){
                    if(GameUtil.DATA_BOTTOM[i][j] == -1 && GameUtil.DATA_TOP[i][j] == 0){
                        GameUtil.DATA_TOP[i][j] = 1;
                    }
                }
            }
            return true;
        }

        if(GameUtil.numFlag == GameUtil.numMine){
            for(int i = 1; i <= GameUtil.MAP_W; i++){
                for(int j = 1; j <= GameUtil.MAP_H; j++){
                    //if wrongly flagged, then not win
                    if(GameUtil.DATA_BOTTOM[i][j] != -1 && GameUtil.DATA_TOP[i][j] == 1){
                        return false;
                    }
                }
            }
            //if all the flags are correct, win
            GameUtil.state = 1;
            seeWin();
            return true;
        }

        return false;
    }

    //logic for opening the mine field by left and right click
    void logic(){

        //reset the mouse click location
        temp_x = 0;//column
        temp_y = 0;//row

        if(GameUtil.MOUSE_X >= GameUtil.OFFSET && GameUtil.MOUSE_Y >= 3*GameUtil.OFFSET){
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET)/GameUtil.SQUARE_LENGTH + 1;
            temp_y = (GameUtil.MOUSE_Y - 3*GameUtil.OFFSET)/GameUtil.SQUARE_LENGTH + 1;
        }

        //only change the data of the top layer if the click is done in the mine field
        if(temp_x >= 1 && temp_x <= GameUtil.MAP_W 
            && temp_y >= 1 && temp_y <= GameUtil.MAP_H){
            if(GameUtil.LEFT){
                if(GameUtil.DATA_TOP[temp_x][temp_y] == 0){
                    GameUtil.DATA_TOP[temp_x][temp_y] = -1;
                }
                //recursively open squares
                openSquare(temp_x, temp_y);
                GameUtil.LEFT = false;
            }
            if(GameUtil.RIGHT){
                //flag a covered sqaure
                if(GameUtil.DATA_TOP[temp_x][temp_y] == 0){
                    GameUtil.DATA_TOP[temp_x][temp_y] = 1;
                    GameUtil.numFlag++;
                }
                //return to a covered square
                else if(GameUtil.DATA_TOP[temp_x][temp_y] == 1){
                    GameUtil.DATA_TOP[temp_x][temp_y] = 0;
                    GameUtil.numFlag--;
                    
                }
                else if(GameUtil.DATA_BOTTOM[temp_x][temp_y] > 0
                         && GameUtil.DATA_TOP[temp_x][temp_y] == -1){
                         numOpen(temp_x, temp_y);
                }
                GameUtil.RIGHT = false;
            }
        }
        gameLose();
        gameWin();
    }

    
    void paintSelf(Graphics g){
        logic();
        for(int i = 1; i <= GameUtil.MAP_W; i++){
            for(int j = 1; j <= GameUtil.MAP_H; j++){
                //draw the covered image on the corresponding squares
                if(GameUtil.DATA_TOP[i][j] == 0){
                    g.drawImage(GameUtil.top,
                     GameUtil.OFFSET + (i-1)*GameUtil.SQUARE_LENGTH,
                     3*GameUtil.OFFSET + (j-1)*GameUtil.SQUARE_LENGTH,
                     GameUtil.SQUARE_LENGTH - 2,
                     GameUtil.SQUARE_LENGTH - 2,
                     null); 
                }

                //draw the flagged image on the corresponding squares
                if(GameUtil.DATA_TOP[i][j] == 1){
                    g.drawImage(GameUtil.flag,
                     GameUtil.OFFSET + (i-1)*GameUtil.SQUARE_LENGTH,
                     3*GameUtil.OFFSET + (j-1)*GameUtil.SQUARE_LENGTH,
                     GameUtil.SQUARE_LENGTH - 2,
                     GameUtil.SQUARE_LENGTH - 2,
                     null); 
                }

                //draw the wrongly flagged image on the corresponding squares
                if(GameUtil.DATA_TOP[i][j] == 2){
                    g.drawImage(GameUtil.noflag,
                     GameUtil.OFFSET + (i-1)*GameUtil.SQUARE_LENGTH,
                     3*GameUtil.OFFSET + (j-1)*GameUtil.SQUARE_LENGTH,
                     GameUtil.SQUARE_LENGTH - 2,
                     GameUtil.SQUARE_LENGTH - 2,
                     null); 
                }
            }
        }    
    }
}
