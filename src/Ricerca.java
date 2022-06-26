import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Ricerca {

    public static void restoreTable(int n,Table table,JTextField ricercaField){
        int dim = (n+1)*4;
        ricercaField.setText("Ricerca Ex: '2022-06-20'");
        for(int i = 4; i < dim; i++){
            table.getComponent(i).setVisible(true);
        }
    }

    public static void searchInTableText(String textSearch, int n,Table table){
        //4 colonne
        int dim = (n+1)*4;
        int trovati = 0;
        Component elementTable = null;
        Component componentPanel = null;
        String element = "";
        Vector posizioni = new Vector(1);

        for(int i = 5; i < dim; i += 4){
            elementTable = table.getComponent(i);
            if (elementTable instanceof JPanel) {
                JPanel pnl = (JPanel)elementTable;
                componentPanel = pnl.getComponent(0);
            }
            if (componentPanel instanceof JLabel) {
                JLabel pnl = (JLabel)componentPanel;
                element = pnl.getText();
            }
            if(element.equals(textSearch))
            {
                posizioni.addElement(i);
            }
        }

        for(int i = 4; i < dim; i++){
                table.getComponent(i).setVisible(false);
        }
        for(int i = 0; i < posizioni.capacity(); i++)
        {
            table.getComponent((Integer) posizioni.get(i)-1).setVisible(true);
            table.getComponent((Integer) posizioni.get(i)).setVisible(true);
            table.getComponent((Integer) posizioni.get(i)+1).setVisible(true);
            table.getComponent((Integer) posizioni.get(i)+2).setVisible(true);
        }
    }
}
