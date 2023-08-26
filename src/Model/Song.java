package Model;

public class Song {
    private int song_id;
    private String song_name;
    private String artist_name;
    private String album_name;
    private String genre;
    private String url;
    private String duration;

    public int getSong_id() {

        return song_id;
    }

    public void setSong_id(int song_id) {

        this.song_id = song_id;
    }

    public String getSong_name() {

        return song_name;
    }

    public void setSong_name(String song_name) {

        this.song_name = song_name;
    }

    public String getArtist_name() {

        return artist_name;
    }

    public void setArtist_name(String artist_name)
    {
        this.artist_name = artist_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name)
    {

        this.album_name = album_name;
    }

    public String getGenre()
    {
        return genre;
    }

    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song{" +
                "song_id=" + song_id +
                ", song_name='" + song_name + '\'' +
                ", artist_name='" + artist_name + '\'' +
                ", album_name='" + album_name + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Song(int song_id, String song_name, String artist_name, String album_name, String genre, String url, String duration) {
        this.song_id = song_id;
        this.song_name = song_name;
        this.artist_name = artist_name;
        this.album_name = album_name;
        this.genre = genre;
        this.url = url;
        this.duration = duration;
    }
    public Song()
    {

    }
}
