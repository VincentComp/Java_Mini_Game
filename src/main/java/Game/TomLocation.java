import java.util.ArrayList;
import javax.swing.JFrame;
public class TomLocation extends MovingObject {

    TomLocation(Tuple position, Tuple ending_position, long s, int d) {
        super(position, ending_position, s, d);
    }

    public void run(){
        while(true){
            checkWall();
            move();
            checkEndGame();
            pauser();
        }
    }
}
