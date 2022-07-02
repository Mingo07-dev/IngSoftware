package farmacie.miglioriconnoi.Common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class TextField extends JTextField {
    private String placeHolder;
    public int contatore;
    public TextField(int maxCharacters, String placeHolder, int preferredWidth, int preferredHeight){
        this.placeHolder = placeHolder;
        this.setColumns(maxCharacters);
        this.setText(this.placeHolder);
        this.setPreferredSize(new Dimension(preferredWidth,preferredHeight));
        this.setFocusTraversalKeysEnabled(true);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TextField.this.setText("");
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
        ((AbstractDocument)this.getDocument()).setDocumentFilter(new LimitDocumentFilter(maxCharacters));
    }

    public TextField(int maxCharacters, String placeHolder, int preferredWidth, int preferredHeight, int contatore){

        this.contatore = contatore;
        this.placeHolder = placeHolder;
        this.setColumns(maxCharacters);
        this.setText(this.placeHolder);
        this.setPreferredSize(new Dimension(preferredWidth,preferredHeight));
        this.setFocusTraversalKeysEnabled(false);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TextField.this.setText("");
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
        ((AbstractDocument)this.getDocument()).setDocumentFilter(new LimitDocumentFilter(maxCharacters));
        ((AbstractDocument)this.getDocument()).setDocumentFilter(new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length,
                                String str, AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            public void insertString(FilterBypass fb, int offs, String str,
                                     AttributeSet a) throws BadLocationException {

                String text = fb.getDocument().getText(0,
                        fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                        && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
    }
    public static class LimitDocumentFilter extends DocumentFilter {

        private int limit;

        public LimitDocumentFilter(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit can not be <= 0");
            }
            this.limit = limit;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - limit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() > 0) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

    }
}
