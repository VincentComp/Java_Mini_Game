package Game;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
//import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * A class to generate a maze map
 */
public class MazeGenerator {
    
    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension;

    /**
     * Initialize elements in maze array to 1 (wall)
     * @param dim size of the maze
     */
    MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;

        for(int i=0; i < dimension; i++)
            for(int j=0; j < dimension; j++)
                maze[i][j] = 1;
    }

    /**
     * Output the maze map as a CSV file using FileWriter
     */
    public void genCSV(){
        String csvFile = "maze.csv";

        try (FileWriter writer = new FileWriter(csvFile)) {
            for (int[] rowData : maze) {
                for (int i = 0; i < rowData.length; i++) {
                    writer.append(String.valueOf(rowData[i]));
                    if (i != rowData.length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }

            System.out.println("CSV file created successfully.");
        } catch (IOException e) {}
    }

    /**
     * Generate the maze using DFS.
     * Push first node (1,1) to a stack.
     * While the stack is not empty, pop an element from the stack,
     * check if the node is valid and not visited.
     * If true, mark the node as visited, find its neighbors and randomly push some nodes to the stack.
     *
     * Break walls randomly (7% probability) to create other possible path(s).
     */
    public void generateMaze() {
        stack.push(new Node(1,1));
        while (!stack.empty()) {
            Node next = stack.pop();
            if (validNextNode(next) && !next.visited) {
                next.visited = true;
                maze[next.y][next.x] = 0;
                ArrayList<Node> neighbors = findNeighbors(next);
                randomlyAddNodesToStack(neighbors);
            }
        }

        // Break walls randomly to create other possible path(s)
        for(int i = 1; i<dimension-1;i++){
            for(int j = 1; j<dimension-1;j++){
                if(rand.nextFloat()>0.93)//
                    maze[j][i] = 0;
            }
        }

        // Randomly pick a starting point
        ArrayList<Integer> start = new ArrayList<>();
        for(int j = 1; j< dimension-1;j++){     // Select valid starting point
            if(maze[j][0+1] == 0){
                start.add(j);
            }
        }
        int s = rand.nextInt(start.size());
        maze[start.get(rand.nextInt(start.size()))][0]=0;

        // Randomly pick an ending point
        ArrayList<Integer> end = new ArrayList<>();
        for(int j = 1; j< dimension-1;j++){     // Select valid ending point
            if(maze[j][dimension-1-1] == 0){
                end.add(j);
            }
        }
        maze[end.get(rand.nextInt(end.size()))][dimension-1]=0;
    }


    // Check if next node is valid or not
    private boolean validNextNode(Node node) {
        int numNeighboringOnes = 0;
        for (int y = node.y-1; y < node.y+2; y++) {
            for (int x = node.x-1; x < node.x+2; x++) {
                if (pointOnGrid(x, y) && pointNotNode(node, x, y) && maze[y][x] == 0) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3) && maze[node.y][node.x] != 0;
    }

    // Randomly push valid neighbors to the stack
    private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }

    // Find valid neighbors and put them to an array list
    private ArrayList<Node> findNeighbors(Node node) {
        ArrayList<Node> neighbors = new ArrayList<>();
        for (int y = node.y-1; y < node.y+2; y++) {
            for (int x = node.x-1; x < node.x+2; x++) {
                if (pointOnGrid(x, y) && pointNotCorner(node, x, y)
                    && pointNotNode(node, x, y)) {
                    neighbors.add(new Node(x, y));
                }
            }
        }
        return neighbors;
    }

    // Check if the point is inside the maze
    private Boolean pointOnGrid(int x, int y) {
        return x > 0 && y > 0 && x < dimension-1 && y < dimension-1;
    }

    // Check if the point is NOT at a corner
    private Boolean pointNotCorner(Node node, int x, int y) {
        return (x == node.x || y == node.y);
    }

    // Check if a node is modified
    private Boolean pointNotNode(Node node, int x, int y) {
        return !(x == node.x && y == node.y);
    }

    /**
     * Accessor: get the maze array
     *
     * @return maze array
     */
    public int[][] getMaze(){
        return maze;
    };
}