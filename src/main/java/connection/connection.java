package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection implements connectionInterface
{
    @Override
    public Connection connection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/jukeboxpunni";
        Connection con = DriverManager.getConnection(url, "root", "admin");
        return con;
    }
}
