package Game;

import java.util.ArrayList;
public class JerryLocation extends MovingObject {

    // Constructor of JerryLocation, stores the current position as well as the ending position
    JerryLocation(Tuple starting_position, Tuple ending_position, GUI g, long s, int d) {
        super(starting_position, ending_position, g, s, d);
        //JerryContrller j = new JerryContrller();
    }

    public void run(){
        while(true){
            if(checkWall()){        // If moving in the current direction will go to a wall
                waitChangeDirection(direction);
            }
            else{
                //move();
                //checkEndGame();
                //pauser();
            }
        }
    }

    public static void clicked(){
        move();
        checkEndGame();

    }
}
