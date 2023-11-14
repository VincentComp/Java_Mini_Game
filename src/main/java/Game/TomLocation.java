package Game;
import java.util.ArrayList;
import javax.swing.JFrame;
public class TomLocation extends Thread {

    static int[][] maze;
    static GUI gui;
    static Tuple position;
    static long speed;
    public static int direction;

    TomLocation(Tuple p, GUI g, long s, int d) {
        gui = g;
        maze = gui.getMaze();
        position = new Tuple(p.getX(), p.getY());
        speed = s;
        direction = d;
    }

    public void run(){
        while(true){
            maze = gui.getMaze();
            if(checkWall()){        // If moving in the current direction will go to a wall
                waitChangeDirection(direction);
            }
            else{
                move();
                pauser();
            }
        }
    }

    // Delay between each move of the moving object
    protected static void pauser(){
        try{
            sleep(speed);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    // Check whether it collide with a wall
    // 1: Right, 2: Left, 3:Top, 4: Bottom
    protected static boolean checkWall(){
        int x = position.getX();
        int y = position.getY();

        switch(direction){
            case 4:
                if(y == 29 || maze[y+1][x] == 1) {             // The below block is wall
                    return true;
                }
                break;

            case 3:
                if(y == 0 || maze[y-1][x] == 1) {             // The upper block is wall
                    return true;
                }
                break;

            case 2:
                if(x == 0 || maze[y][x-1] == 1) {   // The left block is wall or out of boundary
                    return true;
                }
                break;

            case 1:
                if(x == 29 || maze[y][x+1] == 1) {             // The right block is wall
                    return true;
                }
                break;
        }
        return false;
    }

    protected void waitChangeDirection(int old_direction){
        while(old_direction == direction){
            pauser();
        }
    }

    // 1: Right, 2: Left, 3:Top, 4: Bottom
    protected static void move(){

        int x = position.getX();
        int y = position.getY();
        int color = 2;

        switch(direction){
            case 4:
                if(maze[y+1][x] == 3) playerLoses();
                position.ChangeData(x, y+1);
                gui.updateMaze(y, x, y+1, x, color);
                break;

            case 3:
                if(maze[y-1][x] == 3) playerLoses();
                position.ChangeData(x, y-1);
                gui.updateMaze(y,x,y-1,x,color);
                break;

            case 2:
                if(maze[y][x-1] == 3) playerLoses();
                position.ChangeData(x-1, y);
                gui.updateMaze(y,x,y,x-1,color);
                break;

            case 1:
                if(maze[y][x+1] == 3) playerLoses();
                position.ChangeData(x+1, y);
                gui.updateMaze(y,x,y,x+1,color);
                break;
        }
    }

    protected static void playerLoses(){
        System.out.println("You Lose!");
        while(true){
            pauser();
        }
    }
    public void changeDirection(int d){
        direction = d;
    }

    public Tuple getPosition(){
        return position;
    }
}
