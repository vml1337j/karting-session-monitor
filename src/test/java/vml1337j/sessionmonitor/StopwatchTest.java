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

        assertThat(stopwatch.getLap())
                .isEqualTo(new Lap(Duration.ofSeconds(10)));
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
        stopwatch.stop(LocalTime.of(16, 0, 30));

        assertThat(stopwatch.getNumberOfSectors())
                .isEqualTo(1);
    }

    @Test
    void shouldAddSector() {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start(LocalTime.of(16, 0, 0));
        stopwatch.split(LocalTime.of(16, 0, 10));
        stopwatch.stop(LocalTime.of(16, 0, 30));

        assertThat(stopwatch.getNumberOfSectors())
                .isEqualTo(2);
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
