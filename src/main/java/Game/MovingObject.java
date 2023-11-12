package Game;
// import java.util.ArrayList;

// Controls all game logic .. most important part in this project.
// Idea basically the same as ThreadsController
public class MovingObject extends Thread {
    // ArrayList<ArrayList<DataOfSquare>> Squares= new ArrayList<ArrayList<DataOfSquare>>();
    int[][] maze;
    GUI g;
    Tuple position;
    Tuple end_position;
    long speed;
    public static int direction;

    // Constructor of Moving Object
    MovingObject(Tuple p, Tuple ending_position, GUI gui, long s, int d){
        // Squares = Window.Grid;
        maze = gui.getMaze();
        g = gui;
        position = new Tuple(p.getX(), p.getY());
        end_position = new Tuple(ending_position.getX(), ending_position.getY());
        speed = s;
        direction = d;
    }

    // Delay between each move of the moving object
    protected void pauser(){
        try{
            sleep(speed);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    // Check whether it collide with a wall
    // 1: Right, 2: Left, 3:Top, 4: Bottom
    protected void checkWall(){
        int x = position.getX();
        int y = position.getY();

        switch(direction){
            case 4:
                if(maze[y+1][x] == 1) {             // The below block is wall
                    waitChangeDirection(4);
                }
                break;

            case 3:
                if(maze[y-1][x] == 1) {             // The upper block is wall
                    waitChangeDirection(3);
                }
                break;

            case 2:
                if(x == 0 || maze[y][x-1] == 1) {   // The left block is wall or out of boundary
                    waitChangeDirection(2);
                }
                break;

            case 1:
                if(maze[y][x+1] == 1) {             // The right block is wall
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
        int color = 0;
        if(speed == 400) color = 3;
        else if (speed == 500) color = 2;

        switch(direction){
            case 4:
                position.ChangeData(x, y+1);
                maze[y][x] = 0;
                maze[y+1][x] = color;
                break;

            case 3:
                position.ChangeData(x, y-1);
                maze[y][x] = 0;
                maze[y-1][x] = color;
                break;

            case 2:
                position.ChangeData(x-1, y);
                maze[y][x] = 0;
                maze[y][x-1] = color;
                break;

            case 1:
                position.ChangeData(x+1, y);
                maze[y][x] = 0;
                maze[y][x+1] = color;
                break;
        }

        g.updateMaze(maze);
    }

    protected void checkEndGame(){
        int x = position.getX();
        int y = position.getY();
        int ex = end_position.getX();
        int ey = end_position.getY();

        if(maze[y][x] == 2 && maze[y][x] == 3){
            playerLoses();
        }

        else if(maze[ey][ex] == 3){
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
