import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    public static JPanel schermataAutenticazionePanel;
    public static JPanel schermataRegistrazionePanel;
    public static JPanel schermataLoginPanel;
    public static JPanel schermataRecuperoCredenzialiPanel;

    public static JPanel schermataFarmacistaPanel;
    public static JPanel schermataCorrierePanel;
    public static JPanel schermataImpiegatoAziendaPanel;



    public static JPanel mainPanel;

    public static CardLayout cardLayout = new CardLayout();


    public static void main(String[] args){
        mainPanel = new JPanel(cardLayout);

        schermataAutenticazionePanel = new JPanel(new GridBagLayout());
        schermataRegistrazionePanel = new JPanel(new GridBagLayout());
        schermataLoginPanel = new JPanel(new GridBagLayout());
        schermataRecuperoCredenzialiPanel = new JPanel(new GridBagLayout());

        schermataFarmacistaPanel= new JPanel(new GridBagLayout());
        schermataCorrierePanel = new JPanel(new GridBagLayout());
        schermataImpiegatoAziendaPanel = new JPanel(new GridBagLayout());



        mainPanel.add(schermataAutenticazionePanel,"SchermataAutenticazione");
        mainPanel.add(schermataRegistrazionePanel, "SchermataRegistrazione");
        mainPanel.add(schermataLoginPanel, "SchermataLogin");
        mainPanel.add(schermataRecuperoCredenzialiPanel,"SchermataRecuperoCredenziali");

        mainPanel.add(schermataFarmacistaPanel,"SchermataFarmacista");
        mainPanel.add(schermataCorrierePanel, "SchermataCorriere");
        mainPanel.add(schermataImpiegatoAziendaPanel, "SchermataImpiegatoAzienda");



        SchermataAutenticazione schermataAutenticazione = new SchermataAutenticazione();
        SchermataRegistrazione schermataRegistrazione= new SchermataRegistrazione();
        SchermataLogin schermataLogin = new SchermataLogin();
        SchermataRecuperoCredenziali schermataRecuperoCredenziali = new SchermataRecuperoCredenziali();

        SchermataFarmacista schermataFarmacista = new SchermataFarmacista();
        SchermataCorriere schermataCorriere = new SchermataCorriere();
        SchermataImpiegatoAzienda schermataImpiegatoAzienda = new SchermataImpiegatoAzienda();


        JFrame mainFrame = new JFrame("FarmaciePiùBelleConNoi");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(mainPanel, BorderLayout.CENTER);
        mainFrame.setSize(1080,720);
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setMinimumSize(new Dimension(1080, 720));
        mainFrame.setVisible(true);
    }

}