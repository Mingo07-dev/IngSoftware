import java.sql.*;
import java.time.LocalDate;

public class DBMS {

    private static Connection connection;
    private static String databaseName = "";
    private static String url = "jdbc:mysql://localhost:3306/" + databaseName;
    private static String username = "";
    private static String password = "";




    public ResultSet getAll() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM test.consegne;");

        return resultSet;
    }
    public ResultSet getDaily() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = stmt.executeQuery("SELECT * FROM test.consegne WHERE data_di_consegna = \"" + LocalDate.now() + "\";");

        return resultSet;
    }


    public int getResultSetRows(ResultSet rs){
        int rows = 0;
        try {
            rs.last();
            rows = rs.getRow();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public int getResultSetColumns(ResultSet rs){
        int columns = 0;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            columns = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columns;
    }
}
