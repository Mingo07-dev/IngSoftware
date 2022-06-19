import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table extends JPanel{
    private int n;
    private int m;
    private String headers[];
    private ResultSet rs;


    public Table(String headers[],ResultSet rs) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.rs = rs;
        this.n = Main.dbms_Azienda.getResultSetRows(rs);
        this.m = Main.dbms_Azienda.getResultSetColumns(rs);
        this.headers = headers;
        this.setLayout(new GridBagLayout());
    }

    public void createTable_checkBox() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i < m ; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            this.add(new JLabel("" + this.headers[i]), gbc);
        }

        for(int i = 1; i <= n ; i++){
            for(int j = 0; j < m - 1 ; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j;
                gbc.gridy = i;
                this.add(new JLabel("" + rs.getString(j + 1)),gbc);
            }
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m - 1;
            gbc.gridy = i;
            this.add(new JCheckBox("Consegnato"), gbc);
            rs.next();
        }
    }


    //LISTENERS

}
