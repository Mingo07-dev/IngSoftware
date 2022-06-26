package farmacie.miglioriconnoi.Common;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PasswordField extends JPasswordField {
    //MOSTRA PASSWORD
    //httpProxyPassword.setEchoChar((char) 0);
    //NASCONDI PASSWORD
    //httpProxyPassword.setEchoChar('*');
    public PasswordField(int maxCharacters, int preferredWidth, int preferredHeight){
        this.setColumns(maxCharacters);
        this.setText("Password");
        this.setPreferredSize(new Dimension(preferredWidth,preferredHeight));
        this.setFocusTraversalKeysEnabled(false);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PasswordField.this.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public static void addListenerCheckboxMostraPassword(JCheckBox mostraPassword, PasswordField passwordField) {

        ItemListener itemlistener = new ItemListener() {public void itemStateChanged(ItemEvent itemE) {

            AbstractButton absB = (AbstractButton) itemE.getSource();

            Color fgrnd = absB.getForeground();

            Color bgrnd = absB.getBackground();

            int st = itemE.getStateChange();

            if (st == ItemEvent.SELECTED) {
                passwordField.setEchoChar((char) 0);

            }

            if (st == ItemEvent.DESELECTED) {
                passwordField.setEchoChar('*');
            }

        }};
        mostraPassword.addItemListener(itemlistener);

    }

    public static void addListenerCheckboxMostraPasswordEConfirmPassword(JCheckBox mostraPassword, PasswordField passwordField, PasswordField confirmPasswordFiels) {

        ItemListener itemlistener = new ItemListener() {public void itemStateChanged(ItemEvent itemE) {

            AbstractButton absB = (AbstractButton) itemE.getSource();

            Color fgrnd = absB.getForeground();

            Color bgrnd = absB.getBackground();

            int st = itemE.getStateChange();

            if (st == ItemEvent.SELECTED) {
                passwordField.setEchoChar((char) 0);
                confirmPasswordFiels.setEchoChar((char) 0);

            }

            if (st == ItemEvent.DESELECTED) {
                passwordField.setEchoChar('*');
                confirmPasswordFiels.setEchoChar('*');
            }

        }};
        mostraPassword.addItemListener(itemlistener);

    }

}
