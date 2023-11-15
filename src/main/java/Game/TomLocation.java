package Game;


import javafx.scene.layout.GridPane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.Reader;
import java.util.Timer;

public class TomLocation{
    static int[][] maze;
    static GUI gui;
    static Tuple position;
    //static Tuple end_position;
    public static int direction;


    TomLocation(Tuple starting_position, Tuple ending_position, GUI g, int d) {
        maze = g.getMaze();
        gui = g;
        position = ending_position;
        //end_position = ending_position;
        direction = d;
    }



    /*
    public static void clicked(){
        if(!checkWall())
            move();
        checkEndGame();
    }

    protected static boolean checkWall(){
        int x = position.getX();
        int y = position.getY();

        switch(direction){
            case 4:
                if(y == 29 || maze[y+1][x] == 1) {             // The below block is wall
                    return true;
                }
                break;

            case 3:
                if(y == 0 || maze[y-1][x] == 1) {             // The upper block is wall
                    return true;
                }
                break;

            case 2:
                if(x == 0 || maze[y][x-1] == 1) {   // The left block is wall or out of boundary
                    return true;
                }
                break;

            case 1:
                if(x == 29 || maze[y][x+1] == 1) {             // The right block is wall
                    return true;
                }
                break;
        }
        return false;
    }*/


    // 1: Right, 2: Left, 3:Top, 4: Bottom
    protected static void move(){
        if(GUI.Tom_lock == 0) {


            switch (ShortestPath.get_Path(maze, position.getX(), position.getY(), JerryLocation.position.getX(), JerryLocation.position.getY())) {
                case UP:
                    direction = 3;
                    break;
                case DOWN:
                    direction = 4;
                    break;
                case LEFT:
                    direction = 2;
                    break;
                case RIGHT:
                    direction = 1;
                    break;
            }


            int x = position.getX();
            int y = position.getY();


            //Jsut for debug
            if(x ==0 && y==0){
                GUI.cell[0][0].setBackground(Color.RED);

                Tuple EndLocation = new Tuple(0,0);

                for(int j = 0; j < 30; j++){

                    System.out.println(maze[j][29]);
                    if(maze[j][29] == 0){

                        EndLocation = new Tuple(29, j);
                    }
                }

                x = EndLocation.getX();
                y = EndLocation.getY();
                System.out.println("(Solved)");
                position = new Tuple(x,y);
            }

            int color = 2;          // Color for Tom

            switch (direction) {
                case 4:
                    if (maze[y + 1][x] == 3) playerLoses();
                    position.ChangeData(x, y + 1);            // Move up
                    gui.updateMaze(y, x, y + 1, x, color);
                    break;

                case 3:
                    if (maze[y - 1][x] == 3) playerLoses();
                    position.ChangeData(x, y - 1);            // Move down
                    gui.updateMaze(y, x, y - 1, x, color);
                    break;

                case 2:
                    if (maze[y][x - 1] == 3) playerLoses();
                    position.ChangeData(x - 1, y);            // Move left
                    gui.updateMaze(y, x, y, x - 1, color);
                    break;

                case 1:
                    if (maze[y][x + 1] == 3) playerLoses();
                    position.ChangeData(x + 1, y);            // Move right
                    gui.updateMaze(y, x, y, x + 1, color);
                    break;
            }

            checkEndGame();
        }
    }



    protected static void checkEndGame(){
        int x = JerryLocation.position.getX();
        int y = JerryLocation.position.getY();
        int ex = JerryLocation.end_position.getX();
        int ey = JerryLocation.end_position.getY();



        if(x == ex && y == ey){
            playerWins();
        }
    }

    protected static void playerWins(){
        System.out.println("You Win!");
        GUI.Jerry_lock =1;
        GUI.Tom_lock = 1;

        GUI.timer.cancel();
        GUI.timer = new Timer();

        try{
        JPanel panel = new JPanel();

        MainGame.f1.setContentPane(panel);
        MainGame.f1.getContentPane().removeAll();
        MainGame.f1.getContentPane().setBackground(Color.ORANGE);
        JLabel label = new JLabel("You Win", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 100));
        MainGame.f1.getContentPane().add(label);



        MainGame.f1.revalidate();
        MainGame.f1.repaint();}catch(Exception e){
            System.out.println("It may has runtime excpeiton");
        }

    }

    protected static void playerLoses(){
        System.out.println("You Lose!");

        GUI.timer.cancel();
        GUI.timer = new Timer();

        GUI.Jerry_lock =1;
        GUI.Tom_lock = 1;

        try {
            //update gui
            JPanel panel = new JPanel();
            MainGame.f1.setContentPane(panel);
            MainGame.f1.getContentPane().removeAll();
            MainGame.f1.getContentPane().setBackground(Color.BLUE);
            JLabel label = new JLabel("You lose", SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 100));
            label.setForeground(Color.WHITE);
            MainGame.f1.getContentPane().add(label);


            MainGame.f1.revalidate();
            MainGame.f1.repaint();
        }catch (Exception e){
            System.out.println("It may has runtime excpeiton");
        }







    }

    public Tuple getPosition(){
        return position;
    }
}
