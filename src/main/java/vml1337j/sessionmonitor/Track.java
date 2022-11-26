package vml1337j.sessionmonitor;

public class Track {
    private final Position startingLine;

    public Track(int x, int y) {
        this.startingLine = new Position(x, y);
    }

    public boolean hasStartingLinePosition(Position position) {
        return startingLine.equals(position);
    }
}
