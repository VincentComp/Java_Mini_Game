package Game;


import com.sun.tools.javac.Main;
import org.junit.jupiter.api.Test;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

public class TestAll {
    //=============================================GUI====================================================================
    int[][] map = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 2},
            {1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 1, 0, 1},
            {1, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1},
            {1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1},
            {1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1},
            {1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    int[][] deepCopyMap(int map[][]) {
        int newMap[][] = new int[30][30];
        for (int i = 0; i < 30; i++) {
            newMap[i] = new int[map[i].length];
            for (int j = 0; j < 30; j++)
                newMap[i][j] = map[i][j];
        }
        return newMap;
    }


    @Test
    void testGUICon1() {
        int[][] map1 = deepCopyMap(map);
        GUI f1 = new GUI(map1);  // Target function
        assertArrayEquals(map1, f1.getMaze());

    }

    @Test
    void testGUICon2() {
        int[][] map1 = deepCopyMap(map);
        Tuple s = new Tuple(12, 0);
        Tuple e = new Tuple(1, 29);
        GUI f1 = new GUI(map1, s, e);    // Target function

        assertArrayEquals(map1, f1.getMaze());
    }

    @Test
    void testPaintMaze() {
        int[][] map1 = deepCopyMap(map);
        GUI f1 = new GUI(map1);
        f1.paintMaze();     // Target function
    }

    @Test
    void testUpdateMaze() {
        int[][] map1 = deepCopyMap(map);
        GUI f1 = new GUI(map1);
        map1[3][3] = 0;
        map1[4][3] = 2;
        map1[6][6] = 0;
        map1[6][5] = 3;
        f1.updateMaze(3, 3, 4, 3, 2);   // Target function
        f1.updateMaze(6, 6, 6, 5, 3);   // Target function

        assertArrayEquals(map1, f1.getMaze());
    }

    @Test
    void testGetMaze() {
        MazeGenerator m = new MazeGenerator(30);
        m.getMaze();//target function
    }

    @Test
    public void testTimerSchedule() {
        MazeGenerator m = new MazeGenerator(30);
        m.generateMaze();

        int[][] maze = m.getMaze();
        Tuple s = new Tuple(0, 9);
        Tuple e = new Tuple(29, 9);

        // Create a mock instance of TomLocation
        TomLocation tomLocationMock = mock(TomLocation.class);

        // Create a GUI instance, passing the mock TomLocation
        GUI gui = new GUI(maze, s, e);

        // Create a Timer instance
        Timer timer = new Timer();

        try {
            // Schedule the TimerTask
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    tomLocationMock.move();
                }
            }, 1000, 150); //Target function

            // Wait for the expected number of invocations

            Thread.sleep(1500); // Adjust the sleep time based on the expected number of invocations


            // Verify that TomLocation.move() is called the expected number of times
            verify(tomLocationMock, times(8)).move(); // Adjust the expected number based on the timing values

            // Cancel the Timer
            timer.cancel();
        } catch (Exception ef) {
        }
    }

    //============================MazeGenerator============================


    @Test
    void testMazeGenCon() {
        MazeGenerator mazeGenerator = new MazeGenerator(30);//target function
    }


    @Test
    void testGenerateMaze() {
        MazeGenerator mazeGenerator1 = new MazeGenerator(30);
        mazeGenerator1.generateMaze();  // Target function

        MazeGenerator mazeGenerator2 = new MazeGenerator(30);
        mazeGenerator2.generateMaze();  // Target function

        assertNotEquals(mazeGenerator1.getMaze(), mazeGenerator2.getMaze());
    }

    @Test
    void testGenCSV() {
        MazeGenerator mazeGenerator = new MazeGenerator(30);
        mazeGenerator.generateMaze();
        mazeGenerator.genCSV();     // Target function

        File f = new File("maze.csv");
        assertTrue(f.exists());
    }


    @Test
    void testValidNextNode() {
        int map1[][] = deepCopyMap(map);
        MazeGenerator mazeGenerator = new MazeGenerator(30);
        Node node = new Node(4, 2);
        mazeGenerator.validNextNode(node);//target function

    }

    @Test
    void testRandomlyAddNodeToStack() {
        MazeGenerator m = new MazeGenerator(30);
        ArrayList<Node> node = new ArrayList<>();
        m.randomlyAddNodesToStack(node); //target function
    }

    @Test
    void TestFindNeighbors() {
        MazeGenerator m = new MazeGenerator(30);
        Node node = new Node(1, 1);
        m.findNeighbors(node);
    }

    MazeGenerator m = new MazeGenerator(30);

    @Test
    void TestpointOnGrid() {
        m.pointOnGrid(0, 0); // Target function
    }

    @Test
    void TestpointNotCorner() {
        m.pointNotCorner(new Node(0, 0), 0, 0);//target function
    }

    @Test
    void TestNotNode() {
        m.pointNotNode(new Node(0, 0), 0, 0);//target function
    }

    @Test
    void TestGetMaze() {
        m.getMaze(); //Target function
    }


    //=============================================Function B====================================================================

    @Test
    void TestVertexCon() {
        ShortestPath.Vertex v = new ShortestPath.Vertex(1, 2, Direction.RIGHT); //Target function
    }

    @Test
    void TestGetDirection() {
        int[][] map = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1}, {1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1}, {1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1}, {1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1}, {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1}, {1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1}, {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1}, {1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1}, {1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        assertEquals(ShortestPath.get_Path(map, 0, 2, 18, 29), Direction.RIGHT); //Target function
        assertEquals(ShortestPath.get_Path(map, 0, 0, 0, 0), Direction.LEFT); //Target function

    }

    @Test
    void TestDirectionCon() {
        Direction d = Direction.RIGHT; //target function
    }


    Direction d = Direction.RIGHT;

    @Test
    void TestgetDx() {
        d.getDx(); //Target function
    }

    @Test
    void TestgetDy() {
        d.getDy(); //Target function
    }

    //=============================================Node====================================================================
    @Test
    void TestNodeCon() {
        Node node = new Node(3, 3);//target function
    }


    //=============================================Tuple====================================================================
    @Test
    void TestTupleCon() {
        Tuple t = new Tuple(3, 1);//target function
    }

    Tuple t = new Tuple(3, 1);

    @Test
    void TestChangeDate() {
        t.ChangeData(2, 3);//target function
    }

    @Test
    void TestgetX() {
        t.getX(); //target function
    }

    @Test
    void TestgetY() {
        t.getY(); //target function
    }

    //=============================================Jerry Location====================================================================
    @Test
    void JerryLocationCon() {

        Tuple t1 = new Tuple(1, 1);
        Tuple t2 = new Tuple(29, 9);
        GUI g = new GUI(m.getMaze());
        int d = 50;
        JerryLocation j = new JerryLocation(t1, t2, g, d); //target function
    }

    @Test
    void TestClick() {
        JerryLocation.clicked(); //target function
    }

    @Test
    void TestCheckWall() {
        JerryLocation.direction = 1;
        JerryLocation.checkWall();//target function
        JerryLocation.direction = 2;
        JerryLocation.checkWall();//target function
        JerryLocation.direction = 3;
        JerryLocation.checkWall();//target function
        JerryLocation.direction = 4;
        JerryLocation.checkWall(); //target function
    }

    @Test
    void TestMove() {
        JerryLocation.position = new Tuple(5,5);
        JerryLocation.direction = 1;
        JerryLocation.move();//target function
        JerryLocation.direction = 2;
        JerryLocation.move();//target function
        JerryLocation.direction = 3;
        JerryLocation.move();//target function
        JerryLocation.direction = 4;
        JerryLocation.move();//target function
    }

    //=============================================Tom Location====================================================================
    @Test
    void TestTomLocationGenerator(){

        Tuple t1 = new Tuple(1, 1);
        Tuple t2 = new Tuple(29, 9);
        GUI g = new GUI(m.getMaze());
        int d = 50;
        TomLocation tom = new TomLocation(t1,g,d);//target function
    }

    @Test
    void TestTomMove1(){
        //Test 0,0
        GUI.Tom_lock = 0;
        TomLocation.maze[3][29] =0;
        TomLocation.position = new Tuple(0,0);
        JerryLocation.position = new Tuple(0,1);
        TomLocation.move(); //target function


        //Test Right
        TomLocation.position = new Tuple(1,2);
        JerryLocation.position = new Tuple(0,2);
        TomLocation.move();//target function

        //Test Left
        JerryLocation.position = new Tuple(1,3);
        TomLocation.position = new Tuple(0,3);
        TomLocation.move();//target function

    }

    @Test
    void TestMove2(){
        //Test UP
        JerryLocation.position = new Tuple(0,4);
        TomLocation.position = new Tuple(0,3);
        TomLocation.move();//target function

        //Test DOWN
        JerryLocation.position = new Tuple(0,2);
        TomLocation.position = new Tuple(0,3);
        TomLocation.move();//target function

    }


    @Test
    void TestCheckEndGame(){
        JerryLocation.position = new Tuple(0,0);
        JerryLocation.end_position = new Tuple(0,0);
        TomLocation.checkEndGame(); // target function
    }

    @Test
    void TestPlayerWin(){
        MainGame.f1 = new GUI(m.getMaze(), new Tuple(0,0), new Tuple(0,0));

        TomLocation.playerWins(); //target function
    }

    @Test
    void TestPlayerLoses(){
        MainGame.f1 = null;
        TomLocation.playerLoses(); //target function

        MainGame.f1 = new GUI(m.getMaze(), new Tuple(0,0), new Tuple(0,0));
        TomLocation.playerLoses(); //target function


    }

    //=============================================Keyboard Listener====================================================================
    @Test
    void TestKeyPress(){
        GUI.Jerry_lock = 0;
        int keyCode = KeyEvent.VK_RIGHT;
        KeyEvent k1 = new KeyEvent(MainGame.f1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
        KeyboardListener kbl = new KeyboardListener();
        kbl.keyPressed(k1); //Target Function


        keyCode = KeyEvent.VK_LEFT;
        k1 = new KeyEvent(MainGame.f1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
        kbl.keyPressed(k1); //Target Function

        keyCode = KeyEvent.VK_UP;
        k1 = new KeyEvent(MainGame.f1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
        kbl.keyPressed(k1); //Target Function

        keyCode = KeyEvent.VK_DOWN;
        k1 = new KeyEvent(MainGame.f1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
        kbl.keyPressed(k1); //Target Function

    }

    //=============================================Main====================================================================
    @Test
    void TestMainGameCon(){
        MainGame m = new MainGame(); //Target function
    }

    @Test
    void TestMainGamebutton(){
        MainGame m = new MainGame();
        m.button1_lock = 0;
        m.button2_lock = 0;
        m.button3_lock = 0;

        JButton source = new JButton("Function A: Maze Generator");
        ActionEvent event = new ActionEvent(source, ActionEvent.ACTION_PERFORMED, "Function A: Maze Generator");
        m.actionPerformed(event); //Target function





        source = new JButton("Function B: Shortest Path");
        event = new ActionEvent(source, ActionEvent.ACTION_PERFORMED, "Function B: Shortest Path");
        m.actionPerformed(event);//Target function

        source = new JButton("Function C: Have Fun and play");
        event = new ActionEvent(source, ActionEvent.ACTION_PERFORMED, "Function C: Have Fun and play");
        m.actionPerformed(event);//Target function
    }

    @Test
    void TestMain(){
        MainGame m = new MainGame();
        String[] args = {"a","a"};
        MainGame.main(args); //target function
    }








}


