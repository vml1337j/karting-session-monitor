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
}
