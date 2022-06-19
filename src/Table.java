import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Table extends JPanel{
    private int n;
    private int m;
    private String headers[];



    public Table(int rows, int column, String headers[]) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.n = Main.dbms_Azienda.getResultSetRows(Main.dbms_Azienda.getAll());
        this.m = Main.dbms_Azienda.getResultSetColumns(Main.dbms_Azienda.getAll());
        this.headers = headers;
        this.setLayout(new GridBagLayout());
        createTable_checkBox();
    }

    private void createTable_checkBox() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
            for(int j = 0; j < m ; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j;
                gbc.gridy = i;
                String element = "" + Main.dbms_Azienda.getAll().getString(j + 1);
                this.add(new JLabel(element),gbc);
            }
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m - 1;
            gbc.gridy = i;
            this.add(new JCheckBox("Consegnato"), gbc);
            Main.dbms_Azienda.getAll().next();
        }
    }

}
