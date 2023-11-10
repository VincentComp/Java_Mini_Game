import java.util.ArrayList;
public class JerryLocation extends MovingObject {

    // Constructor of JerryLocation, stores the current position as well as the ending position
    JerryLocation(Tuple starting_position, Tuple ending_position, long s, int d) {
        super(starting_position, ending_position, s, d);
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
