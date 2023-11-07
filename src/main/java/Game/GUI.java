package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JPanel {
	private int[][] maze;
	private int cellSize;

	public GUI(int[][] maze, int cellSize) {
		this.maze = maze;
		this.cellSize = cellSize;
		int width = maze[0].length * cellSize;
		int height = maze.length * cellSize;
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	protected void paintComponent(Graphics g) {
		int Empty = 0;
		int Wall = 1;
		int Tom = 2;
		int Jerry = 3;
		//super.paintComponent(g);
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[row].length; col++) {
				int cell = maze[row][col];
				int x = col * cellSize;
				int y = row * cellSize;

				if (cell == Wall) {
					g.setColor(Color.GRAY);
					g.fillRect(x, y, cellSize, cellSize);
				}
				else if (cell == Empty){
					g.setColor(Color.WHITE);
					g.fillRect(x, y, cellSize, cellSize);
				}
				else if (cell == Tom){
					g.setColor(Color.BLUE);
					//g.drawString("T",x,y);
					g.fillRect(x, y, cellSize, cellSize);
				}
				else if (cell == Jerry){
					g.setColor(Color.ORANGE);
					//g.drawString("J",x,y);
					g.fillRect(x, y, cellSize, cellSize);
				}
			}
		}
	}
}