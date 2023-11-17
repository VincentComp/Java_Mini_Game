package Game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A class for moving Jerry according to the key press by the user
 */
 public class KeyboardListener extends KeyAdapter{

	/**
	 * A function that is triggered when user press on keyboard
	 * It moves Jerry on click according to the key that user pressed
	 * @param e the key event
	 */
	public void keyPressed(KeyEvent e){
			 if(GUI.Jerry_lock ==0) {

				 switch (e.getKeyCode()) {
					 case 39:    // -> Right
						 JerryLocation.direction = 1;
						 JerryLocation.clicked();
						 break;
					 case 38:    // -> Top
						 JerryLocation.direction = 3;
						 JerryLocation.clicked();
						 break;

					 case 37:    // -> Left
						 JerryLocation.direction = 2;
						 JerryLocation.clicked();
						 break;

					 case 40:    // -> Bottom
						 JerryLocation.direction = 4;
						 JerryLocation.clicked();
						 break;

					 default:
						 break;

				 }
			 }
	}
 }