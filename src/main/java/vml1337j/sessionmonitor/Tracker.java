package vml1337j.sessionmonitor;

import java.time.LocalTime;

public class Tracker {
    private Stopwatch stopwatch;
    private Position startingLine;
    private Position position;

    public Tracker() {
        position = new Position(0, 0);
        stopwatch = new Stopwatch();
    }

    public void move(int x, int y) {
        position.change(x, y);
        checkPosition();
    }

    private void checkPosition() {
        if (position.equals(startingLine)) {
            if (!stopwatch.isStarted()) {
                stopwatch.start(LocalTime.now());
            } else {
                stopwatch.stop(LocalTime.now());
            }
        }
    }

    public void setStartingLine(int x, int y) {
        startingLine = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }
}
