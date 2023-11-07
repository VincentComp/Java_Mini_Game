package Game;

import javax.swing.*;
import java.awt.*;

public class FuncATestMain {
    public static final int MAZE_SIZE = 30;
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(MAZE_SIZE);
        mazeGenerator.generateMaze();
        mazeGenerator.genCSV();

        GUI frame = new GUI(mazeGenerator.getMaze());
        frame.setTitle("Tom and Jerry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(30, 30));
        frame.pack();
        frame.setVisible(true);

        System.out.println("RAW MAZE\n" + mazeGenerator.getRawMaze());
        System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMaze());
    }
}