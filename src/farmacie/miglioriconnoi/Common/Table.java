package farmacie.miglioriconnoi.Common;


import farmacie.miglioriconnoi.GestioneConsegne.SchermataCaricoScorte;
import farmacie.miglioriconnoi.GestioneConsegne.SchermataConsegne;
import farmacie.miglioriconnoi.GestionePrenotazioni.SchermataListaOrdini;
import farmacie.miglioriconnoi.GestionePrenotazioni.SchermataModificaOrdine;
import farmacie.miglioriconnoi.GestionePrenotazioni.SchermataVisualizzaDettaglioOrdine;
import farmacie.miglioriconnoi.GestioneSegnalazioni.SchermataSegnalazioniIrrisolte;
import farmacie.miglioriconnoi.GestioneSegnalazioni.SchermataSegnalazioniRisolte;
import farmacie.miglioriconnoi.Main;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Table extends JPanel {
    public int n = 0;
    private int m;
    public static int[] intArray;
    private int[] intArrayLocal;
    public static int[] intArrayOldData;
    private int[] intArrayOldDataLocal;
    public static String[] stringNome;
    private String[] stringNomeLocal;
    public static Date[] stringData;
    private Date[] stringDataLocal;
    public static String[] principioAttivo;
    private String[] principioAttivoLocal;
    private int cont;
    private String headers[];
    private ResultSet rs;
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border borderHeader = BorderFactory.createLineBorder(Color.BLACK, 8);


    public Table(String headers[],ResultSet rs) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.rs = rs;
        this.n = Main.dbms_Azienda.getResultSetRows(rs);
        this.m = Main.dbms_Azienda.getResultSetColumns(rs);

        this.intArrayLocal = new int[this.n];
        for (int i:this.intArrayLocal
             ) {
            this.intArrayLocal[i] = 0;
        }

        this.intArrayOldDataLocal = new int[this.n];
        for (int i:this.intArrayOldDataLocal
        ) {
            this.intArrayOldDataLocal[i] = 0;
        }

        this.stringDataLocal = new Date[this.n];
        int l = 0;
        for (Date i:this.stringDataLocal
        ) {
            this.stringDataLocal[l++] = Date.valueOf("2022-12-12");
        }
        l=0;

        this.stringNomeLocal = new String[this.n];
        for (String i:this.stringNomeLocal
        ) {
            this.stringNomeLocal[l++] = "0";
        }
        l=0;

        this.principioAttivoLocal = new String[this.n];
        for (String i:this.principioAttivoLocal
        ) {
            this.principioAttivoLocal[l++] = "0";
        }
        l=0;

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
    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E UN BOTTONE
    public void fillTable_oneButtonCaricoScorte(String buttonName, int listener) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
            Button button = new Button(buttonName,150,25, rs.getInt(1));
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
            Button button_One = new Button(rs.getString(k + 1),150,25, rs.getInt(1), rs.getDate(2));
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
            Button button_Two = new Button(buttonName_Two,150,25, rs.getInt(1), rs.getDate(2));
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
            Button button_Three = new Button(buttonName_Three,150,25, rs.getInt(1),rs.getDate(2));
            bordo3.add(button_Three);
            this.add(bordo3, gbc);
            addListener(button_Three, listener_Three, rs);

            rs.next();
        }
    }




    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E UN CAMPO DI INSERIMENTO TESTO
    public void fillTable_oneEditText() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
        cont = 0;

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
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringDataLocal[cont] = rs.getDate(4);
            stringNomeLocal[cont] = rs.getString(1);
            intArrayOldDataLocal[cont] = Integer.parseInt(rs.getString(3));
            TextField textField = new TextField(10,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArrayLocal[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArrayLocal[textField.contatore] = 0;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }
        intArray = intArrayLocal;
        intArrayOldData = intArrayOldDataLocal;
        stringNome = stringNomeLocal;
        stringData = stringDataLocal;

        cont = 0;
    }

    public void fillTable_oneEditTextModificaOrdine() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
        cont = 0;

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
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringDataLocal[cont] = rs.getDate(3);
            stringNomeLocal[cont] = rs.getString(1);
            intArrayOldDataLocal[cont] = Integer.parseInt(rs.getString(4));
            TextField textField = new TextField(10,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArrayLocal[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArrayLocal[textField.contatore] = 0;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }
        intArray = intArrayLocal;
        intArrayOldData = intArrayOldDataLocal;
        stringNome = stringNomeLocal;
        stringData = stringDataLocal;

        cont = 0;
    }

    public void fillTable_oneEditTextPrenotazioneAutomatica() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
        cont = 0;

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
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringNomeLocal[cont] = rs.getString(1);
            intArrayOldDataLocal[cont] = Integer.parseInt(rs.getString(3));
            TextField textField = new TextField(10,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("") || !textField.getText().equals("0") ) {
                        intArrayLocal[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArrayLocal[textField.contatore] = 0;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }
        intArray = intArrayLocal;
        intArrayOldData = intArrayOldDataLocal;
        stringNome = stringNomeLocal;
        stringData = stringDataLocal;

        cont = 0;
    }

    public void fillTable_oneEditTextCaricoScorte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
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
        cont = 0;

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
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringNomeLocal[cont] = rs.getString(1);
            principioAttivoLocal[cont] = rs.getString(2);
            intArrayOldDataLocal[cont] = Integer.parseInt(rs.getString(3));
            stringDataLocal[cont] = rs.getDate(4);
            TextField textField = new TextField(10,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArrayLocal[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArrayLocal[textField.contatore] = 0;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }
        intArray = intArrayLocal;
        intArrayOldData = intArrayOldDataLocal;
        stringNome = stringNomeLocal;
        stringData = stringDataLocal;
        principioAttivo = principioAttivoLocal;

        cont = 0;
    }

    public void fillTable_oneEditTextPrenotazione() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        m--;
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
        cont = 0;

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
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringNomeLocal[cont] = rs.getString(1);
            principioAttivoLocal[cont] = rs.getString(2);
            stringDataLocal[cont] = rs.getDate(3);
            intArrayOldDataLocal[cont] = Integer.parseInt(rs.getString(4));
            TextField textField = new TextField(10,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArrayLocal[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArrayLocal[textField.contatore] = 0;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }
        intArray = intArrayLocal;
        stringNome = stringNomeLocal;
        stringData = stringDataLocal;
        principioAttivo = principioAttivoLocal;
        intArrayOldData = intArrayOldDataLocal;

        cont = 0;
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
                    //AGGIORNA TABELLA CONSEGNE
                    Main.schermataConsegnePanel.removeAll();
                    try {
                        SchermataConsegne schermataConsegne = new SchermataConsegne();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.schermataConsegnePanel.repaint();
                    Main.mainFrame.setVisible(true);

                });

            case 2: //BOTTONE CARICO SCORTE
                button.addActionListener(e -> {
                    Main.schermataCaricoScortePanel.removeAll();
                    try {
                        SchermataCaricoScorte schermataCaricoScorte = new SchermataCaricoScorte(button.getId_ordine());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.schermataCaricoScortePanel.repaint();
                    Main.mainFrame.setVisible(true);
                    Main.cardLayout.show(Main.mainPanel, "SchermataCaricoScorte");
                    Button.lastView = "SchermataConsegne";
                });
                break;
            case 3: //BOTTONE VISUALIZZA DETTAGLIO ORDINE
                button.addActionListener(e -> {
                    Main.schermataVisualizzaDettaglioOrdinePanel.removeAll();
                    try {
                        SchermataVisualizzaDettaglioOrdine schermataVisualizzaDettaglioOrdine = new SchermataVisualizzaDettaglioOrdine(button.getId_ordine());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.schermataVisualizzaDettaglioOrdinePanel.repaint();
                    Main.mainFrame.setVisible(true);

                    Main.cardLayout.show(Main.mainPanel, "SchermataVisualizzaDettaglioOrdine");
                    Button.lastView = "SchermataListaOrdini";
                });
                break;
            case 4: //BOTTONE MODIFICA ORDINE
                button.addActionListener(e -> {
                    LocalDate data_consegna = button.data_consegna.toLocalDate();
                    LocalDate data_di_oggi = LocalDate.now();
                    if(data_consegna.minusDays(2).compareTo(data_di_oggi) <= 0){
                        JOptionPane.showMessageDialog(Main.mainFrame, "Impossibile eseguire l'operazione, data di consegna inferiore a due giorni.");
                    } else {
                        Main.schermataModificaOrdinePanel.removeAll();
                        try {
                            SchermataModificaOrdine schermataModificaOrdine = new SchermataModificaOrdine(button.getId_ordine());
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        Main.schermataModificaOrdinePanel.repaint();
                        Main.mainFrame.setVisible(true);

                        Main.cardLayout.show(Main.mainPanel, "SchermataModificaOrdine");
                        Button.lastView = "SchermataListaOrdini";
                    }
                });
                break;
            case 5: //BOTTONE ANNULLA ORDINE
                button.addActionListener(e -> {
                    LocalDate data_consegna = button.data_consegna.toLocalDate();
                    LocalDate data_di_oggi = LocalDate.now();
                    if(data_consegna.minusDays(2).compareTo(data_di_oggi) <= 0){
                        JOptionPane.showMessageDialog(Main.mainFrame, "Impossibile eseguire l'operazione, data di consegna inferiore a due giorni.");
                    } else {
                        try {
                            Main.dbms_Azienda.setData("DELETE FROM dbms_azienda.lista_ordini WHERE (Id_ordine = '" + button.getId_ordine() + "');");
                            Main.dbms_Azienda.setData("DELETE FROM dbms_azienda.elenco_consegne WHERE (Id_ordine = '" + button.getId_ordine() + "');");
                            Main.dbms_Azienda.setData("DELETE FROM dbms_azienda.dettaglio_ordine WHERE (Id_ordine = '" + button.getId_ordine() + "');");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                            ex.printStackTrace();
                        }
                        //AGGIORNA TABELLA
                        Main.schermataListaOrdiniPanel.removeAll();
                        try {
                            SchermataListaOrdini schermataListaOrdini = new SchermataListaOrdini();
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        Main.schermataListaOrdiniPanel.repaint();
                        Main.mainFrame.setVisible(true);
                    }
                });
                break;
            case 6://BOTTONE AGGIORNA SEGNALAZIONE
                button.addActionListener(e -> {
                    try {
                        rs1.first();
                        Main.dbms_Azienda.setData("UPDATE dbms_azienda.schermata_segnalazione SET Stato_segnalazione = '1' WHERE dbms_azienda.schermata_segnalazione.Id_ordine = '" + rs1.getInt("Id_ordine") + "';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    //AGGIORNA TABELLA SEGNALAZIONI IRRISOLTE
                    Main.schermataSegnalazioniIrrisoltePanel.removeAll();
                    try {
                        SchermataSegnalazioniIrrisolte schermataSegnalazioniIrrisolte = new SchermataSegnalazioniIrrisolte();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.schermataSegnalazioniIrrisoltePanel.repaint();
                    Main.mainFrame.setVisible(true);
                    //AGGIORNA TABELLA SEGNALAZIONI RISOLTE
                    Main.schermataSegnalazioniRisoltePanel.removeAll();
                    try {
                        SchermataSegnalazioniRisolte schermataSegnalazioniRisolte = new SchermataSegnalazioniRisolte();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.schermataSegnalazioniRisoltePanel.repaint();
                    Main.mainFrame.setVisible(true);
                });
                break;
        }
    }
    //GETTER
    public static int[] getIntArray() {
        return intArray;
    }

    public static int[] getIntArrayOldData() {
        return intArrayOldData;
    }

    public static String[] getStringNome() {
        return stringNome;
    }

    public static Date[] getStringData() {
        return stringData;
    }

    public static String[] getPrincipioAttivo() {
        return principioAttivo;
    }
    //SETTER
    public static void setIntArray(int[] array) {
        intArray = array;
    }

    public static void setIntArrayOldData(int[] array) {
        intArrayOldData = array;
    }

    public static void setStringNome(String[] array) {
        stringNome = array;
    }

    public static void setStringData(Date[] array) {
        stringData = array;
    }

    public static void setPrincipioAttivo(String[] array) {
        principioAttivo = array;
    }

}