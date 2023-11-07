package Game;

import javax.swing.*;

public class FuncATestMain {
    public static void main(String[] args) {
        MazeGenerator mazeGenerator = new MazeGenerator(30);
        mazeGenerator.generateMaze();
        mazeGenerator.genCSV();

        JFrame frame = new JFrame("Tom and Jerry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI(mazeGenerator.getMaze(), 20));
        frame.pack();
        frame.setVisible(true);

        System.out.println("RAW MAZE\n" + mazeGenerator.getRawMaze());
        System.out.println("SYMBOLIC MAZE\n" + mazeGenerator.getSymbolicMaze());
    }
}