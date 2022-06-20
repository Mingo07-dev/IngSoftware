import java.sql.*;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBMS {

    private static Connection connection;
    private static String url;
    public static String databaseName;
    private static String username;
    private static String password;
    public static ResultSet resultSet;
    private Statement stmt;
    private int rows;
    private int columns;




    public DBMS(String databaseName,String username, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.databaseName = databaseName;
        this.username = username;
        this.password = password;
        this.url = "jdbc:mysql://localhost:3306/+";
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(url,username,password);
        this.stmt = this.connection.createStatement();
    };


    public int getResultSetRows(ResultSet rs){
        this.rows = 0;
        try {
            rs.last();
            this.rows = rs.getRow();
            rs.first();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.rows;
    }

    public int getResultSetColumns(ResultSet rs){
        this.columns = 0;
        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            this.columns = rsmd.getColumnCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.columns;
    }

//"SELECT * FROM " + this.databaseName + ".consegne WHERE data_di_consegna = \"" + LocalDate.now() + "\";"
    public ResultSet getData(String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.stmt = this.connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        this.resultSet = this.stmt.executeQuery(query);

        return this.resultSet;
    }

}
