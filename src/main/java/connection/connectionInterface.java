package connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface connectionInterface
{
    Connection connection() throws ClassNotFoundException, SQLException;
}
