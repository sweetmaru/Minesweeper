import java.util.Random;

/*
class BottomMine
This class is used to create mines in the random locations in the mine field.
*/
public class BottomMine{

    Random generator = new Random();

    void newMine(){
        for(int i = 1; i <= GameUtil.numMine; i++){
            int row = generator.nextInt(GameUtil.MAP_W) + 1;//1~GameUtil.MAP_H
            int col = generator.nextInt(GameUtil.MAP_H) + 1;//1~GameUtil.MAP_W
            
            //avoid duplicate mine on the same location
            if(GameUtil.DATA_BOTTOM[row][col] != -1){
                GameUtil.DATA_BOTTOM[row][col] = -1;
            }
            else i--;
            }
    }

    
    

}                                                                                                                                                                                                                                                                                                                                                                                                       
