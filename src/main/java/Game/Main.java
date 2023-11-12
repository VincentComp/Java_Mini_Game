package Game;
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		//Creating the window with all its awesome snaky features
		MazeGenerator maze = new MazeGenerator(30);
		maze.generateMaze();
		GUI f1 = new GUI(maze.getMaze());
		
		//Setting up the window settings
		f1.setTitle("Tom and Jerry");
		f1.setSize(300,300);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

	}
}
