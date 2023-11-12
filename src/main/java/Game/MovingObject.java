package Game;
import java.util.ArrayList;

// Controls all game logic .. most important part in this project.
// Idea basically the same as ThreadsController
public class MovingObject extends Thread {
    ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
    Tuple position;
    Tuple end_position;
    long speed;
    public static int direction;

    // Constructor of Moving Object
    MovingObject(Tuple position, Tuple ending_position, long s, int d){
        Squares = Window.Grid;
        position = new Tuple(position.getX(), position.getY());
        speed = s;
        direction = d;
    }

    // Delay between each move of the moving object
    protected void pauser(){
        try{
            sleep(speed);
        }
        catch(InterruptedException e){
            e.printStackTrace();;
        }
    }

    // Check whether it collide with a wall
    // 1: Right, 2: Left, 3:Top, 4: Bottom
    protected void checkWall(){
        int x = position.getX();
        int y = position.getY();

        switch(direction){
            case 4:
                if(Squares.get(x).get(y+1).color == 1) {            // The below block is wall
                    waitChangeDirection(4);
                }
                break;

            case 3:
                if(Squares.get(x).get(y-1).color == 1) {            // The upper block is wall
                    waitChangeDirection(3);
                }
                break;

            case 2:
                if(Squares.get(x-1).get(y).color == 1 || x == 0) {  // The left block is wall or out of boundary
                    waitChangeDirection(2);
                }
                break;

            case 1:
                if(Squares.get(x+1).get(y).color == 1) {            // The right block is wall
                    waitChangeDirection(1);
                }
                break;
        }
    }

    protected void waitChangeDirection(int old_direction){
        while(true){
            if(old_direction != direction){
                break;
            }
        }
        checkWall();
    }

    protected void move(){
        int x = position.getX();
        int y = position.getY();

        switch(direction){
            case 4:
                position.ChangeData(x, y+1);
                break;

            case 3:
                position.ChangeData(x, y-1);
                break;

            case 2:
                position.ChangeData(x-1, y);
                break;

            case 1:
                position.ChangeData(x+1, y);
                break;
        }
    }

    protected void checkEndGame(){
        int x = position.getX();
        int y = position.getY();
        int ex = end_position.getX();
        int ey = end_position.getY();

        if(Squares.get(x).get(y).color == 2 && Squares.get(x).get(y).color == 3){
            playerLoses();
        }

        else if(Squares.get(ex).get(ey).color == 3){
            playerWins();
        }
    }

    protected void playerWins(){
        System.out.println("You Win!");
        while(true){
            pauser();
        }
    }
    protected void playerLoses(){
        System.out.println("You Lose!");
        while(true){
            pauser();
        }
    }


}
