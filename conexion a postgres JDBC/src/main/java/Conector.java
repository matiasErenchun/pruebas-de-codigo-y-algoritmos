import java.sql.*;

public class Conector
{
    private static final String JDBC_URL ="jdbc:postgresql://localhost:5432/arriendocasas";
    private static final String JDBC_USER ="postgres";
    private static final String JDBC_PASWORD ="Oni199233";

    public static Connection getConnectio() throws SQLException
    {
        return DriverManager.getConnection(JDBC_URL,JDBC_USER,JDBC_PASWORD);
    }

    public static void close(ResultSet result)
    {
        try{
            result.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement statement)
    {
        try{
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace(System.out);
        }
    }

    public static void close(Connection connection)
    {
        try{
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace(System.out);
        }
    }
}
