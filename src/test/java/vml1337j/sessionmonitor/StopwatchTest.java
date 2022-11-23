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
        Lap lap = stopwatch.getLap();

        assertThat(lap.time())
                .isEqualTo(Duration.ofSeconds(10));
    }

    @Test
    void shouldThrowAnExceptionWhenTheStopwatchHasNotBeenStarted() {
        Stopwatch stopwatch = new Stopwatch();

        assertThat(stopwatch.isStarted())
                .isFalse();
        assertThatThrownBy(stopwatch::getLap)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldReturnLapWithOneSector() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.stop(LocalTime.of(16, 0, 30));
        Lap lap = stopwatch.getLap();

        assertThat(lap.durations())
                .containsExactly(Duration.ofSeconds(30));
    }

    @Test
    void shouldReturnLapWithTwoSector() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.split(LocalTime.of(16, 0, 15));
        stopwatch.stop(LocalTime.of(16, 0, 30));
        Lap lap = stopwatch.getLap();

        assertThat(lap.durations())
                .containsExactly(
                        Duration.ofSeconds(15),
                        Duration.ofSeconds(15)
                );
    }

    @Test
    void shouldReturnOneDurationWhichEqualLapTimeWhenTimeNotSplit() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.stop(LocalTime.of(16, 0, 30));

        assertThat(stopwatch.durationOfSectors())
                .containsExactly(
                        Duration.ofSeconds(30)
                );
    }

    @Test
    void shouldReturnDurationOfSectors() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.split(LocalTime.of(16, 0, 15));
        stopwatch.stop(LocalTime.of(16, 0, 30));

        assertThat(stopwatch.durationOfSectors())
                .containsExactly(
                        Duration.ofSeconds(15),
                        Duration.ofSeconds(15)
                );
    }

    @Test
    void shouldReturnDurationsForEachSector() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.split(LocalTime.of(16, 0, 10));
        stopwatch.split(LocalTime.of(16, 0, 20));
        stopwatch.stop(LocalTime.of(16, 0, 30));

        assertThat(stopwatch.durationOfSectors())
                .containsExactly(
                        Duration.ofSeconds(10),
                        Duration.ofSeconds(10),
                        Duration.ofSeconds(10)
                );
    }
}
