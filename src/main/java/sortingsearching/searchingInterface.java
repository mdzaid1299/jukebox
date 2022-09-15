package sortingsearching;

import java.sql.Connection;

public interface searchingInterface
{
    void searchingSong(Connection con, int user_id );

    void searchingPodcast(Connection con ,int user_id);
}
