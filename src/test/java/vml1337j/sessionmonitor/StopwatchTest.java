package vml1337j.sessionmonitor;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StopwatchTest {

    @Test
    void shouldBeStopped() {
        Stopwatch stopwatch = new Stopwatch();

        assertThat(stopwatch.isStarted())
                .isFalse();
    }

    @Test
    void shouldStartStopwatch() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));

        assertThat(stopwatch.isStarted())
                .isTrue();
    }

    @Test
    void shouldStopStopwatch() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.stop(LocalTime.of(16, 0, 10));

        assertThat(stopwatch.isStarted())
                .isFalse();
    }

    @Test
    void shouldReturnTenSecondsWhenStartAt16_00_00AndStopAt16_00_10() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.stop(LocalTime.of(16, 0, 10));

        assertThat(stopwatch.getLapTime())
                .isEqualTo(Duration.ofSeconds(10));
    }

    @Test
    void shouldThrowAnExceptionWhenTheStopwatchHasNotBeenStarted() {
        Stopwatch stopwatch = new Stopwatch();

        assertThat(stopwatch.isStarted())
                .isFalse();
        assertThatThrownBy(stopwatch::getLapTime)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldReturnOneSectorWhenLapTimeNotSplit() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.start(LocalTime.of(16, 0, 30));

        assertThat(stopwatch.getNumberOfSectors())
                .isEqualTo(1);
    }

    @Test
    void shouldAddSector() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.split();
        stopwatch.stop(LocalTime.of(16, 0, 30));

        assertThat(stopwatch.getNumberOfSectors())
                .isEqualTo(2);
    }
}
