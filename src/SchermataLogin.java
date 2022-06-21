import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SchermataLogin {

    public static TextField emailField;
    public static TextField passwordField;

    public SchermataLogin() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        //CREA L'IMMAGINE
        Image picLabel = new Image("logo.png",100,100);
        //FINE

        mainNorthPanel.add(picLabel, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        //CREA IL BOTTONE TORNA INDIETRO
        Button buttonTornaIndietro = new Button("SchermataLogin", "Torna Indietro", 150,50);
        buttonTornaIndietro.changeFontButton("Arial", 1,15);
        buttonTornaIndietro.createListenerButtonGoBack();
        //FINE

        mainUserOptionsPanel.add(buttonTornaIndietro);

        //CREA IL BOTTONE TORNA ALLA HOME
        Button buttonHome = new Button("SchermataLogin","Home",150,50);
        buttonHome.changeFontButton("Arial", 1,15);
        buttonHome.createListenerButtonHome();
        //FINE

        mainUserOptionsPanel.add(buttonHome);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        JPanel centerBox = new JPanel(new FlowLayout());
        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.Y_AXIS));

        Image utente = new Image("utente.png", 200,200);

        utente.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(utente);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));

        emailField = new TextField(30, "Email", 150,30);
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(emailField);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));

        passwordField = new TextField(20, "Password", 150,30);
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(passwordField);
        mainCenterPanel.add(Box.createRigidArea(new Dimension(0,60)));


        //CREA IL BOTTONE LOGIN
        Button buttonLogin = new Button("SchermataLogin","Login",150,50);
        buttonLogin.changeFontButton("Arial", 1, 25);
        buttonLogin.createListenerButtonLogin();
        //FINE

        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainCenterPanel.add(buttonLogin);

        centerBox.add(mainCenterPanel);
        mainPanel.add(centerBox, BorderLayout.CENTER);

        Main.schermataLoginPanel.add(mainPanel, BorderLayout.CENTER);
    }
    
}
