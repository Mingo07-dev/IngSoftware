package farmacie.miglioriconnoi.Autenticazione.Control;

import farmacie.miglioriconnoi.Autenticazione.Views.SchermataRecuperoCredenziali;
import farmacie.miglioriconnoi.Common.ActionListeners;
import farmacie.miglioriconnoi.Main;
import farmacie.miglioriconnoi.Utils.Email;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControlRecuperoCredenziali {
    public static void setListeners(){
        ActionListener AC_GoBack = ActionListeners.createActionListenerButtonGoBack("SchermataAutenticazione");
        SchermataRecuperoCredenziali.GoBack.addListener(AC_GoBack);

        ActionListener AC_Credenziali = ActionListeners.createListenerButtonRecuperaCredenziali();
        SchermataRecuperoCredenziali.recuperaCredenziali_Button.addListener(AC_Credenziali);
    }

    public static void recuperaCredenziali(){
        ResultSet resultSet = null;
        try {
            resultSet = Main.dbms_Azienda.getData("SELECT email,password from dbms_azienda.utente WHERE email = '" + SchermataRecuperoCredenziali.emailText.getText() + "';");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }

        try {
            if(resultSet.next()) {
                SchermataRecuperoCredenziali.mail = null;
                try {
                    resultSet.first();
                    SchermataRecuperoCredenziali.mail = resultSet.getString(1);
                    SchermataRecuperoCredenziali.password = resultSet.getString(2);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if(SchermataRecuperoCredenziali.emailText.getText().equals(SchermataRecuperoCredenziali.mail)){
            Frame frame = new Frame();
            Email.sendEmail(SchermataRecuperoCredenziali.mail,SchermataRecuperoCredenziali.password);
            JOptionPane.showMessageDialog(frame, "TI ABBIAMO INVIATO UNA EMAIL CONTENENTE" +
                    "\nLE TUE CREDENZIALI ALLA MAIL FORNITA");
        }
        else{
            Frame frame = new Frame();
            JOptionPane.showMessageDialog(frame, "NON ESISTE UN ACCOUNT CON QUESTA MAIL");
        }
    }
}
