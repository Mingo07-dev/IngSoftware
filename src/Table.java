import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Table extends JPanel{
    private int n;
    private int m;
    private String headers[];
    private ResultSet rs;
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border borderHeader = BorderFactory.createLineBorder(Color.BLACK, 8);
    public static int Id_ordine = 0;


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
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
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
            for(int j = 0; j < m ; j++){
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 20;
                gbc.ipady = 20;
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m;
            gbc.gridy = i +1 ;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            Button button = new Button(buttonName,150,25);
            bordo.add(button);
            this.add(bordo, gbc);
            addListener(button, listener, rs);
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
            addListener(button_One, listener_One, rs);

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
            addListener(button_Two, listener_Two,rs);

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
            addListener(button_Three, listener_Three, rs);

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
    public void addListener(Button button, int listenerType, ResultSet rs1){
        switch(listenerType){
            case 1://BOTTONE CONFERMA CONSEGNA
                button.addActionListener(e -> {
                    try {
                        rs1.first();
                        Main.dbms_Azienda.setData("UPDATE dbms_azienda.elenco_consegne SET Stato_consegna = '1' WHERE dbms_azienda.elenco_consegne.Id_ordine = '"+ rs1.getInt("Id_ordine")+"';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    //AGGIORNA TABELLA
                    Main.schermataConsegnePanel.removeAll();
                    try {
                        SchermataConsegne schermataConsegne = new SchermataConsegne();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.schermataConsegnePanel.repaint();
                    Main.mainFrame.setVisible(true);

                });
            case 2: //BOTTONE VISUALIZZA DETTAGLIO ORDINE
                button.addActionListener(e -> {
                    try {
                        Id_ordine = rs1.getInt(1);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    Main.cardLayout.show(Main.schermataVisualizzaDettaglioOrdinePanel, "SchermataVisualizzaDettaglioOrdine");
                });
            case 3: //BOTTONE CARICO SCORTE
                button.addActionListener(e -> {
                    Main.cardLayout.show(Main.mainPanel, "SchermataCaricoScorte");
                    Button.lastView = "SchermataFarmacista";
                });
                break;
            case 4:

                break;
        }
    }
}
