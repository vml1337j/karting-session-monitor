package vml1337j.sessionmonitor;

public class Tracker {
    private Stopwatch stopwatch;
    private Position startingLine;
    private Position position;

    public Tracker() {
        position = new Position(0, 0);
        stopwatch = new Stopwatch();
    }

    public Position getPosition() {
        return position;
    }

    public void move(int x, int y) {
        position.change(x, y);
    }

    public void setStartingLine(int x, int y) {
        startingLine = new Position(x, y);
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }
}
