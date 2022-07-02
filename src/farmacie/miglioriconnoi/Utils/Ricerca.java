package farmacie.miglioriconnoi.Utils;

import farmacie.miglioriconnoi.Common.Table;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Ricerca {

    public static void restoreTable(int n, Table table, JTextField ricercaField, String placeHolder){
        int dim = (n+1)*4;
        ricercaField.setText(placeHolder);
        for(int i = 4; i < dim; i++){
            table.getComponent(i).setVisible(true);
        }
    }

    public static void searchInTableText(String textSearch, int n,Table table){
        //4 colonne
        int dim = (n+1)*4;
        Component elementTable = null;
        Component componentPanel = null;
        String element = "";
        Vector posizioni = new Vector(0);

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
            if(element.equals(textSearch) || element.toLowerCase().equals(textSearch))
            {
                posizioni.addElement(i);
            }
        }

        for(int i = 4; i < dim; i++){
                table.getComponent(i).setVisible(false);
        }


        for (int i = 0; i < posizioni.capacity(); i++) {
            table.getComponent((Integer) posizioni.get(i) - 1).setVisible(true);
            table.getComponent((Integer) posizioni.get(i)).setVisible(true);
            table.getComponent((Integer) posizioni.get(i) + 1).setVisible(true);
            table.getComponent((Integer) posizioni.get(i) + 2).setVisible(true);
        }
    }


    public static void searchInTablePrenota(String textSearch, int n,Table table){
        //4 colonne
        int dim = (n+1)*4;
        Component elementTable = null;
        Component componentPanel = null;
        String element = "";
        Vector posizioni = new Vector(0);

        for(int i = 4; i < dim; i += 4){
            elementTable = table.getComponent(i);
            if (elementTable instanceof JPanel) {
                JPanel pnl = (JPanel)elementTable;
                componentPanel = pnl.getComponent(0);
            }
            if (componentPanel instanceof JLabel) {
                JLabel pnl = (JLabel)componentPanel;
                element = pnl.getText();
            }
            if(element.equals(textSearch) || element.toLowerCase().equals(textSearch))
            {
                posizioni.addElement(i);
            }
        }

        for(int i = 4; i < dim; i++){
            table.getComponent(i).setVisible(false);
        }


        for(int i = 0; i < posizioni.capacity(); i++)
        {
            table.getComponent((Integer) posizioni.get(i)).setVisible(true);
            table.getComponent((Integer) posizioni.get(i)+1).setVisible(true);
            table.getComponent((Integer) posizioni.get(i)+2).setVisible(true);
            table.getComponent((Integer) posizioni.get(i)+3).setVisible(true);
        }

    }
}
