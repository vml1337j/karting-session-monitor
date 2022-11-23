package vml1337j.sessionmonitor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class Stopwatch {

    private LocalTime startLapAt;
    private LocalTime stopLapAt;
    private boolean isStarted;
    private int numberOfSectors;
    private List<Duration> durations;

    public Stopwatch() {
        isStarted = false;
        numberOfSectors = 1;
        durations = List.of(
                Duration.ofSeconds(15),
                Duration.ofSeconds(15)
        );
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

    public void split(LocalTime splitAt) {
        numberOfSectors = 2;
    }

    public int getNumberOfSectors() {
        return numberOfSectors;
    }

    public List<Duration> durationOfSectors() {
        return durations;
    }
}
