package farmacie.miglioriconnoi.Common;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PasswordField extends JPasswordField {
    public PasswordField(int maxCharacters, int preferredWidth, int preferredHeight){
        this.setColumns(maxCharacters);
        this.setText("Password");
        this.setPreferredSize(new Dimension(preferredWidth,preferredHeight));
        this.setFocusTraversalKeysEnabled(true);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PasswordField.this.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        PlainDocument document = (PlainDocument) PasswordField.this.getDocument();
        document.setDocumentFilter(new DocumentFilter() {

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= maxCharacters) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
    }
}
