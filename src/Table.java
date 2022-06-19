import javax.swing.*;
import java.awt.*;

public class Table {
    JPanel viewPanel = new JPanel(new BorderLayout());

    int n = getResultSetRows(rs);
    int m = getResultSetColumns(rs);


    JPanel viewCenterPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    String headers[] = {"Nome farmacia", "Indirizzo", "Recapito telefonico", "Numero ordine", "Stato"};
        for(int i = 0; i <= m - 1; i++){
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        gbc.gridx = i;
        gbc.gridy = 0;
        viewCenterPanel.add(new JLabel("" + headers[i]), gbc);
    }
        for(int i = 1; i <= n; i++){
        for(int j = 0; j < m - 1; j++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = j;
            gbc.gridy = i;
            viewCenterPanel.add(new JLabel("" + rs.getString(j + 1)), gbc);
        }
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;
        gbc.gridx = m - 1;
        gbc.gridy = i;
        viewCenterPanel.add(new JCheckBox("Consegnato"), gbc);
        rs.next();
    }
    JScrollPane sp = new JScrollPane(viewCenterPanel);

        viewPanel.add(sp, BorderLayout.CENTER);

}
