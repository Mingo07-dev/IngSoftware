import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    public static JPanel schermataAutenticazionePanel;
    public static JPanel schermataFarmacistaPanel;
    public static JPanel schermataCorrierePanel;
    public static JPanel schermataImpiegatoAziendaPanel;
    public static JPanel mainPanel;
    public static CardLayout cardLayout = new CardLayout();


    public Main(){
        mainPanel = new JPanel(cardLayout);

        schermataAutenticazionePanel = new JPanel(new FlowLayout());
        schermataFarmacistaPanel= new JPanel(new BorderLayout());
        schermataCorrierePanel = new JPanel(new FlowLayout());
        schermataImpiegatoAziendaPanel = new JPanel(new FlowLayout());


        mainPanel.add(schermataAutenticazionePanel,"SchermataAutenticazione");
        mainPanel.add(schermataFarmacistaPanel,"SchermataFarmacista");
        mainPanel.add(schermataCorrierePanel, "SchermataCorriere");
        mainPanel.add(schermataImpiegatoAziendaPanel, "SchermataImpiegatoAzienda");


        SchermataAutenticazione schermataAutenticazione = new SchermataAutenticazione();
        SchermataFarmacista schermataFarmacista = new SchermataFarmacista();
        SchermataCorriere schermataCorriere = new SchermataCorriere();
        SchermataImpiegatoAzienda schermataImpiegatoAzienda = new SchermataImpiegatoAzienda();

        JFrame mainFrame = new JFrame("FarmaciePiùBelle");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setSize(1080,720);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);
    }
    public static void main(String[] args){
        Main Main = new Main();
    }

}