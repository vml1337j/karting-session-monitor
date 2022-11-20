package vml1337j.sessionmonitor;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class LapTimerTest {
    @Test
    void shouldBeStopped() {
        LapTimer lapTimer = new LapTimer();

        assertThat(lapTimer.isStart())
                .isFalse();
    }

    @Test
    void shouldStartLapTimer() {
        LapTimer lapTimer = new LapTimer();

        lapTimer.startLap(LocalTime.of(16, 43, 57));

        assertThat(lapTimer.isStart())
                .isTrue();
    }

    @Test
    void shouldFinishLap() {
        LapTimer lapTimer = new LapTimer();

        lapTimer.startLap(LocalTime.of(16, 43, 57));
        lapTimer.finishLap(LocalTime.of(16, 53, 57));

        assertThat(lapTimer.isStart())
                .isFalse();
    }

    @Test
    void shouldHaveNotLapSectors() {
        LapTimer lapTimer = new LapTimer();

        assertThat(lapTimer.numberOfSectors())
                .isZero();
    }

    @Test
    void shouldPushLapSector() {
        LapTimer lapTimer = new LapTimer();

        lapTimer.startLap(LocalTime.of(16, 43, 57));
        lapTimer.passSector(LocalTime.of(16, 43, 57));

        assertThat(lapTimer.numberOfSectors())
                .isEqualTo(1);
    }

    @Test
    void shouldReturnTimeOfLap() {
        LapTimer lapTimer = new LapTimer();

        lapTimer.startLap(LocalTime.of(16, 43, 57));
        lapTimer.finishLap(LocalTime.of(16, 44, 57));

        assertThat(lapTimer.lapTime())
                .isEqualTo(Duration.ofSeconds(60));
    }

    @Test
    void shouldHaveOnlyOneSector() {
        LapTimer lapTimer = new LapTimer();

        lapTimer.startLap(LocalTime.of(16, 43, 57));
        lapTimer.finishLap(LocalTime.of(16, 44, 57));

        assertThat(lapTimer.passedSectors())
                .containsExactly(
                        Duration.ofSeconds(60)
                );
    }

    @Test
    void shouldPushSectorsThenGetASet() {
        {
            LapTimer lapTimer = new LapTimer();

            lapTimer.startLap(LocalTime.of(16, 43, 0));
            lapTimer.passSector(LocalTime.of(16, 43, 20));
            lapTimer.passSector(LocalTime.of(16, 43, 30));
            lapTimer.finishLap(LocalTime.of(16, 43, 57));

            assertThat(lapTimer.numberOfSectors())
                    .isEqualTo(3);
            assertThat(lapTimer.passedSectors())
                    .containsExactly(
                            Duration.ofSeconds(20),
                            Duration.ofSeconds(10),
                            Duration.ofSeconds(27)
                    );
        }
    }
}
