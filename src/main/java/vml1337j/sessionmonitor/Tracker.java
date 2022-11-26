package vml1337j.sessionmonitor;

import java.time.LocalTime;

public class Tracker {
    private final Track track;
    private final Stopwatch stopwatch;
    private final Position position;

    public Tracker(Track track) {
        this.track = track;
        stopwatch = new Stopwatch();
        position = new Position(0, 0);
    }

    public void move(int x, int y) {
        position.change(x, y);
        checkPosition();
    }

    private void checkPosition() {
        if (track.hasStartingLinePosition(position)) {
            if (!stopwatch.isStarted()) {
                stopwatch.start(LocalTime.now());
            } else {
                stopwatch.stop(LocalTime.now());
            }
        }

        if (track.hasCheckPosition(position)) {
            stopwatch.split(LocalTime.now());
        }
    }

    public Position getPosition() {
        return position;
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }
}
