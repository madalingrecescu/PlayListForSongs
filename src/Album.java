import java.util.ArrayList;

public class Album {

    private String albumName;
    private ArrayList<Song> songList;


    public Album(String albumName) {
        this.albumName = albumName;
        this.songList = new ArrayList<Song>();
    }

    public void addSongToAlbum(Song song){
        if(!findSong(song)) {
            songList.add(song);
        } else {
            System.out.println("Song already exists in the album.");
        }
    }

    public boolean findSong(Song song) {
        return this.songList.contains(song);
    }
}
