package Game;

/**
 * A class that stores the x-coordinate and y-coordinate of position
 */
public class Tuple {

	/**
	 * x-coordinate of the position
	 */
	public  int x;

	/**
	 * y-coordinate of the position
	 */
	public  int y;

	/**
	 * Constructor of Tuple
	 * @param x	x-coordinate of position
	 * @param y	y-coordinate of position
	 */
	public Tuple(int x, int y){
		this.x = x;
	    this.y = y; 
	}

	/**
	 * A function to change the data of Tuple
	 * edit x and y according to the input x and y
	 * @param x	new x-coordinate
	 * @param y new y-coordinate
	 */
	public void ChangeData(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * A function that returns the x-coordinate
	 * @return x
	 */
	public int getX(){
		return x;
	}

	/**
	 * A function that returns the y-coordinate
	 * @return y
	 */
	public int getY(){
		return y;
	}
} 