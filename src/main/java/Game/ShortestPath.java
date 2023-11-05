package Game;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestPath {

    private static class Vertex {
        /**
         * An inner Class for recording the currently searching path
         *
         * @param x the column number of the vertex
         * @param y the row number of the vertex
         * @param Dir the direction of the topology associated with the vertex
         */
        public final int x;
        public final int y;
        public final Direction Dir;

        public Vertex(int x, int y, Direction Dir) { //constructor
            this.x = x;
            this.y = y;
            this.Dir = Dir;
        }
    }

    /**
     * A Function to perform BFS for the shortest path between Tom and Jerry
     * @param map the 30*30 game map
     * @param cx  the current col-position of Tom
     * @param cy  the current row-position of Tom
     * @param mx  the current col-position of Jerry
     * @param my  the current row-position of Jerry
     * @return The direction of the shortest path from Tom
     */
    public static Direction get_Path(int[][] map, int cx, int cy, int mx, int my) {
        /**
         * @param queue The ready queue for storing the future searching vertex
         * @param discovered The hidden layer of teh map storing the discovered vertex
         */
        Queue<Vertex> queue = new ArrayDeque<>();
        boolean[][] discovered = new boolean[30][30];


        discovered[cy][cx] = true; //set initial Vertex to be discovered
        queue.add(new Vertex(cx, cy, null));//add initial Vertex to the queue

        while (!queue.isEmpty()) {
            Vertex Vertex = queue.poll(); //pop the Vertex from the front of the queue

            for (Direction dir : Direction.values()) {//For up,rigth,down,left
                int newX = Vertex.x + dir.getDx();//change x
                int newY = Vertex.y + dir.getDy();//change y
                Direction newDir = Vertex.Dir == null ? dir : Vertex.Dir; //if no direction set direction, if have just pass to child

                if (newX == mx && newY == my) {//catch the target
                    return newDir; //return the direction
                }

                if ((newX >= 0) && (newX < 30) && (newY >= 0) && (newY < 30)) { //If within the bound
                    if (!(map[newY][newX] == 1) && !discovered[newY][newX]) {//If not barrier + undiscovred
                        discovered[newY][newX] = true; //set the new vertex to be discovered
                        queue.add(new Vertex(newX, newY, newDir)); //add the new vertex to the queue
                    }
                }
            }
        }

        return null;

    }
}

/**
 * The enum class for record the direction of the vertex in shortest path
 */
enum Direction {//enum for storing enum
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}














