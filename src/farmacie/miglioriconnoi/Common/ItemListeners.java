package farmacie.miglioriconnoi.Common;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ItemListeners {
    public static ItemListener createItemListenerMostraPassword(PasswordField passwordField){
        ItemListener itemlistener = new ItemListener() {public void itemStateChanged(ItemEvent itemE) {
            int st = itemE.getStateChange();
            if (st == ItemEvent.SELECTED) {
                passwordField.setEchoChar((char) 0);
            }
            if (st == ItemEvent.DESELECTED) {
                passwordField.setEchoChar('*');
            }
        }};

        return itemlistener;
    }

    public static ItemListener createItemListenerMostraPasswordAndConfirmPassword(PasswordField passwordField, PasswordField confirmPassword){
        ItemListener itemlistener = new ItemListener() {public void itemStateChanged(ItemEvent itemE) {
            int st = itemE.getStateChange();
            if (st == ItemEvent.SELECTED) {
                passwordField.setEchoChar((char) 0);
                confirmPassword.setEchoChar((char) 0);
            }
            if (st == ItemEvent.DESELECTED) {
                passwordField.setEchoChar('*');
                confirmPassword.setEchoChar('*');
            }
        }};

        return itemlistener;
    }
}
