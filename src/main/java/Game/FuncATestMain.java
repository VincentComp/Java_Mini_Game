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

        System.out.println("RAW MAZE\n" + mazeGenerator.getRawMaze());
        System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMaze());
    }
}