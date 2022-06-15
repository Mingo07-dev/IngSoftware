import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    private JFrame mainFrame;
    public static JPanel schermataPrincipalePanel;
    public static JPanel schermataFarmacistaPanel;
    public static JPanel schermataPrenotazionePanel;
    public static JPanel mainPanel;
    public static CardLayout cardLayout = new CardLayout();
    public Main(){
        mainPanel = new JPanel(cardLayout);

        schermataPrincipalePanel = new JPanel(new BorderLayout());
        schermataFarmacistaPanel= new JPanel(new BorderLayout());
        schermataPrenotazionePanel = new JPanel(new BorderLayout());

        mainPanel.add(schermataPrincipalePanel,"1");
        mainPanel.add(schermataFarmacistaPanel,"2");
        mainPanel.add(schermataPrenotazionePanel,"3");

        SchermataPrincipale schermataPrincipale = new SchermataPrincipale();
        SchermataPrincipale.inizialize();

        mainFrame = new JFrame("Login");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setSize(1000,720);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args){
        Main Main = new Main();
    }

}