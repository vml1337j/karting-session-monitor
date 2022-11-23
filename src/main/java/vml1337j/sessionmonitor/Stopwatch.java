package vml1337j.sessionmonitor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Stopwatch {

    private LocalTime startLapAt;
    private LocalTime stopLapAt;
    private boolean isStarted;

    private LocalTime lastSplitTime;
    private final List<Duration> durations = new ArrayList<>();

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

    public boolean isStarted() {
        return isStarted;
    }

    public void split(LocalTime splitAt) {
        addDuration(splitAt);
        lastSplitTime = splitAt;
    }

    private void addDuration(LocalTime splitAt) {
        durations.add(Duration.between(lastSplitTime, splitAt));
    }

    public Lap getLap() {
        return new Lap(getLapTime(), durations);
    }

    private Duration getLapTime() {
        lapIsCompleted();
        return Duration.between(startLapAt, stopLapAt);
    }

    private void lapIsCompleted() {
        if (!isStarted() && stopLapAt == null) {
            throw new IllegalStateException();
        }
    }
}
