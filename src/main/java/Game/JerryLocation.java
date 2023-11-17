package Game;


public class JerryLocation{
    static int[][] maze;
    static GUI gui;

    /**
     * A tuple to store the current position of Jerry
     */
    static Tuple position;

    /**
     * A tuple to store the target position for Jerry to win
     */
    static Tuple end_position;

    /**
     * An integer to store the moving direction of Jerry
     * 1: Right
     * 2: Left
     * 3: Up
     * 4. Down
     */
    public static int direction;

    /**
     * Constructor for JerryLocation
     * Set the initial position and initial direction for Jerry
     *
     * @param starting_position The starting position for Jerry
     * @param ending_position   The target position for Jerry to win (get out of maze)
     * @param g GUI
     * @param d Initial Direction
     */
    JerryLocation(Tuple starting_position, Tuple ending_position, GUI g, int d) {
        maze = g.getMaze();
        gui = g;
        position = starting_position;
        end_position = new Tuple(ending_position.getX(),ending_position.getY());
        direction = d;
    }


    /**
     * A function that is triggered when user click on the keyboard to move
     * If the target position is a wall, Jerry will not move
     * If the target position is not a wall, Jerry will move and check if it meets end game conditions
     */
    public static void clicked(){
        if(!checkWall())
            move();
            TomLocation.checkEndGame();
    }

    /**
     * A function for checking if the target location is a wall or out of boundary
     * @return true if the target location is a wall or out of boundary, false otherwise
     */
    protected static boolean checkWall(){
        int x = position.getX();
        int y = position.getY();

        switch(direction){
            case 4:
                if(y == 29 || maze[y+1][x] == 1) return true;   // The below block is wall
                break;

            case 3:
                if(y == 0 || maze[y-1][x] == 1) return true;    // The upper block is wall
                break;

            case 2:
                if(x == 0 || maze[y][x-1] == 1) return true;    // The left block is wall or out of boundary
                break;

            case 1:
                if(x == 29 || maze[y][x+1] == 1) return true;   // The right block is wall
                break;
        }
        return false;
    }

    /**
     * A function for moving Jerry according to the direction input by the user
     * It also checks whether it meet Tom in this move
     * If it reaches Tom after the move, the player loses
     * 1: Right
     * 2: Left
     * 3: Up
     * 4: Down
     */
    protected static void move(){

        int x = position.getX();
        int y = position.getY();
        int color = 3;          // Color for Jerry

        switch(direction){
            case 4:
                if(maze[y+1][x] == 2) TomLocation.playerLoses();
                position.ChangeData(x, y+1);            // Move up
                gui.updateMaze(y, x, y+1, x, color);
                break;

            case 3:
                if(maze[y-1][x] == 2) TomLocation.playerLoses();
                position.ChangeData(x, y-1);            // Move down
                gui.updateMaze(y,x,y-1,x,color);
                break;

            case 2:
                if(maze[y][x-1] == 2) TomLocation.playerLoses();
                position.ChangeData(x-1, y);            // Move left
                gui.updateMaze(y,x,y,x-1,color);
                break;

            case 1:
                if(maze[y][x+1] == 2) TomLocation.playerLoses();
                position.ChangeData(x+1, y);            // Move right
                gui.updateMaze(y,x,y,x+1,color);
                break;
        }
    }
}
