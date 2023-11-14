/*
package Game;

public class TomController extends Thread{
    ShortestPath path;
    JerryLocation jerry;
    Tuple endPosition;
    TomLocation tom;
    GUI gui;

    TomController(GUI g, JerryLocation j, TomLocation t){
        gui = g;
        jerry = j;
        endPosition = j.end_position;
        tom = t;
    }

    public void run(){
        while(!checkEndGame()){
            Tuple JerryTuple = jerry.getPosition();
            Tuple TomTuple = tom.getPosition();
            int jx = JerryTuple.getX();
            int jy = JerryTuple.getY();
            int tx = TomTuple.getX();
            int ty = TomTuple.getY();
            path = new ShortestPath();
            Direction direction = path.get_Path(gui.getMaze(), tx, ty, jx, jy);
            int d = 0;
            if (direction == Direction.RIGHT) d = 1;
            if (direction == Direction.LEFT) d = 2;
            if (direction == Direction.UP) d = 3;
            if (direction == Direction.DOWN) d = 4;
            tom.changeDirection(d);
        }
    }

    public boolean checkEndGame(){
        if(jerry.getPosition() == endPosition){
            return playerWins();
        }
        else if(jerry.getPosition() == tom.getPosition()){
            return playerLoses();
        }
        return false;
    }

    public boolean playerLoses(){
        System.out.println("You Lose!");
        return true;
    }

    public boolean playerWins(){
        System.out.println("You win!");
        return true;
    }
}
*/
