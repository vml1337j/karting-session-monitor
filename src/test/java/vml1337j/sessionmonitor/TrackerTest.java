package vml1337j.sessionmonitor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackerTest {

    @Test
    void shouldCreateTrackerWithZeroCoordinates() {
        Track track = new Track(new Position(10, 10));
        Tracker tracker = new Tracker(track);

        assertThat(tracker.getPosition())
                .isEqualTo(new Position(0, 0));
    }

    @Test
    void shouldMoveTrackerTo1x0yCoordinate() {
        Track track = new Track(new Position(10, 10));
        Tracker tracker = new Tracker(track);

        tracker.move(1, 1);

        assertThat(tracker.getPosition())
                .isEqualTo(new Position(1, 1));
    }

    @Test
    void shouldStartStopwatchWhenCrossStartingLineCoordinate() {
        Track track = new Track(new Position(10, 10));
        Tracker tracker = new Tracker(track);

        tracker.move(10, 10);
        Stopwatch stopwatch = tracker.getStopwatch();

        assertThat(stopwatch.isStarted())
                .isTrue();
    }

    @Test
    void shouldStopStopwatchWhenCrossStartingLineCoordinateAgain() {
        Track track = new Track(new Position(10, 10));
        Tracker tracker = new Tracker(track);

        tracker.move(10, 10);
        tracker.move(10, 10);
        Stopwatch stopwatch = tracker.getStopwatch();

        assertThat(stopwatch.isStarted())
                .isFalse();
    }

    @Test
    void shouldReturnLapWithTwoDurations() {
        Track track = new Track(new Position(10, 10));
        track.addCheckPosition(15, 15);
        Tracker tracker = new Tracker(track);

        tracker.move(10, 10);
        tracker.move(15, 15);
        tracker.move(10, 10);
        Stopwatch stopwatch = tracker.getStopwatch();
        Lap lap = stopwatch.getLap();

        assertThat(lap.durations().size())
                .isEqualTo(2);
    }
}
