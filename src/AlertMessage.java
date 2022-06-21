import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class AlertMessage {

    //POTREBBE TORNARE UTILE QUESTA RIGA:
    /*Frame frame = new Frame();
            Object[] options = {"Conferma",
                    "No, grazie"};
            int n = JOptionPane.showOptionDialog(frame,
                    "Sei sicuro di voler effettuare il Logout?",
                    "LogOut",  //titolo
                    JOptionPane.YES_NO_OPTION, //da cambiare se si vogliono più opzioni o meno
                    JOptionPane.QUESTION_MESSAGE, //per cambiare l'iconcina
                    null, //lasciare sempre cosi
                    options,  //the titles of buttons
                    options[0]); //puntatore alla prima opzione
            if(n == 0){
                //se è 0 significa che è stato premuto il primo bottone
            }
            else {
                //se è 1 significa che è stato premuto il secondo bottone e cosi via
                frame.dispose(); per chiudere
            }*/

    public static  JButton buttonOk = new JButton();
    private JFrame frame = new JFrame("Messaggio");

    public AlertMessage(){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        SchermataRegistrazione.indirizzoFarmaciaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        SchermataRegistrazione.nomeFarmaciaField.setAlignmentX(Component.CENTER_ALIGNMENT);
        SchermataRegistrazione.recapitoTelefonicoField.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        boxCenterPanel.add(SchermataRegistrazione.nomeFarmaciaField);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,5)));
        boxCenterPanel.add(SchermataRegistrazione.indirizzoFarmaciaField);
        boxCenterPanel.add(Box.createRigidArea(new Dimension(0,5)));
        boxCenterPanel.add(SchermataRegistrazione.recapitoTelefonicoField);
        JPanel boxFlow = new JPanel(new FlowLayout());
        boxFlow.add(boxCenterPanel);
        frame.getContentPane().add(boxFlow, BorderLayout.CENTER);

        //pannello che contiene i bottoni
        JPanel panel = new JPanel();
        panel.setLayout(layout);


        //bottone ok
        buttonOk.setText("OK");
        createListenerButtonOkRegistrazione();
        panel.add(buttonOk);


        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public void createListenerButtonOkRegistrazione( ){
        buttonOk.addActionListener(e -> {
            Pattern phoneNumberPattern = Pattern.compile("^[0-9]$");
            Matcher phoneNumberMatcher;
            phoneNumberMatcher = phoneNumberPattern.matcher(SchermataRegistrazione.recapitoTelefonicoField.getText());
            if(phoneNumberMatcher.matches()) {
                SchermataRegistrazione.nomeFarmacia = SchermataRegistrazione.nomeFarmaciaField.getText();
                SchermataRegistrazione.indirizzoFarmacia = SchermataRegistrazione.indirizzoFarmaciaField.getText();
                SchermataRegistrazione.recapitoTelefonico = SchermataRegistrazione.recapitoTelefonicoField.getText();
                frame.dispose();
                JOptionPane.showMessageDialog(Main.mainFrame, "UTENTE REGISTRATO CON SUCCESSO");
            }
            else{
                JOptionPane.showMessageDialog(Main.mainFrame, "IL NUMERO INSERITO NON è VALIDO");
            }
        });
    }
}