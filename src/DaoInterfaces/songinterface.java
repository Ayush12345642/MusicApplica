package DaoInterfaces;

import Model.Song;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface songinterface {
    public List<Song> displayallsong() throws SQLException, ClassNotFoundException;

    public List<Song> songlistbysongname(String songname) throws SQLException, ClassNotFoundException;

    public List<Song> songlistbygenre(String genre) throws SQLException, ClassNotFoundException;

    public List<Song> songlistbyartistname(String artistname) throws SQLException, ClassNotFoundException;

    public List<Song> songlistbyalbum(String albumname) throws SQLException, ClassNotFoundException;

    public Set<String> getAllGenre() throws SQLException, ClassNotFoundException;

    public Set<String> getAllAlbumNme() throws SQLException, ClassNotFoundException;

    public Set<String> getAllArtistName() throws SQLException, ClassNotFoundException;

    public Set<String> getAllSongName() throws SQLException, ClassNotFoundException;


    public boolean getSongPresentBysongid(int songid) throws SQLException, ClassNotFoundException;
    // public List<Song> getSongArtistname()throws SQLException,ClassNotFoundExceptio
}
