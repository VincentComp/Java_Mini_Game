package Game;
// import java.util.ArrayList;

import java.util.Currency;

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

        g = gui;
        maze = gui.getMaze();
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
    protected boolean checkWall(){
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
        while(true){
            pauser();
            if(old_direction != direction){
                break;
            }
        }
    }

    protected void move(){

        int x = position.getX();
        int y = position.getY();
        int color = 0;
        if(speed == 400) color = 2;
        else if (speed == 500) color = 3;
        //System.out.println("Current Location : ");
        //System.out.println(x);
        //System.out.println(y);
        switch(direction){
            case 4:
                if(y==29 || maze[y+1][x] == 1) break;
                position.ChangeData(x, y+1);
                g.updateMaze(y, x, y+1, x, color);
                //maze[y][x] = 0;
                //maze[y+1][x] = color;
                break;

            case 3:
                if(y==0 || maze[y-1][x] == 1) break;
                position.ChangeData(x, y-1);
                g.updateMaze(y,x,y-1,x,color);
                //maze[y][x] = 0;
                //maze[y-1][x] = color;
                break;

            case 2:
                if(x==0 || maze[y][x-1] == 1) break;
                position.ChangeData(x-1, y);
                g.updateMaze(y,x,y,x-1,color);
                //maze[y][x] = 0;
                //maze[y][x-1] = color;
                break;

            case 1:
                if(x==29 || maze[y][x+1] == 1) break;
                position.ChangeData(x+1, y);
                g.updateMaze(y,x,y,x+1,color);
                //maze[y][x] = 0;
                //maze[y][x+1] = color;
                break;
        }
        //g.updateMaze(maze);
    }

    protected void checkEndGame(){
        int x = position.getX();
        int y = position.getY();
        int ex = end_position.getX();
        int ey = end_position.getY();

        if(maze[y][x] == 2 && maze[y][x] == 3){
            playerLoses();
        }

        else if(x == ex && y == ey){
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
