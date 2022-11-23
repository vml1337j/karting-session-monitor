package vml1337j.sessionmonitor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Stopwatch {

    private LocalTime startLapAt;
    private LocalTime stopLapAt;
    private boolean isStarted;
    private final List<Duration> durations = new ArrayList<>();
    private LocalTime lastSplitTime;

    public Stopwatch() {
        isStarted = false;
    }

    public void start(LocalTime startLapAt) {
        this.startLapAt = startLapAt;
        lastSplitTime = startLapAt;
        isStarted = true;
    }

    public void stop(LocalTime stopLapAt) {
        this.stopLapAt = stopLapAt;
        addDuration(stopLapAt);
        isStarted = false;
    }

    private void addDuration(LocalTime splitAt) {
        durations.add(Duration.between(lastSplitTime, splitAt));
    }

    public boolean isStarted() {
        return isStarted;
    }

    private Duration getLapTime() {
        lapIsPassed();
        return Duration.between(startLapAt, stopLapAt);
    }

    private void lapIsPassed() {
        if (!isStarted() && stopLapAt == null) {
            throw new IllegalStateException();
        }
    }

    public void split(LocalTime splitAt) {
        addDuration(splitAt);
        lastSplitTime = splitAt;
    }

    public Lap getLap() {
        Lap lap = new Lap(getLapTime(), durations);
        return lap;
    }
}
