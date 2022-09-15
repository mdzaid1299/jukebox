package playlist;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public interface playlistInterface
{
    void playlist(Connection con, int user_id);

    void playlist_podcast(Connection con,int user_id ) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException;

}
