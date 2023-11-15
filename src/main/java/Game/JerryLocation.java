package Game;


public class JerryLocation{
    static int[][] maze;
    static GUI gui;
    static Tuple position;
    static Tuple end_position;
    public static int direction;
    // Constructor of JerryLocation, stores the current position as well as the ending position
    JerryLocation(Tuple starting_position, Tuple ending_position, GUI g, int d) {
        maze = g.getMaze();
        gui = g;
        position = starting_position;
        end_position = new Tuple(ending_position.getX(),ending_position.getY());
        direction = d;
    }



    public static void clicked(){
        if(!checkWall())
            move();
            TomLocation.checkEndGame();
    }

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

    // 1: Right, 2: Left, 3:Top, 4: Bottom
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

    /*

    protected static void checkEndGame(){
        int x = JerryLocation.position.getX();
        int y = JerryLocation.position.getY();
        int ex = JerryLocation.end_position.getX();
        int ey = JerryLocation.end_position.getY();

        if(x == ex && y == ey){
            playerWins();
        }
    }


    protected static void playerWins(){
        System.out.println("You Win!");
        GUI.Jerry_lock =1;
        while(true){
            try{
                sleep(500);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    protected static void playerLoses(){
        System.out.println("You Lose!");

        while(true){
            try{
                sleep(500);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    */

    public Tuple getPosition(){
        return position;
    }
}
