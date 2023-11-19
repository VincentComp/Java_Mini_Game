package Game;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;

/**
 * A class for updating the position of Tom
 */
public class TomLocation{
    static int[][] maze;
    static GUI gui;
    /**
     * A tuple to store the current position of Tom
     */
    static Tuple position;

    /**
     * An integer to store the moving direction of Tom
     * 1: Right
     * 2: Left
     * 3: Up
     * 4. Down
     */
    public static int direction;

    /**
     * Constructor for TomLocation
     * Initializes the initial position and direction of Tom
     *
     * @param ending_position   To initialize the starting position of Tom to be the exit of the maze
     * @param g GUi
     * @param d Initial direction of Tom
     */
    TomLocation(Tuple ending_position, GUI g, int d) {
        maze = g.getMaze();
        gui = g;
        position = ending_position;
        direction = d;
    }

    /**
     * A function for moving Tom according to the direction provided by the shortest path
     * It also checks whether it catches in this move
     * If it catches after the move, the player loses
     * 1: Right
     * 2: Left
     * 3: Up
     * 4: Down
     */
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


            //Just for debug
            if(x ==0 && y==0){
                //GUI.cell[0][0].setBackground(Color.RED);

                Tuple EndLocation = new Tuple(0,0);

                for(int j = 0; j < 30; j++){

                    System.out.println(maze[j][29]);
                    if(maze[j][29] == 0){

                        EndLocation = new Tuple(29, j);
                    }
                }



                x = EndLocation.getX();
                y = EndLocation.getY();
                System.out.println("(Solved)");
                position = new Tuple(x,y);

                JerryLocation.end_position = new Tuple(x,y);
            }

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

    /**
     * A function to check whether Jerry wins
     * Checks the position of Jerry and the position of the exit
     */
    protected static void checkEndGame(){
        int x = JerryLocation.position.getX();
        int y = JerryLocation.position.getY();
        int ex = JerryLocation.end_position.getX();
        int ey = JerryLocation.end_position.getY();

        if(x == ex && y == ey){
            playerWins();
        }
    }

    /**
     * A function that is triggered when Jerry reaches the exit
     * Prints("You Win!")
     */
    protected static void playerWins(){
        System.out.println("You Win!");
        GUI.Jerry_lock =1;
        GUI.Tom_lock = 1;

        GUI.timer.cancel();
        GUI.timer = new Timer();

        try{
        JPanel panel = new JPanel();

        MainGame.f1.setContentPane(panel);
        MainGame.f1.getContentPane().removeAll();
        MainGame.f1.getContentPane().setBackground(Color.ORANGE);
        JLabel label = new JLabel("You Win", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 100));
        MainGame.f1.getContentPane().add(label);



        MainGame.f1.revalidate();
        MainGame.f1.repaint();}catch(Exception e){
            //System.out.println("It may has runtime excpeiton");
        }
    }

    /**
     * A function that is triggered when Tom catches Jerry
     * Prints("You Lose!")
     */
    protected static void playerLoses(){
        System.out.println("You Lose!");

        GUI.timer.cancel();
        GUI.timer = new Timer();

        GUI.Jerry_lock =1;
        GUI.Tom_lock = 1;

        try {
            //update gui
            JPanel panel = new JPanel();
            MainGame.f1.setContentPane(panel);
            MainGame.f1.getContentPane().removeAll();
            MainGame.f1.getContentPane().setBackground(Color.BLUE);
            JLabel label = new JLabel("You lose", SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 100));
            label.setForeground(Color.WHITE);
            MainGame.f1.getContentPane().add(label);


            MainGame.f1.revalidate();
            MainGame.f1.repaint();
        }catch (Exception e){
            //System.out.println("It may has runtime excpeiton");
        }
    }
}
