/**
 * This class represents the coordinates of a client/ depot
 */
public class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX() {
        x++;
    }

    public void setY() {
        y++;
    }
}
