package farmacie.miglioriconnoi.Autenticazione.Views;


import farmacie.miglioriconnoi.Autenticazione.Control.ControlImpiegatoAzienda;
import farmacie.miglioriconnoi.Common.Button;
import farmacie.miglioriconnoi.Common.Image;
import farmacie.miglioriconnoi.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class SchermataImpiegatoAzienda {
    public static Button bottoneLogOut;
    public static Button buttonSegnalazioniRisolte;
    public static Button buttonSegnalazioniIrrisolte;
    public SchermataImpiegatoAzienda() throws FileNotFoundException {

        JPanel mainPanel = new JPanel(new BorderLayout(0,200));

        JPanel mainNorthPanel = new JPanel(new BorderLayout());

        Image image = new Image("logo.png",100,100);

        mainNorthPanel.add(image, BorderLayout.WEST);

        bottoneLogOut = new Button("Logout", 150, 30);
        bottoneLogOut.changeFontButton("Arial", 1,15);
        JPanel mainUserOptionsPanel = new JPanel(new FlowLayout());
        mainUserOptionsPanel.add(bottoneLogOut);
        mainNorthPanel.add(mainUserOptionsPanel, BorderLayout.EAST);

        JPanel mainCenterFlow = new JPanel(new FlowLayout());

        JPanel mainCenterBox = new JPanel();
        mainCenterBox.setLayout(new BoxLayout(mainCenterBox, BoxLayout.PAGE_AXIS));


        buttonSegnalazioniRisolte = new Button("Segnalazioni Risolte", 300,50);
        buttonSegnalazioniRisolte.changeFontButton("Arial", 1,25);
        buttonSegnalazioniRisolte.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainCenterBox.add(buttonSegnalazioniRisolte);
        mainCenterBox.add(Box.createRigidArea(new Dimension(0,20)));

        buttonSegnalazioniIrrisolte = new Button("Segnalazioni Irrisolte", 300,50);
        buttonSegnalazioniIrrisolte.changeFontButton("Arial", 1,25);
        buttonSegnalazioniIrrisolte.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainCenterBox.add(buttonSegnalazioniIrrisolte);
        mainCenterFlow.add(mainCenterBox);

        mainPanel.add(mainNorthPanel, BorderLayout.NORTH);
        mainPanel.add(mainCenterFlow, BorderLayout.CENTER);

        Main.schermataImpiegatoAziendaPanel.add(mainPanel, BorderLayout.CENTER);

        ControlImpiegatoAzienda.setListeners();
    }
}
