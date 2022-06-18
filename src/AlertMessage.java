import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AlertMessage {

    //POTREBBE TORNARE UTILE QUESTA RIGA:
    //JOptionPane.showMessageDialog(Main.mainFrame, "Welcome to Swing!");

    //EXAMPLE: AlertMessage alert = new AlertMessage("OK", "Intelligente chi ha scritto");
    //         alert.createListenerButtonOk();

    private JButton button_Conferma = new JButton();
    private JButton button_Annulla = new JButton();
    private JButton buttonOk = new JButton();
    private JFrame frame = new JFrame("Messaggio");
    
    public AlertMessage(String buttonName_Left, String buttonName_Right, String text){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //pannello che contiene il testo
        JPanel panel_Text = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel_Text.setLayout(layout);
        //testo
        JTextField textArea = new JTextField(text);
        textArea.setEnabled(false);
        panel_Text.add(textArea);
        frame.getContentPane().add(panel_Text, BorderLayout.NORTH);

        //pannello che contiene i bottoni
        JPanel panel = new JPanel();
        panel.setLayout(layout);



        //bottone di sinistra
        button_Conferma.setText(buttonName_Left);
        panel.add(button_Conferma);


        //bottone di destra
        button_Annulla.setText(buttonName_Right);
        panel.add(button_Annulla);


        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public AlertMessage(String buttonName, String text){
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //pannello che contiene il testo
        JPanel panel_Text = new JPanel();
        LayoutManager layout = new FlowLayout();
        panel_Text.setLayout(layout);
        //testo
        JTextField textArea = new JTextField(text);
        textArea.setEnabled(false);
        panel_Text.add(textArea);
        frame.getContentPane().add(panel_Text, BorderLayout.NORTH);

        //pannello che contiene i bottoni
        JPanel panel = new JPanel();
        panel.setLayout(layout);


        //bottone ok
        buttonOk.setText(buttonName);
        panel.add(buttonOk);


        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public void createListenerButtonConferma( ){
        button_Conferma.addActionListener(e -> {
            frame.dispose();
        });
    }

    public void createListenerButtonAnnulla( ){
        button_Annulla.addActionListener(e -> {
            frame.dispose();
        });
    }
    public void createListenerButtonOk( ){
        buttonOk.addActionListener(e -> {
            frame.dispose();
        });
    }
}