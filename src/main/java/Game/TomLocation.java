package Game;
import java.util.ArrayList;
import javax.swing.JFrame;
public class TomLocation extends MovingObject {

    TomLocation(Tuple position, Tuple ending_position, GUI g, long s, int d) {
        super(position, ending_position, g, s, d);
    }

    public void run(){
        while(true){
            if(checkWall()){        // If moving in the current direction will go to a wall
                waitChangeDirection(direction);
            }
            else{
                move();
                checkEndGame();
                pauser();
            }
        }
    }
}
