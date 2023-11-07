package Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
class MazeGenerator {
    
    private Stack<Node> stack = new Stack<>();
    private Random rand = new Random();
    private int[][] maze;
    private int dimension;

    MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;

        for(int i=0; i < dimension; i++)
            for(int j=0; j < dimension; j++)
                maze[i][j] = 1;
    }

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
        } catch (IOException e) {
            System.out.println("Error writing CSV file: " + e.getMessage());
        }

    }
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
        for(int i = 1; i<dimension-1;i++){
            for(int j = 1; j<dimension-1;j++){
                if(rand.nextFloat()>0.93)
                    maze[j][i] = 0;
            }
        }
        ArrayList<Integer> start = new ArrayList<>();
        for(int j = 1; j< dimension-1;j++){
            if(maze[j][0+1] == 0){
                start.add(j);
            }
        }
        int s = rand.nextInt(start.size());
        maze[start.get(rand.nextInt(start.size()))][0]=0;

        ArrayList<Integer> end = new ArrayList<>();
        for(int j = 1; j< dimension-1;j++){
            if(maze[j][dimension-1-1] == 0){
                end.add(j);
            }
        }
        maze[end.get(rand.nextInt(end.size()))][dimension-1]=0;
    }

    public String getRawMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : maze) {
            sb.append(Arrays.toString(row) + "\n");
        }
        return sb.toString();
    }

    public String getSymbolicMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                sb.append(maze[i][j] == 1 ? "*" : " ");
                sb.append("  "); 
            }
            sb.append("\n");
        }
        return sb.toString();
    }

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

    private void randomlyAddNodesToStack(ArrayList<Node> nodes) {
        int targetIndex;
        while (!nodes.isEmpty()) {
            targetIndex = rand.nextInt(nodes.size());
            stack.push(nodes.remove(targetIndex));
        }
    }

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

    private Boolean pointOnGrid(int x, int y) {
        return x > 0 && y > 0 && x < dimension-1 && y < dimension-1;
    }

    private Boolean pointNotCorner(Node node, int x, int y) {
        return (x == node.x || y == node.y);
    }
    
    private Boolean pointNotNode(Node node, int x, int y) {
        return !(x == node.x && y == node.y);
    }
}