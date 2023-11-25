package Game;
import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class to display the GUI
 */
public class GUI extends JFrame {
	/**
	 * Array represents cells in grid layout
	 */
	public static JPanel cell[][];
	private static final int MAZE_SIZE = 30;
	private static final int GRID_SIZE = 20; // Adjust this value to change the grid size

	private int[][] maze;
	/**
	 * 0 if Jerry is allowed to move; 1 if Jerry is NOT allowed to move
	 */
	public static int Jerry_lock = 0;
	/**
	 * 0 if Tom is allowed to move; 1 if Tom is NOT allowed to move
	 */
	public static int Tom_lock = 0;
	/**
	 * For Tom to move periodically
	 */
	public static Timer timer = new Timer();

	/**
	 * Constructor for Function A and B.
	 * Set layout of JFrame to GridLayout.
	 * Define default close operation to DISPOSE_ON_CLOSE.
	 *
	 * @param maze the 30*30 game map
	 */
	public GUI(int[][] maze) {	//constructor 1
		this.maze = maze;
		cell = new JPanel[30][30];
		setLayout(new GridLayout(30, 30));
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		paintMaze();
	}

	/**
	 * Constructor for Function C.
	 * Set layout of JFrame to GridLayout.
	 * Define default close operation to DISPOSE_ON_CLOSE.
	 *
	 * @param maze the 30*30 game map
	 * @param s StartLocation
	 * @param e EndLocation
	 */
	public GUI(int[][] maze, Tuple s, Tuple e){	//constructor 2
		Jerry_lock =0;
		Tom_lock = 0;
		this.maze = maze;
		cell = new JPanel[30][30];
		setLayout(new GridLayout(30, 30));
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		paintMaze();

		// Initialize the location of Jerry
		JerryLocation jerry = new JerryLocation(s, e, this, 500);
		this.addKeyListener((KeyListener) new KeyboardListener());

		TomLocation tom = new TomLocation(e,this,500);

		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				TomLocation.move();
			}
		},1000 ,200);


	}

	/**
	 * Display maze map in GUI using JFrame and JPanel.
	 * Display maze map according to the following rules:
	 * Gray: Wall;
	 * White: Road;
	 * Orange: Jerry;
	 * Blue: Tom.
	 */
	public void paintMaze() {
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

	/**
	 * Update maze array and maze map in GUI.
	 * Set current maze array element to 0.
	 * Set next maze array element to color.
	 * Set the color of the current cell to white (road).
	 * Set the color of the next cell according to the type (Tom or Jerry).
	 *
	 * @param row	current row
	 * @param col	current column
	 * @param nextRow	next row
	 * @param nextCol	next column
	 * @param color	type of the object (Tom or Jerry)
	 */
	public void updateMaze(int row, int col, int nextRow, int nextCol, int color){
		maze[row][col] = 0;
		maze[nextRow][nextCol] = color;

		cell[row][col].setBackground(Color.WHITE);
		if(color == 3)
			cell[nextRow][nextCol].setBackground(Color.ORANGE);
		else if (color == 2)
			cell[nextRow][nextCol].setBackground(Color.BLUE);
	}

	/**
	 * Accessor: return the maze array.
	 *
	 * @return return the current maze array
	 */
	// Get Maze
	public int[][] getMaze(){
		return this.maze;
	}
}