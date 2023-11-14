package Game;


public class TomLocation{
    static int[][] maze;
    static GUI gui;
    static Tuple position;
    //static Tuple end_position;
    public static int direction;


    TomLocation(Tuple starting_position, Tuple ending_position, GUI g, int d) {
        maze = g.getMaze();
        gui = g;
        position = ending_position;
        //end_position = ending_position;
        direction = d;
    }



    /*
    public static void clicked(){
        if(!checkWall())
            move();
        checkEndGame();
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
    }*/


    // 1: Right, 2: Left, 3:Top, 4: Bottom
    protected static void move(){
        if(GUI.Tom_lock == 0) {


            switch (ShortestPath.get_Path(maze, position.getX(), position.getY(), JerryLocation.position.getX(), JerryLocation.position.getY())) {
                case UP:
                    direction = 3;
                    break;
                case DOWN:
                    direction = 4;
                    break;
                case LEFT:
                    direction = 2;
                    break;
                case RIGHT:
                    direction = 1;
                    break;
            }


            int x = position.getX();
            int y = position.getY();
            int color = 2;          // Color for Tom

            switch (direction) {
                case 4:
                    if (maze[y + 1][x] == 3) playerLoses();
                    position.ChangeData(x, y + 1);            // Move up
                    gui.updateMaze(y, x, y + 1, x, color);
                    break;

                case 3:
                    if (maze[y - 1][x] == 3) playerLoses();
                    position.ChangeData(x, y - 1);            // Move down
                    gui.updateMaze(y, x, y - 1, x, color);
                    break;

                case 2:
                    if (maze[y][x - 1] == 3) playerLoses();
                    position.ChangeData(x - 1, y);            // Move left
                    gui.updateMaze(y, x, y, x - 1, color);
                    break;

                case 1:
                    if (maze[y][x + 1] == 3) playerLoses();
                    position.ChangeData(x + 1, y);            // Move right
                    gui.updateMaze(y, x, y, x + 1, color);
                    break;
            }

            checkEndGame();
        }
    }



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
        GUI.Tom_lock = 1;

    }

    protected static void playerLoses(){
        System.out.println("You Lose!");
        GUI.Jerry_lock =1;
        GUI.Tom_lock = 1;

    }

    public Tuple getPosition(){
        return position;
    }
}
