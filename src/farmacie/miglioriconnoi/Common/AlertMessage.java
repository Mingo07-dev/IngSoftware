package farmacie.miglioriconnoi.Common;


import farmacie.miglioriconnoi.Autenticazione.Views.SchermataLogin;
import farmacie.miglioriconnoi.GestioneConsegne.Control.ControlConsegne;
import farmacie.miglioriconnoi.GestionePrenotazioni.Views.SchermataPrenotazione;
import farmacie.miglioriconnoi.Main;
import farmacie.miglioriconnoi.Utils.DBMS;
import farmacie.miglioriconnoi.Utils.ReloadPage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertMessage {

    public static TextField nomeFarmaciaField;
    public static TextField indirizzoFarmaciaField;
    public static TextField civicoField;
    public static TextField recapitoTelefonicoField;
    public static TextField prefissoField;

    public static  JButton buttonOk = new JButton();
    public static  JButton buttonOkPrenotazione = new JButton();
    public static  JButton buttonOkParziale = new JButton();
    public static JButton buttonAnnulla = new JButton();
    public ActionListener ALP;
    public ActionListener ALPP;
    public ActionListener AC;
    private JFrame frame = new JFrame("Messaggio");
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border borderHeader = BorderFactory.createLineBorder(Color.BLACK, 8);

    public AlertMessage(String email, String password){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nomeFarmaciaField = new TextField(20, "Nome Farmacia", 50, 30);
        indirizzoFarmaciaField = new TextField(20, "Indirizzo", 50, 30);
        civicoField = new TextField(4, "0", 50, 30);
        setInputTextField(civicoField, 4);
        recapitoTelefonicoField = new TextField(10, "091987654", 50, 30);
        setInputTextField(recapitoTelefonicoField, 10);
        prefissoField = new TextField(2, "39", 50, 30);
        setInputTextField(prefissoField, 2);


        JPanel indirizzo = new JPanel(new FlowLayout());
        indirizzo.add(indirizzoFarmaciaField);
        indirizzo.add(civicoField);
        indirizzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel telefono = new JPanel(new FlowLayout());
        telefono.add(prefissoField);
        telefono.add(recapitoTelefonicoField);
        nomeFarmaciaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        telefono.setAlignmentX(Component.CENTER_ALIGNMENT);

        //pannello che contiene il testo
        JPanel panel_Text = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel_Text.setLayout(layout);
        //testo
        JTextField textArea = new JTextField("Inserisci i seguenti dati:");
        textArea.setEnabled(false);
        panel_Text.add(textArea);
        frame.getContentPane().add(panel_Text, BorderLayout.NORTH);

        JPanel boxCenterPanel = new JPanel();
        boxCenterPanel.setLayout(new BoxLayout(boxCenterPanel, BoxLayout.PAGE_AXIS));
        boxCenterPanel.add(nomeFarmaciaField);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,5)));
        boxCenterPanel.add(indirizzo);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,5)));
        boxCenterPanel.add(telefono);
        JPanel boxFlow = new JPanel(new FlowLayout());
        boxFlow.add(boxCenterPanel);
        frame.getContentPane().add(boxFlow, BorderLayout.CENTER);

        //pannello che contiene i bottoni
        JPanel panel = new JPanel();
        panel.setLayout(layout);


        //bottone ok
        buttonOk.setText("OK");
        createListenerButtonOkRegistrazione(email, password);
        panel.add(buttonOk);


        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // RIEPILOGO CARICO SCORTE
    public AlertMessage(int id_Ordine, int n, int[] intarrayQuantitaArrivate, int[] intArrayQuantitaOrdine, String[] stringNome, String[] principioAttivo, Date[] dataScadenza){

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel riepilogoOrdine = new JLabel("Riepilogo ordine: " + id_Ordine);
        riepilogoOrdine.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(riepilogoOrdine, BorderLayout.NORTH);

        JScrollPane sp;
        JPanel mainCenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String[] headers = {"Nome farmaco", "Principio attivo", "Data di scadenza", "Quantità prevista", "Quantità arrivata"};

        for(int i = 0; i < headers.length; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + headers[i]));
            mainCenterPanel.add(bordo, gbc);
        }

        // Object[] arrayArray = {stringNome, principioAttivo, dataScadenza, intArrayQuantitaOrdine, intarrayQuantitaArrivate};

        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + stringNome[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 1;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + principioAttivo[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 2;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + dataScadenza[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 3;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + intArrayQuantitaOrdine[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 4;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + intarrayQuantitaArrivate[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        sp = new JScrollPane(mainCenterPanel);
        mainPanel.add(sp, BorderLayout.CENTER);

        buttonOk.setText("Conferma");
        buttonOk.setFont(new Font("Arial", 1,15));
        createListenereButtonOkCaricoScorte(id_Ordine, n, intarrayQuantitaArrivate, intArrayQuantitaOrdine, stringNome, principioAttivo, dataScadenza);

        buttonAnnulla.setText("Annulla");
        buttonAnnulla.setFont(new Font("Arial", 1,15));
        buttonAnnulla.addActionListener(e -> {
            buttonOk.removeActionListener(AC);
            frame.dispose();
        });

        JPanel mainSouthPanel = new JPanel(new FlowLayout());
        mainSouthPanel.add(buttonOk);
        mainSouthPanel.add(buttonAnnulla);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);


        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    // RIEPILOGO PRENOTAZIONE TUTTO DISPONIBILE
    public AlertMessage(int n, int[] intArrayQuantitaOrdinate, int[] intArrayQuantitaDisponibili, String[] stringNome, String[] principioAttivo, Date[] dataScadenza, LocalDate dataConsegna){

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("Riepilogo prenotazione");
        mainNorthPanel.add(message, BorderLayout.CENTER);
        JLabel labelDataConsegna = new JLabel("Data consegna ordine: " + dataConsegna);
        mainNorthPanel.add(labelDataConsegna, BorderLayout.SOUTH);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        JPanel mainCenterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String[] headers = {"Nome farmaco", "Principio attivo", "Data di scadenza", "Quantità richiesta"};

        for(int i = 0; i < headers.length; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = i;
            gbc.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + headers[i]));
            mainCenterPanel.add(bordo, gbc);
        }

        // Object[] arrayArray = {stringNome, principioAttivo, dataScadenza, intArrayQuantitaOrdine, intarrayQuantitaArrivate};

        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + stringNome[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 1;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + principioAttivo[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 2;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + dataScadenza[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < n; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 3;
            gbc.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + intArrayQuantitaOrdinate[i]));
            mainCenterPanel.add(bordo, gbc);
        }

        JScrollPane sp = new JScrollPane(mainCenterPanel);
        mainPanel.add(sp, BorderLayout.CENTER);

        buttonOkPrenotazione.setText("Conferma");
        buttonOkPrenotazione.setFont(new Font("Arial", 1,15));
        createListenereButtonOkPrenotazione(n, stringNome, principioAttivo, dataScadenza, intArrayQuantitaDisponibili, intArrayQuantitaOrdinate, dataConsegna);

        buttonAnnulla.setText("Annulla");
        buttonAnnulla.setFont(new Font("Arial", 1,15));
        buttonAnnulla.addActionListener(e -> {
            frame.dispose();
            buttonOkPrenotazione.removeActionListener(ALP);
        });

        JPanel mainSouthPanel = new JPanel(new FlowLayout());
        mainSouthPanel.add(buttonOkPrenotazione);
        mainSouthPanel.add(buttonAnnulla);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);


        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // RIEPILOGO PRENOTAZIONE NON TUTTO DISPONIBILE
    public AlertMessage(int n, int m, int[] intArrayNuoveQuantitaOrdinate, int[] intArrayQuantitaDisponibili, String[] stringNome, String[] principioAttivo, Date[] dataScadenza, int[] intArrayQuantitaResidue, String[] nomeFarmaciResidui, String[] principioAttivoFarmaciResidui, LocalDate nuovaDataConsegna, LocalDate nuovaDataConsegnaReisua, LocalDate nuovaDataScadenzaResidua){

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        JTextArea message = new JTextArea("Le scorte richieste non sono tutte disponibili al momento, ma possiamo " + System.lineSeparator() + " inviarti quelle che mancano non appena saranno di nuovo disponibili, vuoi confermare?" + System.lineSeparator() + "Riepilogo ordine:");
        message.setEnabled(false);
        mainNorthPanel.add(message, BorderLayout.CENTER);
        JLabel dataConsegna = new JLabel("Data consegna primo ordine: " + nuovaDataConsegna + "| Data consegna secondo ordine: " + nuovaDataConsegnaReisua);
        mainNorthPanel.add(dataConsegna, BorderLayout.SOUTH);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        JPanel mainCenterPanelFlow1 = new JPanel(new FlowLayout());

        JPanel mainCenterPanelBox = new JPanel();
        mainCenterPanelBox.setLayout(new BoxLayout(mainCenterPanelBox, BoxLayout.PAGE_AXIS));

        JScrollPane sp1;
        JPanel mainCenterPanelBox1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        String[] headers1 = {"Nome farmaco", "Principio attivo", "Data di scadenza", "Quantità richiesta"};

        for(int i = 0; i < headers1.length; i++){
            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.ipadx = 20;
            gbc1.ipady = 20;
            gbc1.gridx = i;
            gbc1.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + headers1[i]));
            mainCenterPanelBox1.add(bordo, gbc1);
        }

        // Object[] arrayArray = {stringNome, principioAttivo, dataScadenza, intArrayQuantitaOrdine, intarrayQuantitaArrivate};

        for(int i = 0; i < n; i++){
            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.ipadx = 20;
            gbc1.ipady = 20;
            gbc1.gridx = 0;
            gbc1.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            if(stringNome[i] != null) {
                bordo.add(new JLabel("" + stringNome[i]));
            } else {
                bordo.add(new JLabel("None"));
            }
            mainCenterPanelBox1.add(bordo, gbc1);
        }
        for(int i = 0; i < n; i++){
            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.ipadx = 20;
            gbc1.ipady = 20;
            gbc1.gridx = 1;
            gbc1.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            if(principioAttivo[i] != null) {
                bordo.add(new JLabel("" + principioAttivo[i]));
            } else {
                bordo.add(new JLabel("None"));
            }
            mainCenterPanelBox1.add(bordo, gbc1);
        }
        for(int i = 0; i < n; i++){
            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.ipadx = 20;
            gbc1.ipady = 20;
            gbc1.gridx = 2;
            gbc1.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            if(dataScadenza[i] != null) {
                bordo.add(new JLabel("" + dataScadenza[i]));
            } else {
                bordo.add(new JLabel("None"));
            }
            mainCenterPanelBox1.add(bordo, gbc1);
        }
        for(int i = 0; i < n; i++){
            gbc1.fill = GridBagConstraints.HORIZONTAL;
            gbc1.ipadx = 20;
            gbc1.ipady = 20;
            gbc1.gridx = 3;
            gbc1.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            if(intArrayNuoveQuantitaOrdinate[i] != 0) {
                bordo.add(new JLabel("" + intArrayNuoveQuantitaOrdinate[i]));
            } else {
                bordo.add(new JLabel("None"));
            }
            mainCenterPanelBox1.add(bordo, gbc1);
        }




        sp1 = new JScrollPane(mainCenterPanelBox1);
        mainCenterPanelFlow1.add(sp1);

        JScrollPane sp2;
        JPanel mainCenterPanelBox2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        String[] headers2 = {"Nome farmaco", "Principio attivo", "Data scadenza","Quantità richiesta"};

        for(int i = 0; i < headers2.length; i++){
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.ipadx = 20;
            gbc2.ipady = 20;
            gbc2.gridx = i;
            gbc2.gridy = 0;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(borderHeader);
            bordo.add(new JLabel("" + headers2[i]));
            mainCenterPanelBox2.add(bordo, gbc2);
        }

        for(int i = 0; i < m; i++){
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.ipadx = 20;
            gbc2.ipady = 20;
            gbc2.gridx = 0;
            gbc2.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + nomeFarmaciResidui[i]));
            mainCenterPanelBox2.add(bordo, gbc2);
        }
        for(int i = 0; i < m; i++){
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.ipadx = 20;
            gbc2.ipady = 20;
            gbc2.gridx = 1;
            gbc2.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + principioAttivoFarmaciResidui[i]));
            mainCenterPanelBox2.add(bordo, gbc2);
        }


        for(int i = 0; i < m; i++){
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.ipadx = 20;
            gbc2.ipady = 20;
            gbc2.gridx = 2;
            gbc2.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + nuovaDataScadenzaResidua));
            mainCenterPanelBox2.add(bordo, gbc2);
        }


        for(int i = 0; i < m; i++){
            gbc2.fill = GridBagConstraints.HORIZONTAL;
            gbc2.ipadx = 20;
            gbc2.ipady = 20;
            gbc2.gridx = 3;
            gbc2.gridy = i + 1;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + intArrayQuantitaResidue[i]));
            mainCenterPanelBox2.add(bordo, gbc2);
        }

        sp2 = new JScrollPane(mainCenterPanelBox2);
        mainCenterPanelFlow1.add(sp2);

        JScrollPane sp3 = new JScrollPane(mainCenterPanelFlow1);

        mainPanel.add(sp3, BorderLayout.CENTER);


        buttonOkParziale.setText("Conferma");
        buttonOkParziale.setFont(new Font("Arial", 1,15));
        createListenereButtonOkPrenotazioneParziale(n, m, stringNome, principioAttivo, dataScadenza, intArrayQuantitaDisponibili, intArrayNuoveQuantitaOrdinate, nomeFarmaciResidui, principioAttivoFarmaciResidui, intArrayQuantitaResidue, nuovaDataConsegna, nuovaDataConsegnaReisua, nuovaDataScadenzaResidua);

        buttonAnnulla.setText("Annulla");
        buttonAnnulla.setFont(new Font("Arial", 1,15));
        buttonAnnulla.addActionListener(e -> {
            frame.dispose();
            buttonOkParziale.removeActionListener(ALPP);
        });

        JPanel mainSouthPanel = new JPanel(new FlowLayout());
        mainSouthPanel.add(buttonOkParziale);
        mainSouthPanel.add(buttonAnnulla);

        mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);


        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public void createListenerButtonOkRegistrazione(String email, String password ){
        ActionListener AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pattern phoneNumberPattern = Pattern.compile("^([0-9]{9,10})");
                Matcher phoneNumberMatcher;
                phoneNumberMatcher = phoneNumberPattern.matcher(recapitoTelefonicoField.getText());
                if(!nomeFarmaciaField.getText().equals("") && !indirizzoFarmaciaField.getText().equals("") && !civicoField.getText().equals("") && !prefissoField.getText().equals("") && !recapitoTelefonicoField.getText().equals("")){
                    if(phoneNumberMatcher.matches()) {
                        String nomeFarmacia = nomeFarmaciaField.getText();
                        String indirizzoFarmacia = indirizzoFarmaciaField.getText() + ", " + civicoField.getText();
                        String recapitoTelefonico = "+"+prefissoField.getText() + " " +recapitoTelefonicoField.getText();

                        ResultSet farmaciaPAutomatica = null;
                        try {
                            farmaciaPAutomatica = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.prenotazione_automatica WHERE nome_farmacia = '"+nomeFarmacia+"'");
                            farmaciaPAutomatica.first();
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            if(!farmaciaPAutomatica.next()){
                                int n;
                                ResultSet rs = null;
                                try {
                                    rs = Main.dbms_Azienda.getData("SELECT * FROM dbms_azienda.farmaco_non_da_banco");
                                    n = Main.dbms_Azienda.getResultSetRows(rs);
                                    rs.first();
                                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                    throw new RuntimeException(ex);
                                }
                                String nomeFarmacoNonDaBanco[] = new String[n];
                                String principioAttivoNonDaBanco[] = new String[n];

                                for(int i = 0; i < n; i++){
                                    try {
                                        nomeFarmacoNonDaBanco[i] = rs.getString(1);
                                        principioAttivoNonDaBanco[i] = rs.getString(2);
                                        rs.next();
                                    } catch (SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                for(int i = 0; i < n; i++){
                                    try {
                                        Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`prenotazione_automatica` (`nome_farmaco`, `principio_attivo`, `quantita`, `nome_farmacia`) VALUES ('"+ nomeFarmacoNonDaBanco[i] +"', '"+ principioAttivoNonDaBanco[i] +"', '0', '"+nomeFarmacia+"');");
                                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }

                            }
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }


                        try {
                            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`utente` (`Email`, `Password`, `Mansione`, `Stato`) VALUES ('"+ email +"', '"+ password +"', 'Farmacista', '0');");
                            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`farmacista` (`Email`, `Password`, `Mansione`, `Nome_farmacia`) VALUES ('"+ email +"', '"+ password +"', 'Farmacista','"+ nomeFarmacia +"');");
                            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`farmacie` (`Nome_farmacia`, `Recapito_telefonico`, `Indirizzo_farmacia`) VALUES ('"+ nomeFarmacia +"', '"+ recapitoTelefonico +"', '"+ indirizzoFarmacia +"');");
                            Main.dbms_Azienda.setData("INSERT INTO `dbms_farmacia`.`farmacista` (`Email`, `Password`, `Mansione`, `Nome_farmacia`) VALUES ('"+ email +"', '"+ password +"', 'Farmacista','"+ nomeFarmacia +"');");
                            Main.dbms_Azienda.setData("INSERT INTO `dbms_farmacia`.`farmacie` (`Nome_farmacia`, `Recapito_telefonico`, `Indirizzo_farmacia`) VALUES ('"+ nomeFarmacia +"', '"+ recapitoTelefonico +"', '"+ indirizzoFarmacia +"');");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                            ex.printStackTrace();
                        }

                        frame.dispose();
                        JOptionPane.showMessageDialog(new Frame(), "UTENTE REGISTRATO CON SUCCESSO");
                        buttonOk.removeActionListener(this);
                        try {
                            ReloadPage.reloadRegistrazione();
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                        Main.cardLayout.show(Main.mainPanel, "SchermataAutenticazione");
                    }
                    else{
                        JOptionPane.showMessageDialog(new Frame(), "IL NUMERO INSERITO NON È VALIDO, DEVE CONTENERE TRA LE 9 E LE 10 CIFRE");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(new Frame(), "HAI LASCIATO VUOTI ALCUNI DEI CAMPI");
                }
            }
        };
        buttonOk.addActionListener(AC);
    }

    public void createListenereButtonOkCaricoScorte(int id_ordine, int n, int[] intarrayQuantitaArrivate, int[] intArrayQuantitaOrdine, String[] stringNome, String[] principioAttivo, Date[] dataScadenza){
        AC = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //AGGIORNA LE SCORTE
                try {
                    ResultSet controllo;
                    for(int i = 0; i < n; i++){
                        controllo = Main.dbms_Farmacia.getData("SELECT nome_farmaco FROM dbms_farmacia.elenco_scorte WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '"+ principioAttivo[i] +"' AND scadenza_farmaco = '"+ dataScadenza[i]+"' AND nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"';");
                        if(controllo.next()){
                            Main.dbms_Farmacia.setData("UPDATE dbms_farmacia.elenco_scorte SET quantita_disponibile = quantita_disponibile + '"  + intarrayQuantitaArrivate[i] + "' WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '"+ principioAttivo[i] +"' AND scadenza_farmaco = '"+ dataScadenza[i]+"' AND nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"';");
                        }
                        else{
                            Main.dbms_Farmacia.setData("INSERT INTO `dbms_farmacia`.`elenco_scorte` (`nome_farmaco`, `principio_attivo`, `quantita_disponibile`, `scadenza_farmaco`, `nome_Farmacia`) VALUES ('" + stringNome[i] + "', '"+ principioAttivo[i] +"', '" + intarrayQuantitaArrivate[i] + "', '"+ dataScadenza[i]+"', '"+ SchermataLogin.nomeFarmacia +"');");
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                //CAMBIA STATO CONSEGNA IN CARICATA SIA IN ELENCO CONSEGNE CHE IN LISTA ORDINI
                try {
                    Main.dbms_Farmacia.setData("UPDATE dbms_azienda.lista_ordini SET stato_ordine = 2 WHERE id_ordine = '"+ id_ordine +"';");
                    Main.dbms_Farmacia.setData("UPDATE dbms_azienda.elenco_consegne SET stato_consegna = 2 WHERE id_ordine = '"+ id_ordine +"';");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    throw new RuntimeException(ex);
                }



                ResultSet controllo;
                String recapitoTelefonico;
                try {
                    controllo = Main.dbms_Farmacia.getData("SELECT recapito_telefonico FROM dbms_azienda.elenco_consegne WHERE id_ordine = '"+ id_ordine +"';");
                    controllo.next();
                    recapitoTelefonico = controllo.getString(1);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    throw new RuntimeException(ex);
                }

                //CONTROLLA SEGNALAZIONI
                boolean Segnalazione = false;
                for(int i = 0; i < n; i++){
                    if(intArrayQuantitaOrdine[i] != intarrayQuantitaArrivate[i]){
                        Segnalazione = true;
                    }
                }
                if (Segnalazione) {
                    try {
                        Main.dbms_Farmacia.setData("INSERT INTO `dbms_azienda`.`schermata_segnalazione` (`nome_farmacia`, `recapito_telefonico`, `Id_ordine`, `stato_segnalazione`) VALUES ('"+SchermataLogin.nomeFarmacia+"', '" + recapitoTelefonico + "', '" + id_ordine + "', '0');");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                             SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    Segnalazione = false;
                }
                frame.dispose();

                buttonOk.removeActionListener(this);
                //AGGIORNA LA SCHEMATA CONSEGNE E CI TORNA
                try {
                    ReloadPage.reloadSchermataConsegneFarmacistaPerCaricoScorte();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                Main.cardLayout.show(Main.mainPanel, "SchermataConsegne");
            }
        };
        buttonOk.addActionListener(AC);
    }

    public void createListenereButtonOkPrenotazione(int n, String[] stringNome, String[] principioAttivo, Date[] dateScadenza, int[] quantitaDisponibili, int[] quantitaOrdinate, LocalDate nuovaDataConsegna) {
        ALP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i < n; i++) {
                        //AGGIORNA QUANTITÀ
                        Main.dbms_Azienda.setData("UPDATE dbms_azienda.farmaco SET quantita_disponibile = REPLACE(quantita_disponibile, '" + quantitaDisponibili[i] + "', '" + (quantitaDisponibili[i] - quantitaOrdinate[i]) + "') WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '" + principioAttivo[i] + "' AND Data_scadenza = '" + dateScadenza[i] + "';");
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }
                int nuovoIdOrdine = 1;
                String indirizzoFarmacia = "";
                String recapitoTelefonicoFarmacia = "";

                try {
                    nuovoIdOrdine = generaIdOrdine();
                    indirizzoFarmacia = getIndirizzoFarmacia();
                    recapitoTelefonicoFarmacia = getRecapitoTelefonicoFarmacia();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                try {
                    //AGGIORNA LISTA ORDINI
                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`lista_ordini` (`id_ordine`, `data_consegna_ordine`, `nome_farmacia`, `stato_ordine`) VALUES ('" + nuovoIdOrdine + "', '" + nuovaDataConsegna + "', '" + SchermataLogin.nomeFarmacia + "', '0');");
                    //AGGIORNA ELENCO CONSEGNE
                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`elenco_consegne` (`id_ordine`, `indirizzo_postale`, `recapito_telefonico`, `data_consegna`, `nome_farmacia`, `stato_consegna`) VALUES ('" + nuovoIdOrdine + "', '" + indirizzoFarmacia + "', '" + recapitoTelefonicoFarmacia + "', '" + nuovaDataConsegna + "', '" + SchermataLogin.nomeFarmacia + "', '0');");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }
                //AGGIORNA DETTAGLIO ORDINE
                try {
                    for (int i = 0; i < n; i++) {
                        Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`dettaglio_ordine` (`id_ordine`, `nome_farmaco`, `principio_attivo`, `quantita`, `data_scadenza`) VALUES ('" + nuovoIdOrdine + "', '" + stringNome[i] + "', '" + principioAttivo[i] + "', '" + quantitaOrdinate[i] + "', '" + dateScadenza[i] + "');");
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                    // elenco consegne, lista ordini e dettaglio ordine
                }

                //SE CI SONO FARMACI CHE ARRIVANO A DISPONIBILITÀ 0 E HANNO ALTRI DOPPIONI LI CANCELLA
                ResultSet queryResult = null;
                int rows_farmaci_zero;
                try {
                    queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco FROM dbms_azienda.farmaco WHERE quantita_disponibile = '0';");
                    queryResult.first();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                rows_farmaci_zero = Main.dbms_Azienda.getResultSetRows(queryResult);
                String nomeFarmaco_daCancellare;
                ResultSet queryResult1 = null;
                for(int i = 0; i < rows_farmaci_zero; i++){
                    try {
                        nomeFarmaco_daCancellare = queryResult.getString(1);
                        queryResult.next();
                        queryResult1 = Main.dbms_Azienda.getData("SELECT nome_farmaco FROM dbms_azienda.farmaco WHERE nome_farmaco = '"+ nomeFarmaco_daCancellare +"';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    if(Main.dbms_Azienda.getResultSetRows(queryResult1) > 1)
                    {
                        try {
                            Main.dbms_Azienda.setData("DELETE FROM `dbms_azienda`.`farmaco` WHERE (`nome_farmaco` = '" + nomeFarmaco_daCancellare + "') and (`quantita_disponibile` = '0');");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                                 SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }


                frame.dispose();
                buttonOkPrenotazione.removeActionListener(this);
                try {
                    ReloadPage.reloadSchermataPrenotazione();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.cardLayout.show(Main.mainPanel, "SchermataPrenotazione");
            }
        };

        buttonOkPrenotazione.addActionListener(ALP);
    }

    public void createListenereButtonOkPrenotazioneParziale(int n, int m, String[] stringNome, String[] principioAttivo, Date[] dateScadenza, int[] quantitaDisponibili, int[] nuoveQuantitaOrdinate, String[] nomeFarmaciResidui, String[] principioAttivoFarmaciResidui, int[] quantitaResidue, LocalDate nuovaDataConsegna, LocalDate nuovaDataConsegnaResidua, LocalDate nuovaDataScadenzaResidua){
        ALPP = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                for(int i = 0; i < n; i++) {
                    if (stringNome[i] == null || principioAttivo[i] == null || dateScadenza[i] == null || nuoveQuantitaOrdinate[i] == 0) {
                        flag = true;
                    }
                }
                if (!flag) {
                    try {
                        for (int i = 0; i < n; i++) {
                            //AGGIORNA QUANTITÀ
                            Main.dbms_Azienda.setData("UPDATE dbms_azienda.farmaco SET quantita_disponibile = REPLACE(quantita_disponibile, '" + quantitaDisponibili[i] + "', '" + (quantitaDisponibili[i] - nuoveQuantitaOrdinate[i]) + "') WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '" + principioAttivo[i] + "' AND Data_scadenza = '" + dateScadenza[i] + "';");
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    int nuovoIdOrdine = 1;
                    String indirizzoFarmacia = "";
                    String recapitoTelefonicoFarmacia = "";

                    try {
                        nuovoIdOrdine = generaIdOrdine();
                        indirizzoFarmacia = getIndirizzoFarmacia();
                        recapitoTelefonicoFarmacia = getRecapitoTelefonicoFarmacia();
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        //AGGIORNA LISTA ORDINI
                        Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`lista_ordini` (`id_ordine`, `data_consegna_ordine`, `nome_farmacia`, `stato_ordine`) VALUES ('" + nuovoIdOrdine + "', '" + nuovaDataConsegna + "', '" + SchermataLogin.nomeFarmacia + "', '0');");
                        //AGGIORNA ELENCO CONSEGNE
                        Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`elenco_consegne` (`id_ordine`, `indirizzo_postale`, `recapito_telefonico`, `data_consegna`, `nome_farmacia`, `stato_consegna`) VALUES ('" + nuovoIdOrdine + "', '" + indirizzoFarmacia + "', '" + recapitoTelefonicoFarmacia + "', '" + nuovaDataConsegna + "', '" + SchermataLogin.nomeFarmacia + "', '0');");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                    //AGGIORNA DETTAGLIO ORDINE
                    try {
                        for (int i = 0; i < n; i++) {
                            Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`dettaglio_ordine` (`id_ordine`, `nome_farmaco`, `principio_attivo`, `quantita`, `data_scadenza`) VALUES ('" + nuovoIdOrdine + "', '" + stringNome[i] + "', '" + principioAttivo[i] + "', '" + nuoveQuantitaOrdinate[i] + "', '" + dateScadenza[i] + "');");
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                // QUANTITA RESIDUE

                int nuovoIdOrdineResiduo = 0;
                String indirizzoFarmacia = "";
                String recapitoTelefonicoFarmacia = "";
                try{

                    if(!flag) {
                        nuovoIdOrdineResiduo = generaIdOrdine();
                    } else {
                        nuovoIdOrdineResiduo = generaIdOrdine();
                    }


                    indirizzoFarmacia = getIndirizzoFarmacia();
                    recapitoTelefonicoFarmacia = getRecapitoTelefonicoFarmacia();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
                    ex.printStackTrace();
                }


                try {
                    //AGGIORNA LISTA ORDINI
                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`lista_ordini` (`id_ordine`, `data_consegna_ordine`, `nome_farmacia`, `stato_ordine`) VALUES ('" + nuovoIdOrdineResiduo + "', '" + nuovaDataConsegnaResidua + "', '" + SchermataLogin.nomeFarmacia + "', '0');");
                    //AGGIORNA ELENCO CONSEGNE
                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`elenco_consegne` (`id_ordine`, `indirizzo_postale`, `recapito_telefonico`, `data_consegna`, `nome_farmacia`, `stato_consegna`) VALUES ('" + nuovoIdOrdineResiduo + "', '" + indirizzoFarmacia + "', '" + recapitoTelefonicoFarmacia + "', '" + nuovaDataConsegnaResidua + "', '" + SchermataLogin.nomeFarmacia + "', '0');");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }
                //AGGIORNA DETTAGLIO ORDINE
                try {
                    for (int i = 0; i < m; i++) {
                        Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`dettaglio_ordine` (`id_ordine`, `nome_farmaco`, `principio_attivo`, `quantita`, `data_scadenza`) VALUES ('" + nuovoIdOrdineResiduo + "', '" + nomeFarmaciResidui[i] + "', '" + principioAttivoFarmaciResidui[i] + "', '" + quantitaResidue[i] + "', '" + nuovaDataScadenzaResidua + "');");
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                //SE CI SONO FARMACI CHE ARRIVANO A DISPONIBILITÀ 0 E HANNO ALTRI DOPPIONI LI CANCELLA
                ResultSet queryResult = null;
                int rows_farmaci_zero;
                try {
                    queryResult = Main.dbms_Azienda.getData("SELECT nome_farmaco FROM dbms_azienda.farmaco WHERE quantita_disponibile = '0';");
                    queryResult.first();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                rows_farmaci_zero = Main.dbms_Azienda.getResultSetRows(queryResult);
                String nomeFarmaco_daCancellare;
                ResultSet queryResult1 = null;
                for(int i = 0; i < rows_farmaci_zero; i++){
                    try {
                        nomeFarmaco_daCancellare = queryResult.getString(1);
                        queryResult.next();
                        queryResult1 = Main.dbms_Azienda.getData("SELECT nome_farmaco FROM dbms_azienda.farmaco WHERE nome_farmaco = '"+ nomeFarmaco_daCancellare +"';");
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    if(Main.dbms_Azienda.getResultSetRows(queryResult1) > 1)
                    {
                        try {
                            Main.dbms_Azienda.setData("DELETE FROM `dbms_azienda`.`farmaco` WHERE (`nome_farmaco` = '" + nomeFarmaco_daCancellare + "') and (`quantita_disponibile` = '0');");
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                                 SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }


                buttonOkParziale.removeActionListener(this);
                frame.dispose();

                try {
                    ReloadPage.reloadSchermataPrenotazione();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                Main.cardLayout.show(Main.mainPanel, "SchermataPrenotazione");
                Main.cardLayout.show(Main.mainPanel, "SchermataFarmacista");
            }
        };
        buttonOkParziale.addActionListener(ALPP);
    }

    public int generaIdOrdine() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet queryResult = Main.dbms_Azienda.getData("SELECT MAX(dbms_azienda.lista_ordini.id_ordine) FROM dbms_azienda.lista_ordini;");
        int max = 0;
        if(queryResult.next()) {
            max = queryResult.getInt(1);
        }
        int id_ordine = max + 1;
        return id_ordine;
    }

    public String getIndirizzoFarmacia() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet queryResult;
        String indirizzoFarmacia = "";
        queryResult = Main.dbms_Azienda.getData("SELECT dbms_azienda.farmacie.indirizzo_farmacia FROM dbms_azienda.farmacie WHERE dbms_azienda.farmacie.nome_farmacia = '" + SchermataLogin.nomeFarmacia+"';");
        if(queryResult.next()){
            indirizzoFarmacia = queryResult.getString(1);
        }
        return indirizzoFarmacia;
    }

    public String getRecapitoTelefonicoFarmacia() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ResultSet queryResult;
        String recapitoTelefonico = "";
        queryResult = Main.dbms_Azienda.getData("SELECT dbms_azienda.farmacie.recapito_telefonico FROM dbms_azienda.farmacie WHERE dbms_azienda.farmacie.nome_farmacia = '" + SchermataLogin.nomeFarmacia + "';");
        if(queryResult.next()){
            recapitoTelefonico = queryResult.getString(1);
        }
        return recapitoTelefonico;
    }


    public static void setInputTextField(TextField textField, int maxInput){
        ((AbstractDocument)textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,
                                String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxInput
                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(FilterBypass fb, int offs, String str,
                                     AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxInput
                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }
}