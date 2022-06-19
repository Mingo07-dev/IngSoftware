import javax.swing.*;
import java.awt.*;

public class Table extends JPanel{
    private int n;
    private int m;
    private String headers[];
    private String matrix_Data[];


    public Table(int rows, int column, String headers[], String matrix_Data[]){
        this.n = rows;
        this.m = column;
        this.headers = headers;
        this.matrix_Data = matrix_Data;

        this.setLayout(new GridBagLayout());
        createTable_checkBox();
    }

    private void createTable_checkBox(){
        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i < m ; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            this.add(new JLabel("" + headers[i]), gbc);
        }

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < m - 1; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j;
                gbc.gridy = i;
                this.add(new JLabel("" + matrix_Data[j]), gbc);
            }
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m - 1;
            gbc.gridy = i;
            this.add(new JCheckBox("Consegnato"), gbc);
        }
    }

}
