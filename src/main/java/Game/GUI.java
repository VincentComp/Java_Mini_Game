package Game;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

public class GUI extends JFrame {
	public JPanel[][] cell;
	private static final int MAZE_SIZE = 30;
	private static final int GRID_SIZE = 20; // Adjust this value to change the grid size

	private int[][] maze;

	public GUI(int[][] maze) {
		this.maze = maze;
		cell = new JPanel[30][30];
		paintMaze();
	}

	public GUI(int[][] maze, Tuple s, Tuple e){
		this.maze = maze;
		cell = new JPanel[30][30];
		paintMaze();
		// Initialize the location of Jerry
		JerryLocation jerry = new JerryLocation(s, e, this, 1);
		jerry.start();
		this.addKeyListener((KeyListener) new KeyboardListener());

		// Initialize the location of Tom
		TomLocation tom = new TomLocation(e, this,100, 2);
		tom.start();

		TomController tomControl = new TomController(this, jerry, tom);
		tomControl.start();
	}

	private void paintMaze() {
		// enum to increase readability
		getContentPane().removeAll();
		int Empty = 0;
		int Wall = 1;
		int Tom = 2;
		int Jerry = 3;

		// Loop through the whole maze to fill grids with the corresponding color
		for (int i = 0; i < MAZE_SIZE; i++) {
			for (int j = 0; j < MAZE_SIZE; j++) {
				//JPanel cell = new JPanel();
				cell[i][j] = new JPanel();
				cell[i][j].setPreferredSize(new Dimension(GRID_SIZE, GRID_SIZE));
				if (maze[i][j] == Wall) {
					cell[i][j].setBackground(Color.GRAY);
				}
				else if(maze[i][j] == Empty) {
					cell[i][j].setBackground(Color.WHITE);
				}
				else if(maze[i][j] == Tom) {
					cell[i][j].setBackground(Color.BLUE);
				}
				else if(maze[i][j] == Jerry) {
					cell[i][j].setBackground(Color.ORANGE);
				}

				add(cell[i][j]);
			}
		}
		pack();
		revalidate();
		repaint();
	}

	// Update Maze
	public void updateMaze(int[][] maze){

		this.maze = maze;
		paintMaze();
	}

	public void updateMaze(int row, int col, int new_row, int new_col, int color){
		maze[row][col] = 0;
		maze[new_row][new_col] = color;
		cell[row][col].setBackground(Color.WHITE);
		if(color == 2) cell[new_row][new_col].setBackground(Color.BLUE);
		if(color == 3) cell[new_row][new_col].setBackground(Color.ORANGE);
	}

	// Get Maze
	public int[][] getMaze(){
		return this.maze;
	}
}