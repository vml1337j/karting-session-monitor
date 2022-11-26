package vml1337j.sessionmonitor;

import java.time.LocalTime;

public class Tracker {
    private Track track;
    private Stopwatch stopwatch;
    private Position position;

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

//        if (track.hasCheckPosition(Position)) {
//            new CrossCheckPositionAction(stopwatch);
//            crossStartingLineAction.cross();
//        }
    }

    public Position getPosition() {
        return position;
    }

    public Stopwatch getStopwatch() {
        return stopwatch;
    }
}
