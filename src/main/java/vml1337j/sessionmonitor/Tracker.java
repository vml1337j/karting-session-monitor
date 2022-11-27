package vml1337j.sessionmonitor;

import java.time.LocalTime;

public class Tracker {
    private final Track track;
    private final Stopwatch stopwatch;
    private final Position position;

    public Tracker(Track track, Stopwatch stopwatch) {
        this.track = track;
        this.stopwatch = stopwatch;
        position = new Position(0, 0);
    }

    public void move(int x, int y) {
        position.change(x, y);
        isStartingLine();
        isCheckPoint();
    }

    private void isStartingLine() {
        if (track.hasStartingLinePosition(position)) {
            if (!stopwatch.isStarted()) {
                stopwatch.start(LocalTime.now());
            } else {
                stopwatch.stop(LocalTime.now());
            }
        }
    }

    private void isCheckPoint() {
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
