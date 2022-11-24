package vml1337j.sessionmonitor;

public class Tracker {
    private int x;
    private int y;

    public Tracker() {
        x = 0;
        y = 0;
    }

    public Position getPosition() {
        return new Position(x, y);
    }
}
