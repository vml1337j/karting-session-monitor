package vml1337j.sessionmonitor;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class LapTimerTest {

    @Test
    void shouldBeStopped() {
        LapTimer timer = new LapTimer();

        assertThat(timer.isStart())
                .isFalse();
    }

    @Test
    void shouldBeStarted() {
        LapTimer timer = new LapTimer();

        timer.start(LocalTime.now());

        assertThat(timer.isStart())
                .isTrue();
    }

    @Test
    void shouldBeStoppedWhenLapIsOver() {
        LapTimer timer = new LapTimer();

        timer.start(LocalTime.now());
        timer.stop(LocalTime.now());

        assertThat(timer.isStart())
                .isFalse();
    }

    @Test
    void shouldReturnLapTime() {
        LapTimer timer = new LapTimer();

        timer.start(LocalTime.of(10, 15, 30));
        timer.stop(LocalTime.of(10, 15, 59));

        assertThat(timer.result())
                .isEqualTo(Duration.ofSeconds(29));
    }
}
