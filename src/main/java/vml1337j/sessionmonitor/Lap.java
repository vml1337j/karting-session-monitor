package vml1337j.sessionmonitor;

import java.time.Duration;
import java.util.List;

public record Lap(Duration time, List<Duration> durations) {
}
