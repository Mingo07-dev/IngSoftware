import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class SchermataLogin {

    public SchermataLogin() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        //CREA L'IMMAGINE
        Image picLabel = new Image("logo.jpg",200,100);
        //FINE

        mainNorthPanel.add(picLabel, BorderLayout.WEST);

        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());

        //CREA IL BOTTONE TORNA INDIETRO
        Button buttonTornaIndietro = new Button("SchermataLogin", "Torna Indietro", 150,30);
        buttonTornaIndietro.changeFontButton("Arial", 1,15);
        buttonTornaIndietro.createListenerButtonGoBack();
        //FINE

        mainUserOptionsPanel.add(buttonTornaIndietro);

        //CREA IL BOTTONE TORNA ALLA HOME
        Button buttonHome = new Button("SchermataLogin","Home",150,30);
        buttonHome.changeFontButton("Arial", 1,15);
        buttonHome.createListenerButtonHome();
        //FINE

        mainUserOptionsPanel.add(buttonHome);

        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);
        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);

        JPanel mainCenterPanel = new JPanel();
        mainCenterPanel.setLayout(new BoxLayout(mainCenterPanel, BoxLayout.PAGE_AXIS));


        //CREA IL BOTTONE LOGIN
        Button buttonLogin = new Button("SchermataLogin","LOGIN",150,150);
        buttonLogin.createListenerButtonLogin();
        Main.schermataLoginPanel.add(buttonLogin,BorderLayout.SOUTH);
        //FINE
    }
    
}
