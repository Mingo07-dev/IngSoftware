import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextField extends JTextField {
    private String placeHolder;
    public TextField(int maxCharacters, String placeHolder, int preferredWidth, int preferredHeight){
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

}
