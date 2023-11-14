package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainGame extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel mainScene;
    private JPanel scene2;
    private JPanel scene3;
    private JPanel scene4;


    private JButton button1;
    private int button1_lock = 0;
    public MainGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // Create the CardLayout and JPanel to hold the scenes
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Create the main page scene with three buttons
        mainScene = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton button1 = new JButton("Page 1");
        JButton button2 = new JButton("Page 2");
        JButton button3 = new JButton("Page 3");
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        mainScene.add(button1);
        mainScene.add(button2);
        mainScene.add(button3);
        cardPanel.add(mainScene, "Main Scene");

        // Create the second scene
        scene2 = createScene("This is Page 1", "Main Scene");
        cardPanel.add(scene2, "Scene 2");

        // Create the third scene
        scene3 = createScene("This is Page 2", "Main Scene");
        cardPanel.add(scene3, "Scene 3");

        // Create the fourth scene
        scene4 = createScene("This is Page 3", "Main Scene");
        cardPanel.add(scene4, "Scene 4");

        // Add the cardPanel to the JFrame
        getContentPane().add(cardPanel);
    }

    private JPanel createScene(String labelText, String backScene) {
        JPanel scene = new JPanel(new BorderLayout());

        // Create the label and add it to the scene
        JLabel label = new JLabel(labelText, SwingConstants.CENTER);
        scene.add(label, BorderLayout.CENTER);

        // Create the back button and add it to the scene
        JButton backButton = new JButton("Back to Main Scene");
        backButton.addActionListener( e-> cardLayout.show(cardPanel, backScene));
        scene.add(backButton, BorderLayout.SOUTH);

        return scene;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Page 1")) {
            if(button1_lock == 0){
                button1_lock = 1;
                MazeGenerator mazeGenerator = new MazeGenerator(30);
                mazeGenerator.generateMaze();
                mazeGenerator.genCSV();

                GUI frame = new GUI(mazeGenerator.getMaze());
                frame.setTitle("Tom and Jerry");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new GridLayout(30, 30));
                frame.pack();
                frame.setVisible(true);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        button1_lock = 0;
                    }
                });
            }





        } else if (e.getActionCommand().equals("Page 2")) {
            cardLayout.show(cardPanel, "Scene 3");
        } else if (e.getActionCommand().equals("Page 3")) {
            cardLayout.show(cardPanel, "Scene 4");

        }
    }

    public static void main(String[] args) {
        MainGame game = new MainGame();
        game.setVisible(true);
    }
}
