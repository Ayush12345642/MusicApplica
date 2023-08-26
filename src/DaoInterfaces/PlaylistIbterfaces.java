package DaoInterfaces;

import java.sql.SQLException;
import java.util.Set;

public interface PlaylistIbterfaces {
    public Set<String> getAllPlaylist(int userid)throws SQLException,ClassNotFoundException;
    public void addsongToPlaylist(int userid,String playlistname)throws SQLException,ClassNotFoundException;
    public Set<String> getAllSongsOfPlaylist(int userid)throws SQLException,ClassNotFoundException;


}


