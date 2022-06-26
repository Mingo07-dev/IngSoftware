package farmacie.miglioriconnoi.Autenticazione;


import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataImpiegatoAzienda {
    public SchermataImpiegatoAzienda() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout(0,200));

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        Button bottoneLogOut = new Button("SchermataImpiegatoAzienda","Log out", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        bottoneLogOut.createListenerButtonLogOut();

        mainNorthPanel.add(bottoneLogOut, BorderLayout.EAST);

        JPanel mainCenterFlow = new JPanel(new FlowLayout());

        JPanel mainCenterBox = new JPanel();
        mainCenterBox.setLayout(new BoxLayout(mainCenterBox, BoxLayout.PAGE_AXIS));


        Button buttonSegnalazioniRisolte = new Button("SchermataImpiegatoAzienda", "Segnalazioni Risolte", 300,50);
        buttonSegnalazioniRisolte.changeFontButton("Arial", 1,25);
        buttonSegnalazioniRisolte.createListenerButtonChangeView("SchermataSegnalazioniRisolte");
        buttonSegnalazioniRisolte.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainCenterBox.add(buttonSegnalazioniRisolte);
        mainCenterBox.add(Box.createRigidArea(new Dimension(0,20)));

        Button buttonSegnalazioniIrrisolte = new Button("SchermataImpiegatoAzienda", "Segnalazioni Irrisolte", 300,50);
        buttonSegnalazioniIrrisolte.changeFontButton("Arial", 1,25);
        buttonSegnalazioniIrrisolte.createListenerButtonChangeView("SchermataSegnalazioniIrrisolte");
        buttonSegnalazioniIrrisolte.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainCenterBox.add(buttonSegnalazioniIrrisolte);
        mainCenterFlow.add(mainCenterBox);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterFlow, BorderLayout.CENTER);

        Main.schermataImpiegatoAziendaPanel.add(mainPanel, BorderLayout.CENTER);
        

    }
}
