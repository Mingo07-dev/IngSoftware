import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;

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
    private JFrame frame = new JFrame("Messaggio");

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
}