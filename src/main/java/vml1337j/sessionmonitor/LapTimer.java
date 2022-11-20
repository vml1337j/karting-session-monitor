package vml1337j.sessionmonitor;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LapTimer {

    private boolean isStart = false;
    private int numberOfSectors = 0;

    private LocalTime lapStartTime = null;
    private LocalTime lapFinishTime = null;

    private LocalTime lastTimePoint = null;
    private List<Duration> sectors = new ArrayList<>();

    public boolean isStart() {
        return isStart;
    }

    public void startLap(LocalTime lapStartTime) {
        this.lapStartTime = lapStartTime;
        lastTimePoint = lapStartTime;
        isStart = true;
    }

    public void finishLap(LocalTime lapFinishTime) {
        this.lapFinishTime = lapFinishTime;
        passSector(lapFinishTime);
        isStart = false;
    }

    public int numberOfSectors() {
        return numberOfSectors;
    }

    public void passSector(LocalTime sectorPassedAt) {
        sectors.add(Duration.between(
                lastTimePoint, sectorPassedAt
        ));
        lastTimePoint = sectorPassedAt;
        numberOfSectors++;
    }

    public Duration lapTime() {
        return Duration.between(
                lapStartTime,
                lapFinishTime
        );
    }

    public List<Duration> passedSectors() {
        return sectors;
    }
}
