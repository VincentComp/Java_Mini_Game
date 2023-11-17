package Game;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestForA {
   int[][] map ={  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,1,1,0,0,0,0,0,0,2},
                    {1,0,0,1,1,0,1,1,0,0,0,1,1,0,1,1,0,0,0,0,0,1,0,0,1,1,1,1,0,1},
                    {1,1,0,1,0,0,1,1,1,1,1,0,0,0,1,1,1,0,1,1,0,0,0,1,1,0,0,0,0,1},
                    {1,0,0,1,0,1,1,0,0,1,0,0,0,1,0,0,0,0,1,0,1,1,1,1,0,0,0,0,1,1},
                    {1,0,1,1,0,0,0,0,1,1,0,1,1,1,1,1,1,0,0,0,0,1,0,0,0,1,1,0,0,1},
                    {1,0,1,1,1,1,1,1,1,0,0,1,0,0,0,0,1,1,0,1,0,1,0,0,0,0,1,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,1,1,0,1,1,0,1,0,0,1,0,0,0,1,0,0,0,0,0,1},
                    {1,1,0,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,1,1,0,1,1,1,1,0,1,1,1},
                    {1,0,0,1,0,1,0,1,0,0,0,0,0,0,1,1,1,1,1,1,0,0,1,0,0,1,0,0,1,1},
                    {1,1,0,1,0,0,0,1,0,1,1,1,1,0,0,0,0,0,0,1,0,0,1,0,1,0,0,0,0,1},
                    {1,1,0,1,1,1,1,1,0,1,0,1,0,0,1,1,1,0,1,1,0,0,1,0,1,0,1,1,0,1},
                    {3,0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,1,0,0,0,1,0,1,0,1,0,0,0,0,1},
                    {1,0,1,1,1,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,1,0,0,0,1,1,0,1,1,1},
                    {1,1,1,0,0,0,1,1,0,0,0,1,1,1,0,1,0,1,1,0,1,0,1,1,0,0,0,1,0,1},
                    {1,0,0,0,1,1,1,0,0,1,1,1,1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,0,1},
                    {1,0,0,1,1,0,0,0,1,1,1,0,0,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,0,1},
                    {1,0,0,0,0,1,1,0,0,0,0,0,1,0,1,1,0,0,0,1,1,0,0,0,1,0,0,0,0,1},
                    {1,0,1,1,0,1,1,0,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,1,0,1,1,0,1},
                    {1,0,1,0,0,1,0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,1,1,1,1,0,1,0,0,1},
                    {1,0,1,0,1,1,0,1,0,0,0,1,1,0,0,0,1,1,1,1,1,0,0,0,0,0,1,0,0,1},
                    {1,0,1,0,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,0,0,1,1,1,1,0,0,1,1},
                    {1,0,0,0,0,0,0,0,1,1,1,1,0,1,0,1,0,0,0,1,1,0,1,0,0,0,1,0,1,1},
                    {1,0,1,0,0,1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,0,0,1},
                    {1,0,1,0,0,1,1,1,0,0,0,1,1,0,1,1,0,1,1,0,1,0,0,1,1,0,1,1,1,1},
                    {1,0,1,0,1,0,0,0,1,0,0,1,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1},
                    {1,0,0,0,0,1,1,0,1,1,0,0,1,1,0,1,0,1,0,1,1,0,0,1,0,1,1,0,1,1},
                    {1,0,1,1,0,0,1,0,0,0,1,0,1,1,0,0,0,1,0,0,1,0,1,1,0,1,0,0,0,1},
                    {1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,1,0,1,1,0,0,0,1,0,0,0,0,1,0,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};
    int[][] deepCopyMap(int map[][]){
        int newMap[][] = new int[30][30];
        for(int i=0; i < 30; i++) {
            newMap[i] = new int[map[i].length];
            for (int j = 0; j < 30; j++)
                newMap[i][j] = map[i][j];
        }
        return newMap;
    }
    @Test
    void testGUICon1(){
        int [][]map1 = deepCopyMap(map);
        GUI f1 = new GUI(map1);  // Target function
        assertArrayEquals(map1, f1.getMaze());
    }

    @Test
    void testGUICon2(){
        int [][]map1 = deepCopyMap(map);
        Tuple s = new Tuple(12,0);
        Tuple e = new Tuple(1,29);
        GUI f1 = new GUI(map1, s, e);    // Target function

        assertArrayEquals(map1, f1.getMaze());
    }

    @Test
    void testPaintMaze(){
        int [][]map1 = deepCopyMap(map);
        GUI f1 = new GUI(map1);
        f1.paintMaze();     // Target function
    }

    @Test
    void testUpdateMaze(){
        int[][] map1 = deepCopyMap(map);
        GUI f1 = new GUI(map1);
        map1[3][3] = 0;
        map1[4][3] = 2;
        map1[6][6] = 0;
        map1[6][5] = 3;
        f1.updateMaze(3,3,4,3,2);   // Target function
        f1.updateMaze(6,6,6,5,3);   // Target function

        assertArrayEquals(map1, f1.getMaze());
    }

    @Test
    void testMazeGenCon(){
        MazeGenerator mazeGenerator = new MazeGenerator(30);
    }

    @Test
    void testValidNextNode(){
        int map1[][] = deepCopyMap(map);
        MazeGenerator mazeGenerator = new MazeGenerator(30);
        Node node = new Node(4,2);

        // To be Done


    }

    @Test
    void testGenerateMaze(){
        MazeGenerator mazeGenerator1 = new MazeGenerator(30);
        mazeGenerator1.generateMaze();  // Target function

        MazeGenerator mazeGenerator2 = new MazeGenerator(30);
        mazeGenerator2.generateMaze();  // Target function

        assertNotEquals(mazeGenerator1.getMaze(), mazeGenerator2.getMaze());
    }

    @Test
    void testGenCSV(){
        MazeGenerator mazeGenerator = new MazeGenerator(30);
        mazeGenerator.generateMaze();
        mazeGenerator.genCSV();     // Target function

        File f = new File("maze.csv");
        assertTrue(f.exists());
    }
}
