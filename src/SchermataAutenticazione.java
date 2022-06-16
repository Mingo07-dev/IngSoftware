import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SchermataAutenticazione {
    public SchermataAutenticazione(){

        JPanel nordBorderLayout = new JPanel(new BorderLayout());
        JPanel sudBorderLayout = new JPanel(new BorderLayout());

        Main.schermataAutenticazionePanel.add(nordBorderLayout, BorderLayout.NORTH);
        Main.schermataAutenticazionePanel.add(sudBorderLayout, BorderLayout.SOUTH);

        JPanel nordBorderLayout_nordSection = new JPanel(new BorderLayout());
        JPanel nordBorderLayout_sudSection = new JPanel(new BorderLayout());
        JPanel sudBorderLayout_nordSection = new JPanel(new BorderLayout());
        JPanel sudBorderLayout_sudSection = new JPanel(new BorderLayout());

        JPanel nordFlowLayout_nordSection = new JPanel(new FlowLayout());
        JPanel nordFlowLayout_sudSection = new JPanel(new FlowLayout());
        JPanel sudFlowLayout_nordSection = new JPanel(new FlowLayout());
        JPanel sudFlowLayout_sudSection = new JPanel(new FlowLayout());

        nordBorderLayout.add(nordBorderLayout_nordSection, BorderLayout.NORTH);
        nordBorderLayout.add(nordBorderLayout_sudSection, BorderLayout.SOUTH);
        sudBorderLayout.add(sudBorderLayout_nordSection, BorderLayout.NORTH);
        sudBorderLayout.add(sudBorderLayout_sudSection, BorderLayout.SOUTH);


        nordBorderLayout_nordSection.add(nordFlowLayout_nordSection);
        nordBorderLayout_sudSection.add(nordFlowLayout_sudSection);
        sudBorderLayout_nordSection.add(sudFlowLayout_nordSection);
        sudBorderLayout_sudSection.add(sudFlowLayout_sudSection);

        JButton botton1 = new JButton("logo");
        JButton botton2 = new JButton("login");
        JButton botton3 = new JButton("registrazione");
        JButton botton4 = new JButton("recuperoCredenziali");

        nordFlowLayout_nordSection.add(botton1);
        nordFlowLayout_sudSection.add(botton2);
        sudFlowLayout_nordSection.add(botton3);
        sudFlowLayout_sudSection.add(botton4);
    }


}
