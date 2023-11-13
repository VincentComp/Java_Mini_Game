package Game;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class JerryContrller extends JFrame {
    JerryContrller(){
        this.addKeyListener((KeyListener) new KeyboardListener());
    }
}
