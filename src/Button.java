import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//QUESTA CLASSE CONTIENE I METODI PER LA CREAZIONE DI OGNI TIPO DI BOTTONE
public class Button {
    public static String lastView = "SchermataAutenticazione";

    //QUESTO METODO PERMETTE LA CREAZIONE DI UN SEMPLICE BOTTONE CHE CAMBIA SCHERMATA DA QUELLA ATTUALE AD UNA A SCELTA
    //CHIEDE IN INPUT LA SCHERMATA CORRENTE, LA SCHERMATA SUL QUALE AGGIUNGERE IL BOTTONE
    //(DOVREBBERO COINCIDERE, MA IL PRIMO DATO è UNA STRINGA, IL SECONDO è UN PANEL)
    //, LA SCHERMATA DA MOSTRARE E IL NOME DEL BOTTONE
    public static void createButtonChangeView(String currentView, JPanel viewToAddOn,String viewToShow, String buttonName){

        //DICHIARA IL BOTTONE E LO INIZIALIZZA
        JButton buttonChangeView = new JButton(buttonName);
        //AGGIUNGE IL BOTTONE ALLA SCHERMATA
        viewToAddOn.add(buttonChangeView);
        //IMPOSTA UN LISTENER PER SAPERE QUANDO IL BOTTONE VIENE PREMUTO
        buttonChangeView.addActionListener(e -> {
            //MOSTRA LA NUOVA SCHERMATA
            Main.cardLayout.show(Main.mainPanel, viewToShow);
            //SALVA IL NOME DELLA SCHERMATA CHE ABBIAMO APENNA LASCIATO, PER POTER EVENTUALMENTE
            //TORNARE INDIETRO TRAMITE APPOSITO BOTTONE
            lastView = "" + currentView;
        });
    }


    //QUESTO METODO PERMETTE LA CREAZIONE DI UN BOTTONE CHE PERMETTE DI TORNARE INDIETRO DALLA SCHERMATA ATTUALE A QUELLA PRECEDENTE
    //CHIEDE IN INPUT COME PARAMETRI LA SCHERMATA ATTUALE E LA SCHERMATA SUL QUALE AGGIUNGERE IL BOTTONE
    //(DOVREBBERO COINCIDERE, MA IL PRIMO DATO è UNA STRINGA, IL SECONDO è UN PANEL)
    public static void createButtonGoBack(String currentView, JPanel viewToAddOn){

        //DICHIARA IL BOTTONE E LO INIZIALIZZA
        JButton buttonChangeView = new JButton("Torna Indietro");
        //AGGIUNGE IL BOTTONE ALLA SCHERMATA
        viewToAddOn.add(buttonChangeView);
        //IMPOSTA UN LISTENER PER SAPERE QUANDO IL BOTTONE VIENE PREMUTO
        buttonChangeView.addActionListener(e -> {
            //MOSTRA LA NUOVA SCHERMATA
            Main.cardLayout.show(Main.mainPanel, lastView);
            //SALVA IL NOME DELLA SCHERMATA CHE ABBIAMO APENNA LASCIATO, PER POTER EVENTUALMENTE
            //TORNARE INDIETRO TRAMITE APPOSITO BOTTONE
            lastView = "" + currentView;
        });
    }


}
