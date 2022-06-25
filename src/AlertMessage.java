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

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import javax.swing.border.Border;

public class AlertMessage {

    /*Frame frame = new Frame();
            Object[] options = {"Conferma",
                    "No, grazie"};
            int n = JOptionPane.showOptionDialog(frame,
                    "Sei sicuro di voler effettuare il Logout?",
                    "LogOut",  //titolo
                    JOptionPane.YES_NO_OPTION, //da cambiare se si vogliono più opzioni o meno
                    JOptionPane.QUESTION_MESSAGE, //per cambiare l'iconcina
                    null, //lasciare sempre cosi
                    options,
                    options[0]); //puntatore alla prima opzione
            if(n == 0){
                //se è 0 significa che è stato premuto il primo bottone
            }
            else {
                //se è 1 significa che è stato premuto il secondo bottone e cosi via
                frame.dispose(); per chiudere
            }*/
    public static TextField nomeFarmaciaField;
    public static TextField indirizzoFarmaciaField;
    public static TextField recapitoTelefonicoField;

    public static  JButton buttonOk = new JButton();
    public static JButton buttonAnnulla = new JButton();
    private JFrame frame = new JFrame("Messaggio");
    private Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
    private Border borderHeader = BorderFactory.createLineBorder(Color.BLACK, 8);

    public AlertMessage(String email, String password){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        nomeFarmaciaField = new TextField(15, "Nome Farmacia", 50, 30);
        indirizzoFarmaciaField = new TextField(20, "Indirizzo, 0", 50, 30);
        recapitoTelefonicoField = new TextField(10, "Telefono", 50, 30);

        indirizzoFarmaciaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nomeFarmaciaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        recapitoTelefonicoField.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        boxCenterPanel.add(indirizzoFarmaciaField);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,5)));
        boxCenterPanel.add(recapitoTelefonicoField);
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
    public AlertMessage(int n, int[] intArrayQuantitaOrdinate, int[] intArrayQuantitaDisponibili, String[] stringNome, String[] principioAttivo, Date[] dataScadenza){

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("Riepilogo prenotazione");
        mainNorthPanel.add(message, BorderLayout.CENTER);
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

        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

        buttonOk.setText("Conferma");
        buttonOk.setFont(new Font("Arial", 1,15));
        createListenereButtonOkPrenotazione(n, stringNome, principioAttivo, dataScadenza, intArrayQuantitaDisponibili, intArrayQuantitaOrdinate);

        buttonAnnulla.setText("Annulla");
        buttonAnnulla.setFont(new Font("Arial", 1,15));
        buttonAnnulla.addActionListener(e -> {
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

    // RIEPILOGO PRENOTAZIONE NON TUTTO DISPONIBILE
    public AlertMessage(int n, int m, int[] intArrayNuoveQuantitaOrdinate, int[] intArrayQuantitaDisponibili, String[] stringNome, String[] principioAttivo, Date[] dataScadenza, int[] intArrayQuantitaResidue, String[] nomeFarmaciResidui, String[] principioAttivoFarmaciResidui){

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel mainNorthPanel = new JPanel(new BorderLayout());
        JLabel message = new JLabel("Le scorte richieste non sono interamente disponibili, vuoi effettuare due ordini nel seguente modo? Riepilogo prenotazione");
        mainNorthPanel.add(message, BorderLayout.CENTER);
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
            bordo.add(new JLabel("" + stringNome[i]));
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
            bordo.add(new JLabel("" + principioAttivo[i]));
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
            bordo.add(new JLabel("" + dataScadenza[i]));
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
            //System.out.println(intArrayNuoveQuantitaOrdinate[i]);
            bordo.add(new JLabel("" + intArrayNuoveQuantitaOrdinate[i]));
            mainCenterPanelBox1.add(bordo, gbc1);
        }

        sp1 = new JScrollPane(mainCenterPanelBox1);
        mainCenterPanelFlow1.add(sp1);

        JScrollPane sp2;
        JPanel mainCenterPanelBox2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc2 = new GridBagConstraints();
        String[] headers2 = {"Nome farmaco", "Principio attivo", "Quantità richiesta"};

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
            bordo.add(new JLabel("" + intArrayQuantitaResidue[i]));
            mainCenterPanelBox2.add(bordo, gbc2);
        }

        sp2 = new JScrollPane(mainCenterPanelBox2);
        mainCenterPanelFlow1.add(sp2);

        JScrollPane sp3 = new JScrollPane(mainCenterPanelFlow1);

        mainPanel.add(sp3, BorderLayout.CENTER);

        buttonOk.setText("Conferma");
        buttonOk.setFont(new Font("Arial", 1,15));
        createListenereButtonOkPrenotazioneParziale(n, m, stringNome, principioAttivo, dataScadenza, intArrayQuantitaDisponibili, intArrayNuoveQuantitaOrdinate, nomeFarmaciResidui, principioAttivoFarmaciResidui, intArrayQuantitaResidue);

        buttonAnnulla.setText("Annulla");
        buttonAnnulla.setFont(new Font("Arial", 1,15));
        buttonAnnulla.addActionListener(e -> {
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




    public void createListenerButtonOkRegistrazione(String email, String password ){
        buttonOk.addActionListener(e -> {
            Pattern phoneNumberPattern = Pattern.compile("^[0-9](.+)$");
            Matcher phoneNumberMatcher;
            phoneNumberMatcher = phoneNumberPattern.matcher(recapitoTelefonicoField.getText());
            if(phoneNumberMatcher.matches()) {
                String nomeFarmacia = nomeFarmaciaField.getText();
                String indirizzoFarmacia = indirizzoFarmaciaField.getText();
                int recapitoTelefonico =Integer.parseInt(recapitoTelefonicoField.getText());
                try {
                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`utente` (`Email`, `Password`, `Mansione`, `Stato`) VALUES ('"+ email +"', '"+ password +"', 'Farmacista', '0');");
                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`farmacista` (`Email`, `Password`, `Mansione`, `Nome_farmacia`) VALUES ('"+ email +"', '"+ password +"', 'Farmacista','"+ nomeFarmacia +"');");
                    Main.dbms_Azienda.setData("INSERT INTO `dbms_azienda`.`farmacie` (`Nome_farmacia`, `Recapito_telefonico`, `Indirizzo_farmacia`) VALUES ('"+ nomeFarmacia +"', '"+ recapitoTelefonico +"', '"+ indirizzoFarmacia +"');");
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    ex.printStackTrace();
                }

                frame.dispose();
                JOptionPane.showMessageDialog(Main.mainFrame, "UTENTE REGISTRATO CON SUCCESSO");
            }
            else{
                JOptionPane.showMessageDialog(Main.mainFrame, "IL NUMERO INSERITO NON è VALIDO");
            }
        });
    }

    public void createListenereButtonOkCaricoScorte(int id_ordine, int n, int[] intarrayQuantitaArrivate, int[] intArrayQuantitaOrdine, String[] stringNome, String[] principioAttivo, Date[] dataScadenza){
        buttonOk.addActionListener(e -> {
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
            int recapitoTelefonico;
            try {
                controllo = Main.dbms_Farmacia.getData("SELECT recapito_telefonico FROM dbms_azienda.elenco_consegne WHERE id_ordine = '"+ id_ordine +"';");
                controllo.next();
                recapitoTelefonico = controllo.getInt(1);
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


            //AGGIORNA LA SCHEMATA CONSEGNE E CI TORNA
            Main.schermataConsegnePanel.removeAll();
            try {
                SchermataConsegne.aggiornaTabellaFarmacista();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataConsegnePanel.repaint();
            Main.mainFrame.setVisible(true);
            Main.cardLayout.show(Main.mainPanel, "SchermataConsegne");
        });
    }

    public void createListenereButtonOkPrenotazione(int n, String[] stringNome, String[] principioAttivo, Date[] dateScadenza, int[] quantitaDisponibili, int[] quantitaOrdinate) {
        buttonOk.addActionListener(e -> {
            try {
                for (int i = 0; i < n; i++) {
                    //AGGIORNA QUANTITà
                    Main.dbms_Azienda.setData("UPDATE dbms_azienda.farmaco SET quantita_disponibile = REPLACE(quantita_disponibile, '" + quantitaDisponibili[i] + "', '" + (quantitaDisponibili[i] - quantitaOrdinate[i]) + "') WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '" + principioAttivo[i] + "' AND Data_scadenza = '" + dateScadenza[i] + "';");
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }
            int nuovoIdOrdine = 1;
            LocalDate nuovaDataConsegna = LocalDate.now().plusDays(3);
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
            Main.schermataPrenotazionePanel.removeAll();
            try {
                SchermataPrenotazione schermataPrenotazione = new SchermataPrenotazione();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataPrenotazionePanel.repaint();
            Main.mainFrame.setVisible(true);

            Main.cardLayout.show(Main.mainPanel, "SchermataFarmacista");
        });
    }

    public void createListenereButtonOkPrenotazioneParziale(int n, int m, String[] stringNome, String[] principioAttivo, Date[] dateScadenza, int[] quantitaDisponibili, int[] nuoveQuantitaOrdinate, String[] nomeFarmaciResidui, String[] principioAttivoFarmaciResidui, int[] quantitaResidue){
        buttonOk.addActionListener(e -> {
            try {
                for (int i = 0; i < n; i++) {
                    //AGGIORNA QUANTITà
                    Main.dbms_Azienda.setData("UPDATE dbms_azienda.farmaco SET quantita_disponibile = REPLACE(quantita_disponibile, '" + quantitaDisponibili[i] + "', '" + (quantitaDisponibili[i] - nuoveQuantitaOrdinate[i]) + "') WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '" + principioAttivo[i] + "' AND Data_scadenza = '" + dateScadenza[i] + "';");
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            }
            int nuovoIdOrdine = 1;
            LocalDate nuovaDataConsegna = LocalDate.now().plusDays(3);
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

            // QUANTITA RESIDUE

            int nuovoIdOrdineResiduo = nuovoIdOrdine + 1;
            LocalDate nuovaDataConsegnaResidua = LocalDate.now().plusDays(3).plusMonths(2);
            LocalDate nuovaDataScadenzaResidua = LocalDate.now().plusDays(3).plusMonths(3);

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

            Main.schermataPrenotazionePanel.removeAll();
            try {
                SchermataPrenotazione schermataPrenotazione = new SchermataPrenotazione();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            Main.schermataPrenotazionePanel.repaint();
            Main.mainFrame.setVisible(true);

            Main.cardLayout.show(Main.mainPanel, "SchermataFarmacista");
        });
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
}