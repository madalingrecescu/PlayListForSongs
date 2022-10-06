public class Song {

    private String songName;
    private double duration;

    public Song(String songName, double duration) {
        this.songName = songName;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return this.songName+": " + this.duration;
    }
}
