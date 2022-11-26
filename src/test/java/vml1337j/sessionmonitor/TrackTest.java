package vml1337j.sessionmonitor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TrackTest {

    @Test
    void shouldCreateTrackWithStartingLine() {
        Position startingLine = new Position(10, 10);
        Track track = new Track(startingLine);

        assertThat(track.hasStartingLinePosition(startingLine))
                .isTrue();
    }

    @Test
    void shouldHasOneCheckPosition() {
        Position startingLine = new Position(10, 10);
        Track track = new Track(startingLine);

        track.addCheckPosition(1, 1);

        assertThat(track.hasCheckPosition(new Position(1, 1)))
                .isTrue();
    }

    @Test
    void shouldHasTwoCheckPositions() {
        Position startingLine = new Position(10, 10);
        Track track = new Track(startingLine);

        track.addCheckPosition(1, 1);
        track.addCheckPosition(2, 2);

        assertThat(track.hasCheckPosition(new Position(1, 1)))
                .isTrue();
        assertThat(track.hasCheckPosition(new Position(2, 2)))
                .isTrue();
    }
}
