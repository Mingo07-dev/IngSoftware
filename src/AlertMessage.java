import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public AlertMessage(int id_Ordine, int n, int[] intarrayQuantitaArrivate, int[] intArrayQuantitaOrdine, String[] stringNome, String[] principioAttivo, Date[] dataScadenza){

        /*
        intarrayQuantitaArrivate = Table.getIntArray();
        intArrayQuantitaOrdine = Table.getIntArrayOldData();
        stringNome = Table.getStringNome();
        principioAttivo = Table.getPrincipioAttivo();
        dataScadenza = Table.getStringData();
        */

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel riepilogoOrdine = new JLabel("Riepilogo ordine: " + id_Ordine);
        riepilogoOrdine.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(riepilogoOrdine, BorderLayout.NORTH);

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
            gbc.gridy = i;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + stringNome[i]));
            mainCenterPanel.add(bordo, gbc);
        }
        for(int i = 0; i < principioAttivo.length; i++){
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.ipadx = 20;
            gbc.ipady = 20;
            gbc.gridx = 1;
            gbc.gridy = i;
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
            gbc.gridy = i;
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
            gbc.gridy = i;
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
            gbc.gridy = i;
            JPanel bordo = new JPanel(new FlowLayout());
            bordo.setBorder(border);
            bordo.add(new JLabel("" + intarrayQuantitaArrivate[i]));
            mainCenterPanel.add(bordo, gbc);
        }

        mainPanel.add(mainCenterPanel, BorderLayout.CENTER);

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
                        Main.dbms_Farmacia.setData("UPDATE dbms_farmacia.elenco_scorte SET quantita_disponibile = quantita_disponibile + '" + intarrayQuantitaArrivate[i] + "' WHERE nome_farmaco = '" + stringNome[i] + "' AND principio_attivo = '"+ principioAttivo[i] +"' AND scadenza_farmaco = '"+ dataScadenza[i]+"' AND nome_farmacia = '"+ SchermataLogin.nomeFarmacia +"';");
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
}