package farmacie.miglioriconnoi.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Date;

//QUESTA CLASSE CONTIENE I METODI PER LA CREAZIONE DI OGNI TIPO DI BOTTONE
public class Button extends JButton {

    private String currentView;
    private JPanel viewToAddOn;
    private final int width;
    private final int height;

    public int Id_ordine;
    public Date data_consegna;

    //lastView SERVE PER TENERE TRACCIA DELL'ULTIMA SCHERMATA VISITATA COSÌ DA POTER TORNARE INDIETRO
    public static String lastView = "SchermataAutenticazione";

    //COSTRUTTORE

    public Button(String buttonName, int width, int height){
        this.width = width;
        this.height = height;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
    };

    public Button(String buttonName, int width, int height, int Id_ordine){
        this.width = width;
        this.height = height;
        this.Id_ordine = Id_ordine;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
    };

    public Button(String buttonName, int width, int height, int Id_ordine, Date data_consegna){
        this.width = width;
        this.height = height;
        this.Id_ordine = Id_ordine;
        this.data_consegna = data_consegna;

        //dichiara il bottone e lo inizializza con una dimensione da noi scelta
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(width,  height));
    };



    //METODO PER CAMBIARE FONT E GRANDEZZA
    //ATTENZIONE: FONT STYLE --> 0 = PLAIN, 1 = BOLD, 2 = ITALIC
    public void changeFontButton(String fontName,int style, int size){

        this.setFont(new Font(fontName, style, size));

    }

    public void addListener(ActionListener AC){
        this.addActionListener(AC);
    }

    public int getId_ordine() {
        return Id_ordine;
    }

    public int[] trovaZeri(int[] array){
        int[] zeri = new int[array.length];
        for(int i = 0; i < zeri.length; i++){
            if(array[i] == 0){
                zeri[i] = 1;
            }
        }
        return zeri;
    }

    public int[] removeZerosFromArrayInt2(int[] zeros, int[] arrayPartenza){
        int[] zeri = zeros;
        boolean flag = false;
        for(int i = 0; i < zeri.length; i++){
            if(zeri[i] == 1){
                flag = true;
            }
        }

        int[] arrayRisultante;

        if(flag){
            int counter = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] == 1){
                    counter++;
                }
            }
            arrayRisultante = new int[arrayPartenza.length - counter];
            int k = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] != 1){
                    arrayRisultante[k] = arrayPartenza[i];
                    k++;
                }
            }
        } else{
            arrayRisultante = arrayPartenza;
        }
        return arrayRisultante;
    }

    public String[] removeZerosFromArrayString(int[] zeros, String[] arrayPartenza){
        int[] zeri = zeros;
        boolean flag = false;
        for(int i = 0; i < zeri.length; i++){
            if(zeri[i] == 1){
                flag = true;
            }
        }

        String[] arrayRisultante;

        if(flag){
            int counter = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] == 1){
                    counter++;
                }
            }
            arrayRisultante = new String[arrayPartenza.length - counter];
            int k = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] != 1){
                    arrayRisultante[k] = arrayPartenza[i];
                    k++;
                }
            }
        } else{
            arrayRisultante = arrayPartenza;
        }
        return arrayRisultante;
    }

    public Date[] removeZerosFromArrayDate(int[] zeros, Date[] arrayPartenza){
        int[] zeri = zeros;
        boolean flag = false;
        for(int i = 0; i < zeri.length; i++){
            if(zeri[i] == 1){
                flag = true;
            }
        }

        Date[] arrayRisultante;

        if(flag){
            int counter = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] == 1){
                    counter++;
                }
            }
            arrayRisultante = new Date[arrayPartenza.length - counter];
            int k = 0;
            for(int i = 0; i < zeri.length; i++){
                if(zeri[i] != 1){
                    arrayRisultante[k] = arrayPartenza[i];
                    k++;
                }
            }
        } else{
            arrayRisultante = arrayPartenza;
        }
        return arrayRisultante;
    }

}
