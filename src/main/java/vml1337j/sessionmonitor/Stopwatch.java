package vml1337j.sessionmonitor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Stopwatch {

    private LocalTime startLapAt;
    private LocalTime stopLapAt;
    private boolean isStarted;
    private List<Duration> durations = new ArrayList<>();

    public Stopwatch() {
        isStarted = false;
        durations.add(Duration.ZERO);
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
        durations.add(Duration.ZERO);
    }

    public int getNumberOfSectors() {
        return durations.size();
    }

    public List<Duration> durationOfSectors() {
        return durations;
    }
}
