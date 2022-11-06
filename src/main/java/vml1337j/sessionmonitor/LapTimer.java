package vml1337j.sessionmonitor;

import java.time.Duration;
import java.time.LocalTime;

public class LapTimer {

    private boolean isStart;
    private LocalTime startTime;
    private LocalTime stopTime;

    public LapTimer() {
        this.isStart = false;
    }

    public void start(LocalTime startTime) {
        this.isStart = true;
        this.startTime = startTime;
    }

    public void stop(LocalTime stopTime) {
        this.isStart = false;
        this.stopTime = stopTime;
    }

    public boolean isStart() {
        return isStart;
    }

    public Duration result() {
        return Duration.between(
                startTime,
                stopTime
        );
    }
}
