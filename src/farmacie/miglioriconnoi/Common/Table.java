package farmacie.miglioriconnoi.Common;

import farmacie.miglioriconnoi.Main;
import farmacie.miglioriconnoi.Utils.ReloadPage;

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
    public static int[] intArrayOldData;
    public static String[] stringNome;
    public static Date[] stringData;
    public static String[] principioAttivo;
    private int cont;
    private String headers[];
    private ResultSet rs;
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border borderHeader = BorderFactory.createLineBorder(Color.BLACK, 8);


    public Table(String headers[],ResultSet rs) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.rs = rs;
        this.n = Main.dbms_Azienda.getResultSetRows(rs);
        this.m = Main.dbms_Azienda.getResultSetColumns(rs);

        this.intArray = new int[this.n];
        for(int i = 0; i < this.n; i++){
            this.intArray[i] = 0;
        }

        this.intArrayOldData = new int[this.n];
        for(int i = 0; i < this.n; i++){
            this.intArrayOldData[i] = 0;
        }

        this.stringData = new Date[this.n];
        int l = 0;
        for(int i = 0; i < this.n; i++){
            this.stringData[l++] = Date.valueOf("2022-12-12");
        }
        l=0;

        this.stringNome = new String[this.n];
        for(int i = 0; i < this.n; i++){
            this.stringNome[l++] = "0";
        }

        l=0;

        this.principioAttivo = new String[this.n];
        for(int i = 0; i < this.n; i++){
            this.principioAttivo[l++] = "0";
        }
        l=0;

        this.headers = headers;
        this.setLayout(new GridBagLayout());
    }



    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE
    public void fillTable_onlyData() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        for(int i = 0; i < this.headers.length ; i++){
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m; j++){
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
    public void fillTable_oneButton(String buttonName, int listener, int buttonNumber) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        for(int i = 0; i < this.headers.length ; i++){
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m ; j++){
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i +1 ;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            Button button = new Button(buttonName,150,25, rs.getInt(buttonNumber));
            bordo.add(button);
            this.add(bordo, gbc);
            addListener(button, listener);
            rs.next();
        }
    }
    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E UN BOTTONE
    public void fillTable_oneButtonCaricoScorte(String buttonName, int listener) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        for(int i = 0; i < this.headers.length ; i++){
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }

        for(int i = 0; i < n ; i++){
            for(int j = 0; j < m ; j++){
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i +1 ;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            Button button = new Button(buttonName,150,25, rs.getInt(1));
            bordo.add(button);
            this.add(bordo, gbc);
            addListener(button, listener);
            rs.next();
        }
    }





    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E TRE BOTTONI
    public void fillTable_threeButton(String buttonName_One, String buttonName_Two, String buttonName_Three, int listener_One, int listener_Two, int listener_Three) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);

        for(int i = 0; i < this.headers.length ; i++){
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + this.headers[i]));
            this.add(bordo, gbc);
        }

        int k = 0;
        for(int i = 0; i < n ; i++){
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            Button button_One = new Button(rs.getString(k + 1),150,25, rs.getInt(1), rs.getDate(2));
            bordo.add(button_One);
            this.add(bordo, gbc);
            addListener(button_One, listener_One);

            for(int j = 0; j < m - 1 ; j++){
                gbc.gridx = j + 1;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 2)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i + 1;
            JPanel bordo2 = new JPanel(new FlowLayout());
            bordo2.setBorder(border);
            Button button_Two = new Button(buttonName_Two,150,25, rs.getInt(1), rs.getDate(2));
            bordo2.add(button_Two);
            this.add(bordo2, gbc);
            addListener(button_Two, listener_Two);


            gbc.gridx = m + 1;
            gbc.gridy = i + 1;
            JPanel bordo3 = new JPanel(new FlowLayout());
            bordo3.setBorder(border);
            Button button_Three = new Button(buttonName_Three,150,25, rs.getInt(1),rs.getDate(2));
            bordo3.add(button_Three);
            this.add(bordo3, gbc);
            addListener(button_Three, listener_Three);

            rs.next();
        }
    }




    //RIEMPE LA TABELLA CON I DATI PRESI DAL DATABASE E UN CAMPO DI INSERIMENTO TESTO
    public void fillTable_oneEditText() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        for(int i = 0; i < this.headers.length ; i++){
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
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringData[cont] = rs.getDate(4);
            stringNome[cont] = rs.getString(1);
            intArrayOldData[cont] = Integer.parseInt(rs.getString(3));
            TextField textField = new TextField(3,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArray[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArray[textField.contatore] = 0;
                    }
                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }

        cont = 0;
    }

    public void fillTable_oneEditTextModificaOrdine() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for(int i = 0; i < this.n; i++){
            this.intArray[i] = -1;
        }
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        for(int i = 0; i < this.headers.length ; i++){
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
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringData[cont] = rs.getDate(3);
            stringNome[cont] = rs.getString(1);
            intArrayOldData[cont] = Integer.parseInt(rs.getString(4));
            TextField textField = new TextField(3,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArray[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArray[textField.contatore] = -1;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }

        cont = 0;
    }

    public void fillTable_oneEditTextPrenotazioneAutomatica() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for(int i = 0; i < this.n; i++){
            this.intArray[i] = -1;
        }
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        for(int i = 0; i < this.headers.length ; i++){
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
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringNome[cont] = rs.getString(1);
            intArrayOldData[cont] = Integer.parseInt(rs.getString(3));
            TextField textField = new TextField(4,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArray[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArray[textField.contatore] = -1;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }

        cont = 0;
    }

    public void fillTable_oneEditTextCaricoScorte() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        for(int i = 0; i < this.headers.length ; i++){
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
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringNome[cont] = rs.getString(1);
            principioAttivo[cont] = rs.getString(2);
            intArrayOldData[cont] = Integer.parseInt(rs.getString(3));
            stringData[cont] = rs.getDate(4);
            TextField textField = new TextField(4,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArray[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArray[textField.contatore] = 0;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }

        cont = 0;
    }

    public void fillTable_oneEditTextPrenotazione() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        GridBagConstraints gbc = new GridBagConstraints();
        setGridBagConstraints(gbc);
        m--;
        for(int i = 0; i < this.headers.length ; i++){
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
                gbc.gridx = j;
                gbc.gridy = i + 1;
                JPanel bordoData = new JPanel(new FlowLayout());
                bordoData.setBorder(border);
                bordoData.add(new JLabel("" + rs.getString(j + 1)));
                this.add(bordoData, gbc);
            }
            gbc.gridx = m;
            gbc.gridy = i +1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            stringNome[cont] = rs.getString(1);
            principioAttivo[cont] = rs.getString(2);
            stringData[cont] = rs.getDate(3);
            intArrayOldData[cont] = Integer.parseInt(rs.getString(4));
            TextField textField = new TextField(3,"0", 150,25,cont);
            textField.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (!textField.getText().equals("")) {
                        intArray[textField.contatore] = Integer.parseInt(textField.getText());
                    }
                    else{
                        intArray[textField.contatore] = 0;
                    }

                }
            });
            bordo.add(textField, gbc);
            this.add(bordo, gbc);

            cont++;
            rs.next();
        }

        cont = 0;
    }


    //LISTENERS
    public void addListener(Button button, int listenerType){
        switch(listenerType){
            case 1://BOTTONE CONFERMA CONSEGNA
                button.addActionListener(e -> {
                    try {
                        Main.dbms_Azienda.setData("UPDATE dbms_azienda.elenco_consegne SET Stato_consegna = '1' WHERE dbms_azienda.elenco_consegne.Id_ordine = '"+ button.getId_ordine() +"';");
                        Main.dbms_Azienda.setData("UPDATE dbms_azienda.lista_ordini SET stato_ordine = '1' WHERE dbms_azienda.lista_ordini.Id_ordine = '"+ button.getId_ordine() +"';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    //AGGIORNA TABELLA CONSEGNE
                    try {
                        ReloadPage.reloadElencoConsegne();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.cardLayout.show(Main.mainPanel, "SchermataConsegne");
                });

            case 2: //BOTTONE CARICO SCORTE
                button.addActionListener(e -> {
                    try {
                        ReloadPage.reloadCaricoScorte(button.Id_ordine);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.cardLayout.show(Main.mainPanel, "SchermataCaricoScorte");
                });
                break;
            case 3: //BOTTONE VISUALIZZA DETTAGLIO ORDINE
                button.addActionListener(e -> {
                    try {
                        ReloadPage.reloadDettaglioOrdine(button.getId_ordine());
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                    Main.cardLayout.show(Main.mainPanel, "SchermataVisualizzaDettaglioOrdine");
                });
                break;
            case 4: //BOTTONE MODIFICA ORDINE
                button.addActionListener(e -> {
                    LocalDate data_consegna = button.data_consegna.toLocalDate();
                    LocalDate data_di_oggi = LocalDate.now();
                    if(data_consegna.minusDays(2).compareTo(data_di_oggi) <= 0){
                        JOptionPane.showMessageDialog(Main.mainFrame, "Impossibile eseguire l'operazione, data di consegna inferiore a due giorni.");
                    } else {

                        try {
                            ReloadPage.reloadModificaOrdine(button.getId_ordine());
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                        Main.cardLayout.show(Main.mainPanel, "SchermataModificaOrdine");
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
                        try {
                            ReloadPage.reloadSchermataVisualizzaListaOrdini();
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        Main.cardLayout.show(Main.mainPanel, "SchermataListaOrdini");
                    }
                });
                break;
            case 6://BOTTONE AGGIORNA SEGNALAZIONE
                button.addActionListener(e -> {
                    try {
                        Main.dbms_Azienda.setData("UPDATE dbms_azienda.schermata_segnalazione SET Stato_segnalazione = '1' WHERE dbms_azienda.schermata_segnalazione.Id_ordine = '" + button.getId_ordine() + "';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    //AGGIORNA TABELLA SEGNALAZIONI IRRISOLTE
                    try {
                        ReloadPage.reloadSegnalazioniIrrisolte();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    //AGGIORNA TABELLA SEGNALAZIONI RISOLTE
                    try {
                        ReloadPage.reloadSegnalazioniRisolte();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                    Main.cardLayout.show(Main.mainPanel, "SchermataSegnalazioniIrrisolte");
                });
                break;
        }
    }

    public void setGridBagConstraints(GridBagConstraints gbc){
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.ipady = 20;
    }
}
