package Game;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.xml.crypto.Data;


class Window extends JFrame{
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = 30;
	public static int height = 30;

	MazeGenerator maze;
	public Window(){

		// Generates a maze
		maze = new MazeGenerator(height);
		maze.generateMaze();

		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;
		
		// Creates Threads and its data and adds it to the arrayList
		for(int i=0;i<width;i++){
			data= new ArrayList<DataOfSquare>();
			for(int j=0;j<height;j++){
				if(i == 0 && maze.getMaze()[i][j] == 0){
					DataOfSquare c = new DataOfSquare(3);
					data.add(c);
				}
				else if (i == height-1 && maze.getMaze()[i][j] == 0){
					DataOfSquare c = new DataOfSquare(2);
					data.add(c);
				}
				else {
					DataOfSquare c = new DataOfSquare(maze.getMaze()[i][j]);
					data.add(c);
				}
			}
			Grid.add(data);
		}
		
		// Setting up the layout of the panel
		getContentPane().setLayout(new GridLayout(30,30,0,0));
		
		// Start & pauses all threads, then adds every square of each thread to the panel
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				getContentPane().add(Grid.get(i).get(j).square);
			}
		}



		for(int i = 0; i < height; i++){
			for(int j = 0; j < height; j++){
				Grid.get(i).get(j).color = maze.getMaze()[i][j];
			}
		}

		// Initial position of Jerry
		Tuple StartLocation = new Tuple(0,0);

		// Initial position of Tom / Target ending location of Jerry
		Tuple EndLocation = new Tuple(0,0);

		// Find the actual starting locations from the map
		for(int j = 0; j < height; j++){
			if(Grid.get(0).get(j).color == 0){
				StartLocation = new Tuple(0, j);
			}

			else if(Grid.get(height-1).get(j).color == 0){
				EndLocation = new Tuple(height-1, j);
			}
		}

		// passing this value to the controller
		//MovingObject Tom = new MovingObject(EndLocation, EndLocation, 400, 2);
		//Tom.start();

		//MovingObject Jerry = new MovingObject(StartLocation, EndLocation, 500, 1);
		//Jerry.start();

		// Links the window to the keyboardlistenner.
		this.addKeyListener((KeyListener) new KeyboardListener());
	}
}
