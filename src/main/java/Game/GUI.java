package Game;
import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
	private static final int MAZE_SIZE = 30;
	private static final int GRID_SIZE = 20; // Adjust this value to change the grid size

	private int[][] maze;

	public GUI(int[][] maze) {
		this.maze = maze;
		setTitle("Tom and Jerry");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(30, 30));
		setVisible(true);
		paintMaze();
	}

	private void paintMaze() {
		// enum to increase readability
		int Empty = 0;
		int Wall = 1;
		int Tom = 2;
		int Jerry = 3;

		// Loop through the whole maze to fill grids with the corresponding color
		for (int i = 0; i < MAZE_SIZE; i++) {
			for (int j = 0; j < MAZE_SIZE; j++) {
				JPanel cell = new JPanel();
				cell.setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
				if (maze[i][j] == Wall) {
					cell.setBackground(Color.GRAY);
				}
				else if(maze[i][j] == Empty) {
					cell.setBackground(Color.WHITE);
				}
				else if(maze[i][j] == Tom) {
					cell.setBackground(Color.BLUE);
				}
				else if(maze[i][j] == Jerry) {
					cell.setBackground(Color.ORANGE);
				}

				add(cell);
			}
		}
		pack();
	}

	// Update Maze
	public void updateMaze(int maze[][]){
		this.maze = maze;
	}

	// Get Maze
	public int[][] getMaze(){
		return this.maze;
	}
}