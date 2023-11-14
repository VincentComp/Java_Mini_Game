package Game;
import javax.swing.JFrame;
import java.awt.*;

public class FunctionCTestMain {

    public static void main(String[] args) {

        //Creating the window with all its awesome snaky features
        MazeGenerator maze = new MazeGenerator(30);
        maze.generateMaze();

        // Initial position of Jerry
        Tuple StartLocation = new Tuple(0,0);

        // Initial position of Tom / Target ending location of Jerry
        Tuple EndLocation = new Tuple(0,0);

        // Find the actual starting locations from the map
        for(int j = 0; j < 30; j++){
            if(maze.getMaze()[j][0] == 0){
                StartLocation = new Tuple(0, j);
                maze.getMaze()[j][0] = 3;
            }

            else if(maze.getMaze()[j][29] == 0){
                EndLocation = new Tuple(29, j);
                maze.getMaze()[j][29] = 2;
            }
        }

        // GUI
        GUI f1 = new GUI(maze.getMaze(), StartLocation, EndLocation);

        //Setting up the window settings
        f1.setTitle("Tom and Jerry");

        f1.setLayout(new GridLayout(30, 30));
        f1.pack();
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }
}