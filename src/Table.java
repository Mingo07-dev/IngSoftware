import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table extends JPanel{
    private int n;
    private int m;
    private String headers[];
    private ResultSet rs;
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border borderHeader = BorderFactory.createLineBorder(Color.BLACK, 8);


    public Table(String headers[],ResultSet rs) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.rs = rs;
        this.n = Main.dbms_Azienda.getResultSetRows(rs);
        this.m = Main.dbms_Azienda.getResultSetColumns(rs);
        this.headers = headers;
        this.setLayout(new GridBagLayout());
    }



    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE
    public void fillTable_onlyData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i < this.headers.length ; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 2)));
                this.add(bordoData, gbc);
            }
            rs.next();
        }
    }



    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E UN BOTTONE
    public void fillTable_oneButton(String buttonName, int listener) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i < this.headers.length ; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m - 1 ; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 2)));
                this.add(bordoData, gbc);
            }
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m - 1;
            gbc.gridy = i +1 ;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            Button button = new Button(buttonName,150,25);
            bordo.add(button);
            this.add(bordo, gbc);
            addListener(button, listener);
            rs.next();
        }
    }





    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E TRE BOTTONI
    public void fillTable_threeButton(String buttonName_One, String buttonName_Two, String buttonName_Three, int listener_One, int listener_Two, int listener_Three) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();

        for(int i = 0; i < this.headers.length ; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }


        int k = 0;
        for(int i = 0; i < n ; i++){

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            Button button_One = new Button(rs.getString(k + 1),150,25);
            bordo.add(button_One);
            this.add(bordo, gbc);
            addListener(button_One, listener_One);

            for(int j = 0; j < m - 1 ; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j + 1;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 2)));
                this.add(bordoData, gbc);
            }

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m;
            gbc.gridy = i + 1;
            JPanel bordo2 = new JPanel(new FlowLayout());
            bordo2.setBorder(border);
            Button button_Two = new Button(buttonName_Two,150,25);
            bordo2.add(button_Two);
            this.add(bordo2, gbc);
            addListener(button_Two, listener_Two);

            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m + 1;
            gbc.gridy = i + 1;
            JPanel bordo3 = new JPanel(new FlowLayout());
            bordo3.setBorder(border);
            Button button_Three = new Button(buttonName_Three,150,25);
            bordo3.add(button_Three);
            this.add(bordo3, gbc);
            addListener(button_Three, listener_Three);

            rs.next();
        }
    }




    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E UN CAMPO DI INSERIMENTO TESTO
    public void fillTable_oneEditText(String buttonName, int listener) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        for(int i = 0; i < this.headers.length ; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m - 1 ; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 2)));
                this.add(bordoData, gbc);
            }
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m - 1;
            gbc.gridy = i +1 ;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);

            rs.next();
        }
    }




    //LISTENERS
    //esempio:
    //button.addActionListener(e -> {
    //            QUELLO CHE DEVE FARE IL BOTTONE
    //        });
    public void addListener(Button button, int listenerType){
        switch(listenerType){
            case 1:
                button.addActionListener(e -> {
                    try {
                        Main.dbms_Azienda.setData("UPDATE dbms_azienda.elenco_consegne SET Stato_consegna = '1' WHERE dbms_azienda.elenco_consegne.Id_ordine = '"+ this.rs.getString("Id_ordine")+"';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    Main.cardLayout.show(Main.mainPanel, "SchermataConsegne");
                });
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
        }
    }
}
