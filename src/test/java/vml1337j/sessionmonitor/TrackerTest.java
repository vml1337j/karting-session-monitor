package vml1337j.sessionmonitor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackerTest {

    @Test
    void shouldCreateTrackerWithZeroCoordinates() {
        Tracker tracker = new Tracker();

        assertThat(tracker.getPosition())
                .isEqualTo(new Position(0, 0));
    }

    @Test
    void shouldMoveTrackerTo1x0yCoordinate() {
        Tracker tracker = new Tracker();

        tracker.move(1, 1);

        assertThat(tracker.getPosition())
                .isEqualTo(new Position(1, 1));
    }

    @Test
    void shouldStartStopwatchWhenCrossStartingLineCoordinate() {
        Tracker tracker = new Tracker();

        tracker.setStartingLine(10, 10);
        tracker.move(10, 10);
        Stopwatch stopwatch = tracker.getStopwatch();

        assertThat(stopwatch.isStarted())
                .isTrue();
    }
}
