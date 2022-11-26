package vml1337j.sessionmonitor;

import java.util.HashSet;
import java.util.Set;

public class Track {
    private final Position startingLine;
    private final Set<Position> positions = new HashSet<>();

    public Track(Position startingLine) {
        this.startingLine = startingLine;
    }

    public void addCheckPosition(int x, int y) {
        positions.add(new Position(x, y));
    }

    public boolean hasStartingLinePosition(Position position) {
        return startingLine.equals(position);
    }

    public boolean hasCheckPosition(Position position) {
        return positions.contains(position);
    }
}
