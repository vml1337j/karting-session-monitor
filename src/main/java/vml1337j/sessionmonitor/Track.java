package vml1337j.sessionmonitor;

public class Track {
    private final Position startingLine;

    public Track(Position startingLine) {
        this.startingLine = startingLine;
    }

    public boolean hasStartingLinePosition(Position position) {
        return startingLine.equals(position);
    }
}
