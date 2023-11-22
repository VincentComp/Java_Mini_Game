package Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;


public class MainGame extends JFrame implements ActionListener {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    private JPanel mainScene;

    public static GUI f1;//in fact this is button3
    public static GUI f2;
    public static GUI f3;//in fact this is button1

    public int button1_lock = 0;
    public int button2_lock = 0;
    public int button3_lock = 0;

    public MainGame() {


        //===============Set Window===============
        // Get the size of the PC window
        try {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int screenWidth = screenSize.width;
            int screenHeight = screenSize.height;

            // Calculate the center coordinates
            int frameWidth = 1200;  // Set your desired frame width
            int frameHeight = 900;  // Set your desired frame height
            int centerX = (screenWidth - frameWidth) / 2;
            int centerY = (screenHeight - frameHeight) / 2;

            // Set the frame size and position
            setSize(frameWidth, frameHeight);
            setLocation(centerX, centerY);
        }catch(Exception e){}
        //===============Set Window===============

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(400, 300);

        // Create the CardLayout and JPanel to hold the scenes
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);



        // Create the main page scene with three buttons

        setTitle("Tom & Jerry ©2023 Group 18");
        mainScene = new JPanel(new FlowLayout(FlowLayout.CENTER, 10000, 50));
        JButton button1 = new JButton("Function A: Maze Generator");
        JButton button2 = new JButton("Function B: Shortest Path");
        JButton button3 = new JButton("Function C: Have Fun and play");
        button1.setPreferredSize(new Dimension(500,100));
        button2.setPreferredSize(new Dimension(500,100));
        button3.setPreferredSize(new Dimension(500,100));
        button1.setFont(new Font("Press Start 2P",Font.BOLD,24));
        button2.setFont(new Font("Press Start 2P",Font.BOLD,24));
        button3.setFont(new Font("Press Start 2P",Font.BOLD,24));
        button1.setBackground(Color.ORANGE);
        button2.setBackground(Color.ORANGE);
        button3.setBackground(Color.ORANGE);
        button1.setOpaque(true);
        button2.setOpaque(true);
        button3.setOpaque(true);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        mainScene.add(button1);
        mainScene.add(button2);
        mainScene.add(button3);


        JLabel creditLabel = new JLabel("©2023 Group 18  Alex Kamus Vincent");
        creditLabel.setFont(new Font("Press Start 2P",Font.BOLD,32));

        mainScene.add(creditLabel);



        // ====================Set the background image=======================
        ImageIcon imageIcon = new ImageIcon("Main.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(1200, 900,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        JLabel background = new JLabel(imageIcon);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1200, 900));


        //==================================================
        background.setBounds(0, 0, 1200, 900);
        mainScene.setBounds(0, 0, 1200, 900);
        layeredPane.add(background, Integer.valueOf(1));
        layeredPane.add(mainScene, Integer.valueOf(2));


        cardPanel.add(layeredPane, "Main Scene");
        mainScene.setOpaque(false);



        //cardPanel.add(mainScene, "Main Scene");


        // Add the cardPanel to the JFrame
        getContentPane().add(cardPanel);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Function A: Maze Generator")) {
            if(button1_lock == 0){
                button1_lock = 1;
                button2_lock = 1;
                button3_lock = 1;
                MazeGenerator mazeGenerator = new MazeGenerator(30);
                mazeGenerator.generateMaze();
                mazeGenerator.genCSV();

                f3 = new GUI(mazeGenerator.getMaze());
                f3.setResizable(false);
                f3.setTitle("Function A: Maze Generator");
                f3.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        button1_lock = 0;
                        button2_lock = 0;
                        button3_lock = 0;
                    }
                });
            }





        } else if (e.getActionCommand().equals("Function B: Shortest Path")) {
            if(button2_lock == 0){
                button1_lock = 1;
                button2_lock = 1;
                button3_lock = 1;


                MazeGenerator mazeGenerator = new MazeGenerator(30);
                mazeGenerator.generateMaze();
                int[][] maze = mazeGenerator.getMaze();


                //=========Get start x and y ==========
                Tuple StartLocation = new Tuple(0,0);

                Tuple EndLocation = new Tuple(0,0);

                for(int j = 0; j < 30; j++){
                    if(maze[j][0] == 0){
                        StartLocation = new Tuple(0, j);
                    }

                    else if(maze[j][29] == 0){
                        EndLocation = new Tuple(29, j);
                    }
                }

                int start_x = StartLocation.getX();
                int start_y = StartLocation.getY();
                int dest_x = EndLocation.getX();
                int dest_y = EndLocation.getY();

                //=======================================


                String csvFile = "shortest path.csv";
                try (FileWriter writer = new FileWriter(csvFile)) {


                    //Modified the maze=============//
                    writer.append(start_y +"," + start_x+"\n");
                    maze[start_y][start_x] = 3;

                    while(!((start_x == dest_x) && (start_y == dest_y))){
                        Direction dir = ShortestPath.get_Path(maze,start_x,start_y,dest_x,dest_y);
                        start_x += dir.getDx();
                        start_y += dir.getDy();
                        maze[start_y][start_x] = 3;
                        writer.append(start_y +"," + start_x +"\n");//write to csv
                    }
                    //==========================

                } catch (IOException exception) {}
                //GUI==========================
                f2 = new GUI(maze);
                f2.setResizable(false);
                f2.setTitle("Function B: Shortest Path");

                f2.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        button1_lock = 0;
                        button2_lock = 0;
                        button3_lock = 0;
                    }
                });
            }
        } else if (e.getActionCommand().equals("Function C: Have Fun and play")) {
            if(button3_lock ==0){
                button1_lock = 1;
                button2_lock = 1;
                button3_lock = 1;

                //Creating the window with all its awesome snaky features
                MazeGenerator maze = new MazeGenerator(30);
                maze.generateMaze();

                // Initial position of Jerry
                Tuple StartLocation = new Tuple(0,0);

                // Initial position of Tom / Target ending location of Jerry
                Tuple EndLocation = new Tuple(0,0);

                // Find the actual starting locations from the map
                for(int j = 0; j < 30; j++){
                    if(maze.getMaze()[j][0] == 0){
                        StartLocation = new Tuple(0, j);
                        maze.getMaze()[j][0] = 3;
                    }

                    else if(maze.getMaze()[j][29] == 0){
                        EndLocation = new Tuple(29, j);
                        maze.getMaze()[j][29] = 2;
                    }
                }

                // GUI
                f1 = new GUI(maze.getMaze(), StartLocation, EndLocation);
                f1.setResizable(false);
                //Setting up the window settings
                f1.setTitle("Function C: Have Fun and play");

                f1.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        button1_lock = 0;
                        button2_lock = 0;
                        button3_lock = 0;
                        GUI.timer.cancel();
                        GUI.timer = new Timer();

                        GUI.Jerry_lock =1;
                        GUI.Tom_lock =1;
                    }
                });

            }
        }
    }

    public static void main(String[] args) {
        MainGame game = new MainGame();
        game.setVisible(true);
    }
}
