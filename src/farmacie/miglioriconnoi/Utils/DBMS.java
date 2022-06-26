package farmacie.miglioriconnoi.Utils;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.exit;

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
    private PreparedStatement ps;
    private static Timer timer;
    private static boolean connectionLosed = false;
    Frame frameAttendi = new Frame();
    JDialog dialog;
    private static boolean dialogClosed = false;



    public DBMS(String databaseName,String username, String password) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        DBMS.databaseName = databaseName;
        DBMS.username = username;
        DBMS.password = password;
        url = "jdbc:mysql://localhost:3306/+";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.stmt = connection.createStatement();

        checkConnection();
    };

    private void checkConnection(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean bool;
                try {
                    bool = connection.isValid(2);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(bool){
                    System.out.println("CONNESSIONE STABILE");
                }
                else{
                    if(!connectionLosed){
                        System.out.println("CONNESSIONE SCADUTA");
                        connectionLosed = true;
                        db_Caduto();
                        timer.cancel();
                    }
                }
            }
        }, 5, 4000);
    }

    private void db_Caduto(){
        Frame frame = new Frame();
        Object[] options = {"Ricarica",
                "Esci"};
        int n = JOptionPane.showOptionDialog(frame,
                "La connessione ai server è scaduta," +
                        "vuoi provare a riconnetterti?",
                "Cercasi DBMS",  //titolo
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE,
                null,
                options,
                options[0]);
        if(n == 0){
            frame.dispose();
            riconettiDB();
        }
        else {
            exit(1);
        }
    }

    private void riconettiDB(){
        frameAttendi = new Frame();
        JOptionPane.showMessageDialog(Main.mainFrame, "Tentativo di riconnessione in corso\n          Premi 'OK' e ATTENDI", "CERCHIAMO IL DBMS", JOptionPane.WARNING_MESSAGE);
        Main.mainFrame.setEnabled(false);
        Timer timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean bool;

                try {
                    connection = DriverManager.getConnection(url,username,password);
                } catch (SQLException e) {
                    System.out.println("CONNESSIONE ANCORA NON RIPRISTINATA");
                    //throw new RuntimeException(e);
                }

                try {
                    bool = DBMS.connection.isValid(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                if(bool) {
                    Main.mainFrame.setEnabled(true);
                    connectionLosed = false;
                    //dialog.dispose();
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "Connessione Ristabilita");

                    timer1.cancel();
                    timer = new Timer();
                    checkConnection();
                }
            }
        }, 3, 2000);
    }


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

    public void setData(String query) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.ps = this.connection.prepareStatement(query);
        this.ps.executeUpdate();
    }



}
