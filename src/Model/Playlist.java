package Model;

public class Playlist {
    // private int playlist_id;
    private String playlist_name;
    private int userid;
    private int songid;

    public Playlist(String playlist_name, int userid, int songid) {
        this.playlist_name = playlist_name;
        this.userid = userid;
        this.songid = songid;
    }

    // private int songid;

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
    public void setSongid(int songid)
    {
        this.songid=songid;
    }
    public int getSongid()
    {
        return songid;
    }
    @Override
    public String toString() {
        return "Playlist{" +
                "playlist_name='" + playlist_name + '\'' +
                ", userid=" + userid +
                ", songid=" + songid +
                '}';
    }

    public Playlist(int userid, int songid) {
        this.userid = userid;
        this.songid = songid;
    }
    public Playlist(int songid,String playlistname)
    {
        this.playlist_name=playlistname;
        this.songid=songid;
    }

}
