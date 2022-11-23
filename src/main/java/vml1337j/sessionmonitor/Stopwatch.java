package vml1337j.sessionmonitor;

import java.time.Duration;
import java.time.LocalTime;

public class Stopwatch {

    private LocalTime startLapAt;
    private LocalTime stopLapAt;
    private boolean isStarted;
    private int numberOfSectors;

    public Stopwatch() {
        isStarted = false;
        numberOfSectors = 1;
    }

    public void start(LocalTime startLapAt) {
        this.startLapAt = startLapAt;
        isStarted = true;
    }

    public void stop(LocalTime stopLapAt) {
        this.stopLapAt = stopLapAt;
        isStarted = false;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public Duration getLapTime() {
        lapIsPassed();
        return Duration.between(startLapAt, stopLapAt);
    }

    private void lapIsPassed() {
        if (!isStarted() && stopLapAt == null) {
            throw new IllegalStateException();
        }
    }

    public void split() {
        numberOfSectors = 2;
    }

    public int getNumberOfSectors() {
        return numberOfSectors;
    }
}
