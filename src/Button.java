import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//QUESTA CLASSE CONTIENE I METODI PER LA CREAZIONE DI OGNI TIPO DI BOTTONE
public class Button extends JButton {

    private String currentView;
    private JPanel viewToAddOn;
    private final int width;
    private final int height;

    //lastView SERVE PER TENERE TRACCIA DELL'ULTIMA SCHERMATA VISITATA COSì DA POTER TORNARE INDIETRO
    public static String lastView = "SchermataAutenticazione";

    //COSTRUTTORE
    Button(String currentView, String buttonName, int width, int height){
        this.currentView = currentView;
        this.width = width;
        this.height = height;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
    };

    Button(String buttonName, int width, int height){
        this.width = width;
        this.height = height;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
    };



    //METODO PER CAMBIARE FONT E GRANDEZZA
    //ATTENZIONE: FONT STYLE --> 0 = PLAIN, 1 = BOLD, 2 = ITALIC
    public void changeFontButton(String fontName,int style, int size){

        this.setFont(new Font(fontName, style, size));

    }



    //CREAZIONE DEI LISTENERS

    public void createListenerButtonChangeView(String viewToShow ){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, viewToShow);


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

    public void createListenerButtonHome(){
        this.addActionListener(e -> {
            //mostra la nuova schermata
            Main.cardLayout.show(Main.mainPanel, SchermataLogin.mansione);

            //salva il nome della schermata che abbiamo appena lasciato, per poter eventualmente
            //tornare indietro tramite apposito bottone
            Button.lastView = "" + this.currentView;
        });
    }

}
