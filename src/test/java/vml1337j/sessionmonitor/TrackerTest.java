package vml1337j.sessionmonitor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackerTest {

    private final Track track = new Track(new Position(10, 10));
    private final Stopwatch stopwatch = new Stopwatch();

    @Test
    void shouldCreateTrackerWithZeroCoordinates() {
        Tracker tracker = new Tracker(track, stopwatch);

        assertThat(tracker.getPosition())
                .isEqualTo(new Position(0, 0));
    }

    @Test
    void shouldMoveTrackerTo1x1yCoordinate() {
        Tracker tracker = new Tracker(track, stopwatch);

        tracker.move(1, 1);

        assertThat(tracker.getPosition())
                .isEqualTo(new Position(1, 1));
    }

    @Test
    void shouldStartStopwatchWhenCrossStartingLine() {
        Tracker tracker = new Tracker(track, stopwatch);

        tracker.move(10, 10);

        assertThat(stopwatch.isStarted())
                .isTrue();
    }

    @Test
    void shouldStopStopwatchWhenCrossStartingLineAgain() {
        Tracker tracker = new Tracker(track, stopwatch);

        tracker.move(10, 10);
        tracker.move(10, 10);

        assertThat(stopwatch.isStarted())
                .isFalse();
    }

    @Test
    void shouldReturnLapWithTwoDurations() {
        track.addCheckPosition(15, 15);
        Tracker tracker = new Tracker(track, stopwatch);

        tracker.move(10, 10);
        tracker.move(15, 15);
        tracker.move(10, 10);
        Lap lap = stopwatch.getLap();

        assertThat(lap.durations().size())
                .isEqualTo(2);
    }

    @Test
    void shouldReturnLapWithThreeDurations() {
        track.addCheckPosition(15, 15);
        track.addCheckPosition(200, 15);
        Tracker tracker = new Tracker(track, stopwatch);

        tracker.move(10, 10);
        tracker.move(15, 15);
        tracker.move(200, 15);
        tracker.move(10, 10);
        Lap lap = stopwatch.getLap();

        assertThat(lap.durations().size())
                .isEqualTo(3);
    }
}
