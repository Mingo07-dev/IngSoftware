import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//QUESTA CLASSE CONTIENE I METODI PER LA CREAZIONE DI OGNI TIPO DI BOTTONE
public class Button extends JButton {

    private String currentView;
    private JPanel viewToAddOn;
    private String viewToShow;
    private final int width;
    private final int height;
    private final GridBagConstraints buttonConstraints;

    //lastView SERVE PER TENERE TRACCIA DELL'ULTIMA SCHERMATA VISITATA COSì DA POTER TORNARE INDIETRO
    public static String lastView = "SchermataAutenticazione";

    //COSTRUTTORE DEL BOTTONE PER CAMBIARE SCHERMATA
    Button(String currentView, JPanel viewToAddOn,String viewToShow, String buttonName, int width, int height, GridBagConstraints buttonConstraints){
        this.currentView = currentView;
        this.viewToAddOn = viewToAddOn;
        this.viewToShow = viewToShow;
        this.width = width;
        this.height = height;
        this.buttonConstraints = buttonConstraints;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
        //aggiunge il bottone alla schermata
        viewToAddOn.add(this, buttonConstraints);
    };

    //COSTRUTTORE DEL IL BOTTONRE "TORNA INDIETRO"
    Button(String currentView, JPanel viewToAddOn, int width, int height, GridBagConstraints buttonConstraints){
        this.currentView = currentView;
        this.viewToAddOn = viewToAddOn;
        this.width = width;
        this.height = height;
        this.buttonConstraints = buttonConstraints;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText("Torna Indietro");
        this.setPreferredSize(new Dimension(width,  height));
        //aggiunge il bottone alla schermata
        viewToAddOn.add(this, buttonConstraints);
    };

    //COSTRUTTORE DEL BOTTONE LOGIN
    Button(int width, int height, GridBagConstraints buttonConstraints){
        this.width = width;
        this.height = height;
        this.buttonConstraints = buttonConstraints;
        this.setText("LOGIN");
        //dichiara il bottone e lo inizializza con una dimensione da noi scelta

        this.setPreferredSize(new Dimension(width,  height));
        //aggiunge il bottone alla schermata
        Main.schermataLoginPanel.add(this, buttonConstraints);
    };






//CREAZIONE DEI LISTENERS

    public void createListenerButtonChangeView( ){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, this.viewToShow);
            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }


    public void createListenerButtonGoBack(){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, Button.lastView);

            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }

    public void createListenerButtonLogin(){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, SchermataLogin.mansione);

            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }

}
