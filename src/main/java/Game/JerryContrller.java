package Game;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class JerryContrller extends JFrame {
    JerryContrller(Tuple s, Tuple e, GUI g){
        JerryLocation jerry = new JerryLocation(s, e, g, 500, 1);
        jerry.start();
        this.addKeyListener((KeyListener) new KeyboardListener());
    }
}
