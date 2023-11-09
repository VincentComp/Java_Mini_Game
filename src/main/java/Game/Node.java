package Game;

public class Node {
    public final int x;
    public final int y;
    public boolean visited;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
        visited = false;
    }
}